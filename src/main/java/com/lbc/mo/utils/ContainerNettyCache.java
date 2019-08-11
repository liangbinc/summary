package com.lbc.mo.utils;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerNettyCache {
    private static volatile ContainerNettyCache statsMapCache;
    private static volatile Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

    public static ContainerNettyCache getInstance() {
        if (null == statsMapCache) {
            synchronized (ContainerNettyCache.class) {
                if (null == statsMapCache) {
                    statsMapCache = new ContainerNettyCache();
                }
            }
        }
        return statsMapCache;
    }

    public void setCache(String key, Object obj) {
        cacheMap.put(key, obj);
    }

    public Object getCache(String key) {
        return cacheMap.get(key);
    }

    public void removeCache(String key) {
        cacheMap.remove(key);
    }

    public Boolean containsKeyCache(String key) {
        return cacheMap.containsKey(key);
    }

    public Set<Map.Entry<String, Object>> getEntrySet() {
        return cacheMap.entrySet();
    }
}
