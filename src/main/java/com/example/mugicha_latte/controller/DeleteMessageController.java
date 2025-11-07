package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.repository.entity.User;
import com.example.mugicha_latte.service.DeleteMessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeleteMessageController {

    @Autowired
    DeleteMessageService deleteMessageService;
    @PostMapping("post/delete")
    public ModelAndView deleteMessageContent(@RequestParam("id") int messageId,
                                             HttpSession session,
                                             RedirectAttributes redirectAttributes) {
        //投稿ユーザーとログインユーザーが一致しているか確認
        List<String> errorMessages = new ArrayList<>();
        User user = (User) session.getAttribute("loginUser");


        if (!deleteMessageService.deleteMessage(messageId, user)) {
            errorMessages.add("無効なアクセスです");
        }

        if (errorMessages.size() >= 1) {
            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("redirect:/");
        }
    }
}
