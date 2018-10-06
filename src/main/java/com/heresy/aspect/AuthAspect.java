package com.heresy.aspect;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.heresy.domain.user.FirebaseUser;
import com.heresy.domain.user.User;
import com.heresy.exceptions.TokenException;
import com.heresy.exceptions.UserServiceException;
import com.heresy.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @user park
 * @date 2018. 9. 25.
 **/

@Aspect
@Component("AuthAspect")
public class AuthAspect {

    @Autowired
    UserService userService;

    @Around("@annotation(com.heresy.annotations.AuthCheck) && args(..,user)")
    public Object authCheck(ProceedingJoinPoint joinPoint, User user)  {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String idToken = httpServletRequest.getHeader("idToken");
        try{
            FirebaseToken decodeToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
            for(int i = 0; i < joinPoint.getArgs().length; i++){
                if(joinPoint.getArgs()[i].equals(user)){
                    User serverUser = userService.selectOneByUserId(decodeToken.getEmail());
                    if(serverUser == null){
                        throw new UserServiceException( "User Not Found",
                                                        "User In Token",
                                                        "Not Found");
                    }
                    joinPoint.getArgs()[i] = serverUser;
                }
                if(joinPoint.getArgs()[i].getClass().equals(FirebaseUser.class)){
                    joinPoint.getArgs()[i] = new FirebaseUser(decodeToken.getUid());
                }
            }
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            e.printStackTrace();
            throw new TokenException("Token Error", "Token Invalid");
        }
    }
}
