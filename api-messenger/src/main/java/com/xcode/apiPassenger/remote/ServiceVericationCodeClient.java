package com.xcode.apiPassenger.remote;

import com.xcode.dto.ResponseResult;
import com.xcode.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-vericationcode")
public interface ServiceVericationCodeClient {

    @RequestMapping(method = RequestMethod.GET,value = "/numbercode/{size}")
    ResponseResult <NumberCodeResponse> getNumberCode(@PathVariable("size") int size);

}
