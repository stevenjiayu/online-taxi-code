package com.xcode.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwtutils {


    private static final  String   SIGN = "CPFxcode!@#$%$";
    private static final  String   JWT_KEY = "passengerPhone";

    public static String generatorToken(String passengerPhone){

        Map<String,String> map =new HashMap<>();
        map.put(JWT_KEY ,passengerPhone);
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

    public static  String parseToken(String token){

        DecodedJWT verify= JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY );
        return claim.toString();
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


        String s =generatorToken("13922323");

        System.out.println(s);
        System.out.println(parseToken(s));

//        map.forEach((k,v)->
//
//                System.out.println("key:" + k + ",value:" + v));

                ;


    }

}
