package com.xcode.apiPassenger.service;


import com.xcode.apiPassenger.remote.ServiceVericationCodeClient;
import com.xcode.dto.ResponseResult;
import com.xcode.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeriCationCodeService {

    @Autowired
    private ServiceVericationCodeClient serviceVCodeClient;

    public String generateCode(String passengerPhone){


      ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVCodeClient.getNumberCode(6);

      int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("api-messne"+numberCode);

        JSONObject result =new JSONObject();
        result.put("code",1);
        result.put("message","success");


        return result.toString();
    }



}
