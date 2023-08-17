package com.xcode.apiPassenger.controller;



import com.xcode.apiPassenger.service.VeriCationCodeService;
import com.xcode.dto.ResponseResult;
import com.xcode.request.VericationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VericationCodeController {

    @Autowired
    private VeriCationCodeService verificationCodeService;

    @PostMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VericationDto vericationDto){

        String passengerPhone = vericationDto.getPassengerPhone();
        System.out.println("receive"+passengerPhone);
        return verificationCodeService.generateCode(passengerPhone);

    }

    @PostMapping("/verification-code-check")
    public ResponseResult checkverificationCode(@RequestBody VericationDto vericationDto){

        String passengerPhone = vericationDto.getPassengerPhone();
        String vericationCode = vericationDto.getVericationCode();
        System.out.println("receive"+passengerPhone+"驗證"+vericationCode);
        return verificationCodeService.checkCode(passengerPhone,vericationCode);

    }
}
