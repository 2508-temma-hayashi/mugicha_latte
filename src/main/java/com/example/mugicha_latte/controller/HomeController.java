package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.MessageForm;
import com.example.mugicha_latte.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    MessageService messageService;

    @GetMapping("/home")
    public ModelAndView showHome(HttpSession session){

        //ログインユーザーが総務人事部か確認(ログイン完成次第作成)


        //投稿取得
        List<MessageForm> messageFormList = messageService.findAllMessage();


        ModelAndView mav = new ModelAndView("home");
        //投稿をmodelに詰める
        mav.addObject("messageFormList", messageFormList);
        return mav;
    }

}
