package com.lbc.mo;

import com.lbc.mo.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MonitorController {
    private static final Log LOG = LogFactory.getLog(MonitorController.class);
    @Autowired
    TestService testService;
    @Autowired
    EhCacheCacheManager ehCacheCacheManager;


    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    public String manageGpuLable(@RequestParam String sDay, @RequestParam String eDay) {
        return "done";
    }


    @RequestMapping(value = "/testNetty", method = RequestMethod.GET)
    public void manageGpuLable() {
        testService.conNetty();
    }
}
