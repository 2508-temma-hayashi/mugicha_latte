package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.CommentForm;
import com.example.mugicha_latte.controller.form.MessageForm;
import com.example.mugicha_latte.controller.form.MessageSearchForm;
import com.example.mugicha_latte.repository.entity.Comment;
import com.example.mugicha_latte.repository.entity.User;
import com.example.mugicha_latte.service.CommentService;
import com.example.mugicha_latte.service.MessageService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    MessageService messageService;
    @Autowired
    CommentService commentService;
    @GetMapping("/home")
    public ModelAndView showHome(@ModelAttribute MessageSearchForm form, HttpSession session){

        //★ログインユーザーが総務人事部か確認
        User user = (User) session.getAttribute("loginUser");
        String buttonFlag = "OFF";
        if(user.getDepartmentId() == 1 && user.getBranchId() == 1){
            buttonFlag = "ON";
        }


        //★開始日（LocalDate型）終了日（LocalDate型）とカテゴリの入ったformを渡して投稿取得するメソッド起動
        List<MessageForm> messageFormList = messageService.findAllMessage(form);

        //コメント取得
        List<Comment> commentList = commentService.findComment();

        //★HTMLに渡す
        ModelAndView mav = new ModelAndView("home");
        //ログインユーザーを入れる
        mav.addObject("loginUser", user);
        //投稿をmodelに詰める
        mav.addObject("messageFormList", messageFormList);
        //コメント入力欄に空欄を渡す
        mav.addObject("commentForm", new CommentForm());
        //コメントをhtmlに渡す
        mav.addObject("commentList", commentList);
        //絞り込み入力値保持のためformもそのまま渡しておく
        mav.addObject("messageSearchForm", form);
        //本社所属の総務人事部ならON、そうでないならOFFの文字列も判別のために送る
        mav.addObject("buttonFlag", buttonFlag);
        return mav;
    }

}