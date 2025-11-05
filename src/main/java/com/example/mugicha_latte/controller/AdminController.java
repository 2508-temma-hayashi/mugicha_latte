package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.UserForm;
import com.example.mugicha_latte.repository.entity.User;
import com.example.mugicha_latte.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    //ユーザー一覧表示
    @GetMapping("/user/list")
    public ModelAndView showAllUsers(HttpSession session){
        ModelAndView mav = new ModelAndView("admin");

        User loginUser = (User) session.getAttribute("loginUser");
        //ユーザー情報取得。FORMでかえってくる。
        List<UserForm> userFormList = userService.getAllUser();
        mav.addObject("userFormList", userFormList);
        mav.addObject("loginUser", loginUser);
        return mav;
    }
}
