package com.lbc.mo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbc.mo.bean.GroupUsers;
import com.lbc.mo.bean.StreamMessageFactory;
import com.lbc.mo.bean.StreamType;
import com.lbc.mo.dao.AppStateRepository;
import com.lbc.mo.entity.AppState;
import com.lbc.mo.netty.NettyClient;
import com.lbc.mo.netty.StreamService;
import com.lbc.mo.service.TestService;
import com.lbc.mo.utils.RedisUtil;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Date;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    NettyClient nettyClient;
    @Autowired
    AppStateRepository appStateRepository;

    @Override
    @Cacheable(value = "redisUserInfo", key = "#user")
    public GroupUsers.User getUserInfoCache(String user) {
        Jedis conn = null;
        GroupUsers.User userInfo = null;
        try {
            conn = RedisUtil.getJeidsConn();
            if (!StringUtils.isEmpty(user)) {
                String s = conn.get(user);
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(s, GroupUsers.User.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                conn.close();
            }
        }
        return userInfo;
    }

    @Override
    @Cacheable(value = "nettyCache", key = "#user")
    public String testCache(String user) {
        if ("chang".equals(user)) {
            System.out.println(11111);
            return "lb";
        } else {
            return null;
        }
    }

    @Override
    @CacheEvict(value = "nettyCache", key = "#user")
    public String testEvictCache(String user) {
        if ("chang".equals(user)) {
            System.out.println(11111);
            return "lb";
        } else {
            return null;
        }
    }

    @Override
    public void conNetty() {
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

    @Override
    public void saveTest() {
        AppState appState = new AppState();
        appState.setAppId("app_add_" + 1);
        appState.setUser("add_app");
        appState.setState("INIT");
        appState.setStartTime(new Date());
        appState.setEndTime(new Date());
        appStateRepository.save(appState);
    }
}
