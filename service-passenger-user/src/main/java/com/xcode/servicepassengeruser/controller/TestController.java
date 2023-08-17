package com.xcode.servicepassengeruser.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping
    public String getcode(){


        return "service-user-passenger";

    }
}
