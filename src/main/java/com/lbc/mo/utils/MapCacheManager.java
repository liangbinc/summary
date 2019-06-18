package com.lbc.mo.utils;


import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class MapCacheManager {

    private static volatile MapCacheManager mapCacheObject;
    private static volatile Map<String, Map<String, String>> cacheMap = new ConcurrentHashMap<String, Map<String, String>>();

    private MapCacheManager() {
    }

    public static MapCacheManager getInstance() {
        if (null == mapCacheObject) {
            synchronized (MapCacheManager.class) {
                if (null == mapCacheObject) {
                    mapCacheObject = new MapCacheManager();
                }
            }
        }
        return mapCacheObject;
    }

    public void setCache(String key, Map<String, String> obj) {
        cacheMap.put(key, obj);
    }

    public Map<String, String> getCache(String key) {
        return cacheMap.get(key);
    }

    public void removeCache(String key) {
        cacheMap.remove(key);
    }

    public Boolean containsKeyCache(String key) {
        return cacheMap.containsKey(key);
    }

    public Set<Entry<String, Map<String, String>>> getEntrySet() {
        return cacheMap.entrySet();
    }

    public Set<String> keySet() {
        return cacheMap.keySet();
    }

}
