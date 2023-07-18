package com.xcode.servicevericationcode.controller;


import com.xcode.dto.ResponseResult;
import com.xcode.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    /**
     *
     * @param size
     * @return
     */
    @GetMapping("/numbercode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){

        /**
         * (Math.random()*9+1) 亂數＊9要得到整數數字，+1是要防止＊9還是得到0
         * Math.pow(10,size-1 建立10的次方
         */

        double mathRandom =  (Math.random()*9+1) * (Math.pow(10,size-1));
        Integer result = (int)mathRandom;

        System.out.println("src-service-messen"+result);

        NumberCodeResponse response =new NumberCodeResponse();
        response.setNumberCode(result);

        return ResponseResult.success(response);
    }

}
