package com.lbc.mo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    TestService testService;

    @Async("asyncServiceExecutor")
    public Future<String> diskBilling(String sTime, String eTime) throws InterruptedException {
        //execute current thread service

        return new AsyncResult<>("complete");
    }

    @Async("asyncServiceExecutor")
    public void initAiLabUserCache(String userName) {
        //asyn load cache
        testService.getUserInfoCache(userName);
    }


    @Async("asyncServiceExecutor")
    public Future<String> test() {
        try {
            Thread.sleep(3000);
            LOG.info("11111111111111111");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("complete");
    }
}
