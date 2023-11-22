package com.cb.consul.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "book-service")
public interface StockFeighService {
    @RequestMapping("/hello")
    String reduce();
}


/**
 * @RequestMapping("/stock") public class StockController {
 * @RequestMapping("/reduce") public String reduce(){
 * System.out.println("扣减库存");
 * return "扣减库存";
 * }
 * }
 */