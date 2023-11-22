package com.cb.consul.client.controller;

import com.cb.consul.client.feign.StockFeighService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceController {
 
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StockFeighService feighService;

//    private static final String SERVICE_NAME = "book-iot-api";
    private static final String SERVICE_NAME = "book-service";

   /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances(SERVICE_NAME);
    }
 
    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose(SERVICE_NAME).getUri().toString();
    }

    @RequestMapping("/call")
    public String call() {
        ServiceInstance serviceInstance = loadBalancer.choose(SERVICE_NAME);
//        System.out.println("服务地址：" + serviceInstance.getUri());
//        System.out.println("服务名称：" + serviceInstance.getServiceId());

//        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
//        String callServiceResult = restTemplate.getForObject("http://" +SERVICE_NAME+ "/hello", String.class);
        String callServiceResult = feighService.reduce();
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}