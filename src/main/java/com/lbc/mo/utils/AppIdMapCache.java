package com.lbc.mo.utils;


import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class AppIdMapCache {
    private static volatile AppIdMapCache appIdMapCache;
    private static Map<String, String> cacheMap = new ConcurrentHashMap<String, String>();

    private AppIdMapCache() {
    }

    public static AppIdMapCache getInstance() {
        if (null == appIdMapCache) {
            synchronized (AppIdMapCache.class) {
                if (null == appIdMapCache) {
                    appIdMapCache = new AppIdMapCache();
                }
            }
        }
        return appIdMapCache;
    }

    public void setCache(String key, String obj) {
        cacheMap.put(key, obj);
    }

    public String getCache(String key) {
        return cacheMap.get(key);
    }

    public void removeCache(String key) {
        cacheMap.remove(key);
    }

    public Boolean containsKeyCache(String key) {
        return cacheMap.containsKey(key);
    }

    public Set<Entry<String, String>> getEntrySet() {
        return cacheMap.entrySet();
    }

}
