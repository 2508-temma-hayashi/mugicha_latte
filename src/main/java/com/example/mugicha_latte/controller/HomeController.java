package com.example.mugicha_latte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView showHome(){

        //ログインユーザーが総務人事部か確認

        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

}
