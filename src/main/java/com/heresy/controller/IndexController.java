package com.heresy.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.heresy.annotations.AuthCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @user park
 * @date 2018. 9. 7.
 **/

@Controller
@RequestMapping("/heresy")
public class IndexController {

    @RequestMapping("/{smallCate}")
    public String index(@PathVariable("smallCate") String smallCate) {
        return "/heresy/" +smallCate;
    }

    @RequestMapping("/{largeCate}/{midCate}")
    public String index(@PathVariable("largeCate") String largeCate,
                        @PathVariable("midCate") String midCate) throws FirebaseAuthException {

        return "/heresy/" +largeCate+"/"+midCate;
    }

}