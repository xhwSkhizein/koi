package com.hongv.koi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hongweixu
 * @since 2018/10/25 01:07
 */
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String hello() {
        List<String> services = discoveryClient.getServices();

        String result = "";
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            result += StringUtils.join(instances.stream().map(e -> e.getHost() + ":" + e.getPort() + "  " + e.getScheme() + "   " + e.getServiceId() + "   " + e.getUri()).collect(Collectors.toList()), ",");
        }
        return StringUtils.join(services, ",") + "\n" + result;
    }
}
