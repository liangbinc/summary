package com.lbc.mo.conf;

import io.prometheus.client.exporter.MetricsServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfig {

    @Bean
    ServletRegistrationBean servletRegistrationBean() {

        return new ServletRegistrationBean(new MetricsServlet(), "/metrics");
    }
}
