package com.heresy.controller;

import com.heresy.domain.user.User;
import com.heresy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/usertest")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public String userInfo(HttpServletResponse response,
                          @RequestParam(value = "type", required = false) String askedType) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        return "RestTest";
    }
}

