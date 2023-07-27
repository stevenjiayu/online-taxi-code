package com.xcode.apiPassenger.service;


import com.xcode.apiPassenger.remote.ServiceVericationCodeClient;
import com.xcode.dto.ResponseResult;
import com.xcode.response.NumberCodeResponse;
import com.xcode.response.TokenResponse;
import com.xcode.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class VeriCationCodeService {

    @Autowired
    private ServiceVericationCodeClient serviceVCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private String vericationCodePrefix = "passenger-vericaton-code";

    public String generateCode(String passengerPhone){


      ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVCodeClient.getNumberCode(6);

      int numberCode = numberCodeResponse.getData().getNumberCode();

        // 存入redis
        // key,value,过期时间

        String key = vericationCodePrefix+ passengerPhone;
        //String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone,IdentityConstants.PASSENGER_IDENTITY) ;
        // 存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        System.out.println("api-messne"+numberCode);

        JSONObject result =new JSONObject();
        result.put("code",1);
        result.put("message","success");


        return result.toString();
    }

    public ResponseResult checkCode(String passengerPhone,String vericationCode){


        TokenResponse tokenResponse =new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);

    }



}
