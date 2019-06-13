package com.lbc.mo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MonitorController {
    private static final Log LOG = LogFactory.getLog(MonitorController.class);


    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    public String manageGpuLable(@RequestParam String sDay, @RequestParam String eDay, @RequestParam String v100TOP) {
        return "done";
    }

}
