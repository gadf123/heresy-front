package com.heresy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/heresy/{smallCate}")
    public String index(@PathVariable("smallCate") String smallCate) {
        return "/home/"+smallCate;
    }

    @RequestMapping("/heresy/{largeCate}/{midCate}")
    public String index(@PathVariable("largeCate") String largeCate,
                        @PathVariable("midCate") String midCate) {
        return "/home/"+largeCate+"/"+midCate;
    }
}