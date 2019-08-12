package com.lbc.mo;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@EnablePrometheusEndpoint
public class Application {

    protected Application() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
