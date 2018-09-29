package com.heresy.controller;

import com.heresy.domain.user.User;
import com.heresy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @user park
 * @date 2018. 9. 7.
 **/

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(value="/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public String signup(HttpServletResponse response,
                              @RequestBody User user) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        user.setAuthSnsId("1");
        user.setExperience(1);
        user.setIntroduction("1");
        user.setTendency(1);

        return userService.insert(user) == 1? "success" : "fail";
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public User userInfo(HttpServletResponse response,
                          @RequestParam(value = "userId") String userId)
    {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //String decodedUserId = URLDecoder.decode(userId, "UTF-8");
        User user = userService.selectOne(userId);

        return user;
    }
}

