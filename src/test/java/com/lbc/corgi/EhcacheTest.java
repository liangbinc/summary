package com.lbc.corgi;

import com.lbc.mo.Application;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class EhcacheTest {

    @Resource
    private EhCacheCacheManager ehCacheCacheManager;

    @Test
    public void cacheSet() {
        // 显示所有的Cache空间
        System.out.println(StringUtils.join(ehCacheCacheManager.getCacheNames(), ","));
        Cache cache = ehCacheCacheManager.getCache("aiLabUserCache");
        cache.put("key", "123");
        System.out.println("缓存成功");
        String res = cache.get("key", String.class);
        System.out.println(res);
    }
}
