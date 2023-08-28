package com.xcode.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xcode.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwtutils {


    private static final  String   SIGN = "CPFxcode!@#$%$";
    private static final  String   JWT_KEY_phone = "phone";
    private static final  String   JWT_KEY_IDENTITY = "identity";

    public static String generatorToken(String passengerPhone,String identity){

        Map<String,String> map =new HashMap<>();
        map.put(JWT_KEY_phone ,passengerPhone);
        map.put(JWT_KEY_IDENTITY ,identity);



        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();

        map.forEach(
                (k,v)->{
                    builder.withClaim(k,v);
                }
        );

        builder.withExpiresAt(date);

        String sign = builder.sign(Algorithm.HMAC256(SIGN));



        return sign;
    }

    public static  TokenResult parseToken(String token){

        DecodedJWT verify= JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_phone).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();

        TokenResult tokenResult =new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);

        return tokenResult;
    }


    public static void main(String[] args) {

        /*
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        Date date = calendar.getTime();

        System.out.println(calendar);
         */

//        Map<String,String> map =new HashMap<>();
//        map.put("name","alex");
//        map.put("age","18");


        String s =generatorToken("13922323","1");

        System.out.println(s);
        System.out.println(parseToken(s));

//        map.forEach((k,v)->
//
//                System.out.println("key:" + k + ",value:" + v));

                ;


    }

}
