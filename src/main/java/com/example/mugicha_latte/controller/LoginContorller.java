package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.UserForm;
import com.example.mugicha_latte.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginContorller {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ModelAndView loginContent(@ModelAttribute UserForm userForm) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginForm", userForm);
        mav.setViewName("/login");
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView loginContent(@ModelAttribute @Validated UserForm userForm,
                                     BindingResult result, HttpSession session) {
        List<String> errorMessages = new ArrayList<>();
        UserForm loginUserForm = new UserForm();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        if (errorMessages.size() >= 1) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("loginForm", userForm);
            mav.setViewName("/login");
        } else {
             loginUserForm = loginService.findUsers(userForm);
        }
        if (loginUserForm != null) {
            session.setAttribute("loginUser", loginUserForm);
            return new ModelAndView("redirect:home");
        } else {
            ModelAndView mav = new ModelAndView();
            errorMessages.add("ログインに失敗しました");
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("loginForm", userForm);
            mav.setViewName("/login");
            return mav;
        }
    }
}
