package com.lbc.mo;

import com.lbc.mo.entity.User;
import com.lbc.mo.service.TestService;
import io.prometheus.client.Counter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController("/")
public class MonitorController {
    private static final Log LOG = LogFactory.getLog(MonitorController.class);
    @Autowired
    TestService testService;
    @Autowired
    EhCacheCacheManager ehCacheCacheManager;
    /*
        *  使用Counter.build()创建Counter类型的监控指标，并且通过name()方法定义监控指标的名称network_traffic_input
        * ，通过labelNames()定义该指标包含的标签。最后通过register()将该指标注册到Collector的defaultRegistry中
        */

    @PostMapping(value = "/manage")
    public User manageGpuLable(@RequestParam String sDay, @RequestParam String eDay) {
        User user = new User();
        user.setId(1);
        user.setRecordTime(new Date());
        user.setUserName("changlb1");
        user.setMail("changlb1@lenovo.com");
        return user;
    }


    @GetMapping(value = "/testNetty")
    public void manageGpuLable() {
        testService.conNetty();
    }

}
