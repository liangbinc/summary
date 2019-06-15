package com.lbc.mo;

import com.lbc.mo.bean.StreamMessageFactory;
import com.lbc.mo.bean.StreamType;
import com.lbc.mo.netty.NettyClient;
import com.lbc.mo.netty.StreamService;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MonitorController {
    private static final Log LOG = LogFactory.getLog(MonitorController.class);
    @Autowired
    NettyClient nettyClient;
    @Autowired
    EhCacheCacheManager ehCacheCacheManager;


    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    public String manageGpuLable(@RequestParam String sDay, @RequestParam String eDay, @RequestParam String v100TOP) {
        return "done";
    }


    @RequestMapping(value = "/testNetty", method = RequestMethod.GET)
    public void manageGpuLable() {
        try {
            String sendMsg = "send msg";
            byte[] msgBody = StreamMessageFactory.factory((byte) 0x01, StreamType.LOG.getValue(), sendMsg.getBytes());
            Channel channel = null;
            channel = nettyClient.connect("localhost", StreamService.getPort(), "testContainerName");
            if (!channel.isActive()) {
                nettyClient.cleanCon("testContainerName");
                channel = nettyClient.connect("localhost", StreamService.getPort(), "testContainerName");
            }
            ByteBuf meg = Unpooled.wrappedBuffer(msgBody);
            channel.writeAndFlush(meg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
