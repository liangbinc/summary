package com.lbc.mo.utils;

import com.lbc.mo.entity.ContainerStats;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StatsMapCache {
    private static volatile StatsMapCache statsMapCache;
    private static volatile Map<String, ContainerStats> cacheMap = new ConcurrentHashMap<String, ContainerStats>();

    private StatsMapCache() {
    }

    public static StatsMapCache getInstance() {
        if (null == statsMapCache) {
            synchronized (StatsMapCache.class) {
                if (null == statsMapCache) {
                    statsMapCache = new StatsMapCache();
                }
            }
        }
        return statsMapCache;
    }

    public void setCache(String key, ContainerStats obj) {
        cacheMap.put(key, obj);
    }

    public ContainerStats getCache(String key) {
        return cacheMap.get(key);
    }

    public void removeCache(String key) {
        cacheMap.remove(key);
    }

    public Boolean containsKeyCache(String key) {
        return cacheMap.containsKey(key);
    }

    public Set<Map.Entry<String, ContainerStats>> getEntrySet() {
        return cacheMap.entrySet();
    }

}
