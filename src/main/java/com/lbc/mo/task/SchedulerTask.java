package com.lbc.mo.task;

import com.lbc.mo.conf.MyCondition;
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
@Conditional(MyCondition.class)
public class SchedulerTask {


    private static final Log LOG = LogFactory.getLog(SchedulerTask.class);
    @Autowired
    TestService testService;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        return factory;
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public String saveDataToMysqlandCheck() {
        LOG.info("data loading completed");
        return "wq";
    }

//    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
//    public void stats() {
//        //extra service
//        testService.testCache("chang");
//        testService.testEvictCache("chang");
//        LOG.info("loading extra service");
//    }

//    static final Counter counterDemo = Counter.build()
//            .name("counterChanger2").labelNames("wy", "zxjr", "ocs", "xxjf", "unit")
//            .help("Counter 实例").register();
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    @RequestMapping("/changeCounter")
//    public void changeCounter() {
//        counterDemo.labels("网元", "在线接入", "OCS", "消息计费", "seconds").inc();//指标值增加
//    }
}
