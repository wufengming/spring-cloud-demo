package com.wfm.serviceb.ServiceClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "SERVICE-OBJCAT-A")
public interface ServiceAFeignClient {
    @RequestMapping("/hello")
    String hello();
}
