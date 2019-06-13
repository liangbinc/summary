package com.lbc.mo;

import com.lbc.mo.utils.AppIdMapCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@CacheConfig
public class SchedulerTask {


    private static final Log LOG = LogFactory.getLog(SchedulerTask.class);
    static AppIdMapCache resultMap = AppIdMapCache.getInstance();


    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        return factory;
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public AppIdMapCache saveDataToMysqlandCheck() {
        LOG.info("data loading completed");
        return resultMap;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void stats() {
        //extra service
        LOG.info("loading extra service");
    }

}
