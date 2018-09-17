package com.heresy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/heresy")
public class IndexController {

    @RequestMapping("/{smallCate}")
    public String index(@PathVariable("smallCate") String smallCate) {
        return "/heresy/" +smallCate;
    }

    @RequestMapping("/{largeCate}/{midCate}")
    public String index(@PathVariable("largeCate") String largeCate,
                        @PathVariable("midCate") String midCate) {
        return "/heresy/" +largeCate+"/"+midCate;
    }

}