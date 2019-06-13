package com.lbc.mo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
public class Application {
    private static final Log LOG = LogFactory.getLog(Application.class);

    protected Application() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
