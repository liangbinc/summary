package com.lbc.mo.task;

import com.lbc.mo.conf.MailCondition;
import com.lbc.mo.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Conditional(MailCondition.class)
public class SchedulerMailTask {


    private static final Log LOG = LogFactory.getLog(SchedulerMailTask.class);
    @Autowired
    TestService testService;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        return factory;
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public String saveDataToMysqlandCheck() {
        LOG.info("mail schedule task");
        return "wq";
    }
}
