package com.lbc.mo.netty;

import com.lbc.mo.bean.StreamMessageFactory;
import com.lbc.mo.bean.StreamType;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioServerSocketChannel;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import static io.grpc.netty.shaded.io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.grpc.netty.shaded.io.netty.channel.ChannelOption.SO_KEEPALIVE;

public class StreamService {

    private static final Log LOG = LogFactory.getLog(StreamService.class);


    private Channel channel;
    private static int port;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;


    public void serviceInit() throws Exception {
        int bossSize = 1;
        int workSize = 8;
        bossGroup = new NioEventLoopGroup(bossSize, new DefaultThreadFactory("boss", true));
        workerGroup = new NioEventLoopGroup(workSize, new DefaultThreadFactory("worker", true));

        initNettyServer();

        port = getTransportPort();

        LOG.info("StreamService Started listen on " + port);
    }

    protected void serviceStop() throws Exception {
        if (channel != null || channel.isOpen()) {
            channel.close();
            return;
        }

        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static int getPort() {
        return port;
    }

    protected void initNettyServer() throws IOException {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(SO_BACKLOG, 128)
            .childOption(SO_KEEPALIVE, true)
            .childHandler(new ChannelInitializer<Channel>() {
            @Override
            public void initChannel(Channel ch) throws Exception {
                LOG.info("StreamService Netty Channel init");

                ch.pipeline().addLast(new StreamHandler());

                ch.closeFuture().addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) {
                        LOG.info("StreamService Netty Channel close");
                    }
                });
            }
        });

        // Bind and start to accept incoming connections.
        ChannelFuture future = b.bind(0);
        try {
            future.await().sync();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted waiting for bind");
        }
        if (!future.isSuccess()) {
            throw new IOException("Failed to bind", future.cause());
        }
        channel = future.channel();
    }

    protected int getTransportPort() {
        if (channel == null) {
            return -1;
        }
        SocketAddress localAddr = channel.localAddress();
        if (!(localAddr instanceof InetSocketAddress)) {
            return -1;
        }
        return ((InetSocketAddress) localAddr).getPort();
    }

    static class StreamHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                StreamMessageFactory streamMessageFactory = decode((ByteBuf) msg);
                LOG.info("stream type : " + streamMessageFactory.getType());
                if (streamMessageFactory != null && streamMessageFactory.getType() != null) {

                    if (streamMessageFactory.getType() == StreamType.LOG) {
                        byte[] message = streamMessageFactory.getMessage();
                        String s = new String(message);
                        LOG.info("server receive :" + s);
                        doLogContainer(ctx, "msg");
                    } else {
                        LOG.error("Error Stream Type");
                        ctx.write("Error Stream Type");
                        ctx.close();
                    }
                } else {
                    LOG.error("Error when channelRead");
                    ctx.write("Error when decode");
                    ctx.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            // System.out.println("channelReadComplete " +
            // ctx.channel().remoteAddress());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            // System.out.println("channelActive " +
            // ctx.channel().remoteAddress());
            // ByteBuf meg = Unpooled.wrappedBuffer("uname\n".getBytes());
            // ctx.writeAndFlush(meg);
        }

        StreamMessageFactory decode(ByteBuf msg) {
            // in.array();
            byte[] readBody = new byte[msg.readableBytes()];
            msg.readBytes(readBody);
            byte magic = readBody[0];
            byte type = readBody[1];
            return new StreamMessageFactory(magic, type, tail(readBody, readBody.length - 2));
        }


        void doLogContainer(ChannelHandlerContext ctx, String containerStatMessage) {
            LOG.info("log :" + containerStatMessage);
            byte[] statMsg = containerStatMessage.getBytes();
            byte[] body = new byte[4 + statMsg.length];
            Bytes.putInt(body, 0, statMsg.length);
            Bytes.putBytes(body, 4, statMsg, 0, statMsg.length);
            ctx.channel().writeAndFlush(Unpooled.wrappedBuffer(body));
        }

        public static byte[] tail(byte[] a, int length) {
            if (a.length < length) {
                return null;
            } else {
                byte[] result = new byte[length];
                System.arraycopy(a, a.length - length, result, 0, length);
                return result;
            }
        }
    }
}
