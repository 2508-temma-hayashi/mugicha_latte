package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

    @GetMapping("signup")
    public ModelAndView signupContent(@ModelAttribute SignupForm signupForm) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("signupModel", signupForm);
        mav.setViewName("signup");
        return mav;
    }
}
