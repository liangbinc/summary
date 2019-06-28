package com.lbc.mo.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CacheConfiguration {
    /**
     * get cache manager
     */

//    @Bean(name = "ehcache")
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//        EhCacheManagerFactoryBean cacheBean = new EhCacheManagerFactoryBean();
//        cacheBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//        return cacheBean;
//    }

//    @Bean("ehCacheCacheManager")
//    public EhCacheCacheManager ehCacheCacheManager(@Qualifier("ehcache")  ehcacheManager) {
//        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(ehcacheManager);
//        return ehCacheCacheManager;
//    }

//    @Bean(name = "cacheManager")
//    @Primary
//    public CompositeCacheManager cacheManager(EhCacheCacheManager ehCacheCacheManager) {
//        CompositeCacheManager cacheManager = new CompositeCacheManager(ehCacheCacheManager);
//        return cacheManager;
//    }
}
