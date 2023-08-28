package com.xcode.apiPassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xcode.dto.ResponseResult;
import com.xcode.util.Jwtutils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

public class JwtInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";

        String token = request.getHeader("Authorization");

        try{
            Jwtutils.parseToken(token);
        }catch (SignatureVerificationException e){
            resultString = "token  sign error";
            result=false;
        }catch (TokenExpiredException e){
            resultString = "token time expire";
            result=false;
        }catch (AlgorithmMismatchException e){
            resultString = "token algorithm error";
            result=false;
        }catch (Exception e){
            resultString = "token invalid";
            result=false;
        }

        if(!result){

            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }



        return true;
    }
}
