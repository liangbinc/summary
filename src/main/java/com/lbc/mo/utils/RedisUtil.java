package com.lbc.mo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import java.io.IOException;

public class RedisUtil {

    private static final Log LOG = LogFactory.getLog(RedisUtil.class);
    private static Pool<Jedis> pool;

    private RedisUtil() {
    }

    public static synchronized Pool<Jedis> getJeidsPool() throws IOException {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxWaitMillis(1000);
        config.setMaxIdle(100);
        config.setTestWhileIdle(true);
        config.setTestOnBorrow(true);
        config.setTimeBetweenEvictionRunsMillis(30000);
        pool = new JedisPool("localhost");
        return pool;

    }

    public static synchronized Jedis getJeidsConn() throws IOException {
        if (pool == null) {
            pool = getJeidsPool();
        }
        return pool.getResource();
    }

    public static String getHostAndPort() {
        String hostAndPort = "";
        try {
            Jedis conn = RedisUtil.getJeidsConn();
            hostAndPort = conn.get("husky_ha_lock");
            System.err.println("-------------" + hostAndPort + "----------");
            conn.close();
        } catch (IOException e) {
            LOG.info("get hostAndPort bad");
        }
        return hostAndPort;
    }

    public static void close(Jedis coon) {
        if (coon != null) {
            coon.close();
        }
    }

    public static void getInfo() {
        System.err.println("NumIdle" + pool.getNumIdle());
        System.err.println("NumActive:" + pool.getNumActive());
    }
}
