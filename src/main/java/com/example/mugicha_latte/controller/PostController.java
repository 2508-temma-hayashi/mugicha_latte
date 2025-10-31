package com.example.mugicha_latte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/post")
    public ModelAndView postContent() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/post");
        return mav;
    }
}
