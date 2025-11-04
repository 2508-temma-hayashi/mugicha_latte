package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.CommentForm;
import com.example.mugicha_latte.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    //コメント投稿機能
    @PostMapping("/comment")
    public ModelAndView postComment(@ModelAttribute @Validated CommentForm commentForm,
                                    BindingResult result,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes){

        //アノテーションにひっかかったエラーを表示する
        List<String> errorMessages = getErrorMessages(result);
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("errorMessages",errorMessages);
            redirectAttributes.addFlashAttribute("commentForm", commentForm);
            return new ModelAndView("redirect:/home");
        }

        //Commentクラスの保存するメソッドを起動
        commentService.saveComment(commentForm,session);
        return new ModelAndView("redirect:/home");
    }

    //エラー取得メソッド（汎用）
    public static List<String> getErrorMessages(BindingResult result){
        List<String> errorMessages = new ArrayList<>();
        if(result.hasErrors()){
            for(FieldError error : result.getFieldErrors()){
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return errorMessages;
    }
}
