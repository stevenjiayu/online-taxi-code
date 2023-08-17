package com.xcode.servicepassengeruser.service;


import com.xcode.dto.ResponseResult;
import com.xcode.request.VericationDto;
import com.xcode.servicepassengeruser.dto.PassengerUser;
import com.xcode.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;


    public ResponseResult loginorRegister(String passengerPhone){


        //根據手機來查詢用戶

        Map<String,Object> map =new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        System.out.println(passengerUsers==null?"no record":passengerUsers.get(0).getPassengerName());



        return ResponseResult.success();
    }



}
