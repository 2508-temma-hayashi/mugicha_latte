package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.PostForm;
import com.example.mugicha_latte.service.PostService;
import com.example.mugicha_latte.repository.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post")
    public ModelAndView postContent(@ModelAttribute PostForm postForm) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("postModel", postForm);
        mav.setViewName("/post");
        return mav;
    }

    @PostMapping("/post")
    public ModelAndView postMessageContent(@ModelAttribute @Validated
                                           PostForm postForm, BindingResult result,
                                           RedirectAttributes redirectAttributes,
                                           HttpSession session) {

        List<String> errorMessages = new ArrayList<>();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        if (errorMessages.size() >= 1) {
            redirectAttributes.addFlashAttribute("postModel", postForm);
            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            return new ModelAndView("redirect:post");
        }
        else {
            User user = (User) session.getAttribute("loginUser");
            postService.savePost(postForm, user.getId());
            return new ModelAndView("redirect:home");
        }
    }
}
