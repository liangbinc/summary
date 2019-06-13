package com.lbc.mo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbc.mo.bean.GroupUsers;
import com.lbc.mo.service.TestService;
import com.lbc.mo.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
@Service
public class TestServiceImpl implements TestService {


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
}
