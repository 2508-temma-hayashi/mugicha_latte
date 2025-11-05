package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.SignupForm;
import com.example.mugicha_latte.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SignupController {

    @Autowired
    SignupService signupService;
    @GetMapping("signup")
    public ModelAndView getSignupContent(@ModelAttribute SignupForm signupForm) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("signupModel", signupForm);
        mav.setViewName("signup");
        return mav;
    }

    @PostMapping("signup")
    public ModelAndView postSignupContent(@ModelAttribute @Validated SignupForm signupForm,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes) {
        List<String> errorMessages = new ArrayList<>();
        //バリデーションチェック
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        //アカウントの重複チェックはDBアクセスする必要があるため、フォームのDtoではなくServiceで確認する
        if (!signupService.checkUser(signupForm)) {
            errorMessages.add("アカウントが重複しています");
        }
        //エラーメッセージがあるとき
        if (errorMessages.size() >= 1) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("signupModel", signupForm);
            return mav;
        } else {
            signupService.saveUser(signupForm);
            return new ModelAndView("redirect:user/list");
        }
    }
}
