package com.xcode.apiPassenger.service;



import com.xcode.apiPassenger.remote.ServicePassengerUserClient;
import com.xcode.apiPassenger.remote.ServiceVericationCodeClient;
import com.xcode.constant.CommonStatusEnum;
import com.xcode.constant.IdentityConstants;
import com.xcode.dto.ResponseResult;
import com.xcode.request.VericationDto;
import com.xcode.response.NumberCodeResponse;
import com.xcode.response.TokenResponse;

import com.xcode.util.Jwtutils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class VeriCationCodeService {

    @Autowired
    private ServiceVericationCodeClient serviceVCodeClient;


    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private String vericationCodePrefix = "passenger-vericaton-code";

    public ResponseResult generateCode(String passengerPhone){


      ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVCodeClient.getNumberCode(6);

      int numberCode = numberCodeResponse.getData().getNumberCode();

        // 存入redis
        // key,value,过期时间

        String key = generatekeyByPhone(passengerPhone);
        //String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone,IdentityConstants.PASSENGER_IDENTITY) ;
        // 存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);


        //無參數回傳操作
        return ResponseResult.success();
    }


    private String generatekeyByPhone(String passengerPhone){

        return   vericationCodePrefix+ passengerPhone;
    }

    public ResponseResult checkCode(String passengerPhone,String vericationCode){


        String key = generatekeyByPhone(passengerPhone);
        String codeRedis =stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis value :" +codeRedis);

        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        if (!vericationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        //遠程服務調用

        VericationDto vericationDto =new VericationDto();
        vericationDto.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginorRegister(vericationDto);

        String token = Jwtutils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);

        TokenResponse tokenResponse =new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);

    }



}
