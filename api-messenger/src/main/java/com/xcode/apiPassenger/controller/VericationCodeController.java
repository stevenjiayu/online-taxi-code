package com.xcode.apiPassenger.controller;


import com.xcode.apiPassenger.request.VericationDto;
import com.xcode.apiPassenger.service.VeriCationCodeService;
import com.xcode.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VericationCodeController {

    @Autowired
    private VeriCationCodeService verificationCodeService;

    @PostMapping("/verification-code")
    public String verificationCode(@RequestBody VericationDto vericationDto){

        String passengerPhone = vericationDto.getPassengerPhone();
        System.out.println("receive"+passengerPhone);
        return verificationCodeService.generateCode(passengerPhone);

    }
}
