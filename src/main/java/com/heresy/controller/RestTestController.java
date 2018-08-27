package com.heresy.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RestTestController {

    @RequestMapping("/getInfo")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public String getInfo(HttpServletResponse response,
                              @RequestParam(value = "type", required = false) String askedType) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        return "RestTest";
    }
}
