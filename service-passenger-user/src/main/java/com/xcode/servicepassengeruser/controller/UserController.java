package com.xcode.servicepassengeruser.controller;


import com.xcode.dto.ResponseResult;
import com.xcode.request.VericationDto;
import com.xcode.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {



    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginorRegister(@RequestBody VericationDto vericationDto){

        String passengerPhone = vericationDto.getPassengerPhone();


        return userService.loginorRegister(passengerPhone);

    }

}
