package com.lbc.mo.netty;

import io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.SocketChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioSocketChannel;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class NettyClient implements InitializingBean {
    static final Bootstrap STRAP = new Bootstrap();
    static final EventLoopGroup WORKERGROUP = new NioEventLoopGroup();
    private static final Log LOG = LogFactory.getLog(NettyClient.class);


    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("localhost", 62598, "test");
    }

    public void close() {
        WORKERGROUP.shutdownGracefully();
    }

    public void await(Channel channel, long timeoutMillis) {
        try {
            channel.closeFuture().await(timeoutMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Cacheable(value = "nettyCache", key = "#containerName")
    public Channel connect(String host, int port, String containerName) {
        LOG.info("con " + containerName);
        STRAP.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ClientHandler(containerName));
            }
        });

        ChannelFuture future = null;
        try {
            future = STRAP.connect(host, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return future.channel();
    }

    @CacheEvict(value = "nettyCache", key = "#containerName", allEntries = false, beforeInvocation = true)
    public void cleanCon(String containerName) {
        LOG.info("clean con" + containerName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        STRAP.channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .group(WORKERGROUP);
    }

    static class ClientHandler extends ChannelInboundHandlerAdapter {
        public String containerName;

        ClientHandler(String containerName) {
            this.containerName = containerName;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                ByteBuf in = (ByteBuf) msg;
                byte[] readBody = new byte[in.readableBytes()];
//                long l = in.readUnsignedInt();
                in.readBytes(readBody);
                byte[] statslen = new byte[4];
                if (readBody.length > 0) {
                    System.arraycopy(readBody, 0, statslen, 0, statslen.length);
                    if (readBody.length - 4 >= byteArrayToInt(statslen) && byteArrayToInt(statslen) > 0) {

                        byte[] stream = new byte[byteArrayToInt(statslen)];
                        System.arraycopy(readBody, 4, stream, 0, byteArrayToInt(statslen));
                        LOG.info("client receive the server callback :" + new String(stream));

                    } else {
                        LOG.info("the " + containerName + " length not enough");
                    }
                }
            } catch (RuntimeException e) {
                LOG.error("Netty Exception : " + containerName + e.getMessage());
                ctx.close();
            } finally {
                ReferenceCountUtil.release(msg);
            }

        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            LOG.info("Client channelReadComplete " + ctx.channel().remoteAddress());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            LOG.info("Client exceptionCaught " + ctx.channel().remoteAddress() + " " + containerName);
            cause.printStackTrace();
            ctx.close();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            LOG.info("Client channelActive" + ctx.channel().remoteAddress() + containerName);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            LOG.info("Client channelInactive" + ctx.channel().remoteAddress() + " " + containerName);
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            LOG.info("Client channelRegistered" + ctx.channel().remoteAddress() + containerName);
        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            LOG.info("Client channelUnRegistered" + ctx.channel().remoteAddress() + " " + containerName);
        }
    }
}
