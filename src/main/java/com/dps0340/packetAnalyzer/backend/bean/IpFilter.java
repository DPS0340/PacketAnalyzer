package com.dps0340.packetAnalyzer.backend.bean;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class IpFilter {

    @Bean
    public FilterRegistrationBean remoteAddressFilter() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        RemoteAddrFilter filter = new RemoteAddrFilter();

        filter.setAllow("127.0.0.1");
        filter.setAllow("localhost");
        filter.setAllow("0:0:0:0:0:0:0:1");
        filter.setDenyStatus(404);

        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
