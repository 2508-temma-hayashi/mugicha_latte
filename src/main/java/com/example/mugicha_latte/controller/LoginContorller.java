package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.UsersForm;
//import com.example.mugicha_latte.controller.utils.CloseableUtil.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginContorller {

    @GetMapping("/login")
    public ModelAndView loginContent(@ModelAttribute UsersForm usersForm) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginForm", usersForm);
        mav.setViewName("/login");
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView loginContent(@ModelAttribute @Validated UsersForm usersForm,
                                     BindingResult results) {
        return null;
    }
}
