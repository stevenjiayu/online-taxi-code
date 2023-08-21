package com.xcode.servicepassengeruser.service;


import com.xcode.dto.ResponseResult;
import com.xcode.request.VericationDto;
import com.xcode.servicepassengeruser.dto.PassengerUser;
import com.xcode.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.util.resources.cldr.da.LocaleNames_da;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;


    public ResponseResult loginorRegister(String passengerPhone){

        System.out.println("user 手機被調用"+passengerPhone);
        //根據手機來查詢用戶

        Map<String,Object> map =new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        System.out.println(passengerUsers.size()==0?"no record":passengerUsers.get(0).getPassengerName());

        if(passengerUsers.size() == 0){

            PassengerUser passengerUser = new PassengerUser();
            //passengerUser.setId(1992323L);
            passengerUser.setPassengerName("ky");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            passengerUserMapper.insert(passengerUser);

        }




        return ResponseResult.success();
    }



}
