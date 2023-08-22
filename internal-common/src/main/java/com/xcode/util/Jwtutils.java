package com.xcode.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwtutils {


    private static final  String   SIGN = "CPFxcode!@#$%$";

    public static String generatorToken(Map<String,String> map){


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


    public static void main(String[] args) {

        /*
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        Date date = calendar.getTime();

        System.out.println(calendar);
         */

        Map<String,String> map =new HashMap<>();
        map.put("name","alex");
        map.put("age","18");

        String s =generatorToken(map);

        System.out.println(s);

//        map.forEach((k,v)->
//
//                System.out.println("key:" + k + ",value:" + v));

                ;


    }

}
