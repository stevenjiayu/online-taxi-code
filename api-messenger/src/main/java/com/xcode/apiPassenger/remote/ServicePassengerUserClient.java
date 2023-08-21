package com.xcode.apiPassenger.remote;


import com.xcode.dto.ResponseResult;
import com.xcode.request.VericationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {


    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult loginorRegister(@RequestBody VericationDto vericationDto);

}
