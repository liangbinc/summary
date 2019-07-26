package com.lbc.mo;

import com.lbc.mo.entity.User;
import com.lbc.mo.service.TestService;
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
