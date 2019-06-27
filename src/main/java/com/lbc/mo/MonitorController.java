package com.lbc.mo;

import com.lbc.mo.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MonitorController {
    private static final Log LOG = LogFactory.getLog(MonitorController.class);
    @Autowired
    TestService testService;
    @Autowired
    EhCacheCacheManager ehCacheCacheManager;


    @PostMapping(value = "/manage")
    public String manageGpuLable(@RequestParam String sDay, @RequestParam String eDay) {
        return "done";
    }


    @GetMapping(value = "/testNetty")
    public void manageGpuLable() {
        testService.conNetty();
    }
}
