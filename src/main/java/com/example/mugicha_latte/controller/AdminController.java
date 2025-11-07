package com.example.mugicha_latte.controller;

import com.example.mugicha_latte.controller.form.UserEditForm;
import com.example.mugicha_latte.controller.form.UserForm;
import com.example.mugicha_latte.repository.entity.User;
import com.example.mugicha_latte.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.example.mugicha_latte.controller.CommentController.getErrorMessages;

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

    //ユーザーステータス更新
    @PostMapping("/user/update")
    public String updateStatus(@RequestParam("id") int id){
        userService.update(id);
        return "redirect:/user/list";
    }

    //ユーザー編集画面表示
    @GetMapping("/user/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") String id,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();


        //IDが数字でないとき
        if(!id.matches("\\d+")){
            redirectAttributes.addFlashAttribute("errorMessages", "不正なパラメータが入力されました");
            mav.setViewName("redirect:/user/list");
            return mav;
        }
        int userId = Integer.parseInt(id);

        User user = new User();
        user = userService.getUser(userId);

        //存在しない数字の時
        if(user == null){
            redirectAttributes.addFlashAttribute("errorMessages", "不正なパラメータが入力されました");
            mav.setViewName("redirect:/user/list");
            return mav;
        }

        mav.setViewName("edit");
        User loginUser = (User) session.getAttribute("loginUser");
        UserEditForm userEditForm = userService.updateUser(userId);
        mav.addObject("userEditForm", userEditForm);
        mav.addObject("loginUser", loginUser);
        return mav;
    }

    //URLにIDが含まれていない場合のエラー処理
    @GetMapping("/user/edit/")
    public ModelAndView invalidEditAccess(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessages", "不正なパラメータが入力されました");
        return new ModelAndView("redirect:/user/list");
    }

    //ユーザー編集処理
    @PostMapping("/user/editInfo/{id}")
    public ModelAndView userUpdate(@PathVariable("id") int id, @ModelAttribute @Validated UserEditForm form, BindingResult result, HttpSession session){
        ModelAndView mav = new ModelAndView("edit");

        // ① Formの中にあったエラー取得
        List<String> errorMessages = new ArrayList<>();
        if (result.hasErrors()) {
            errorMessages.addAll(getErrorMessages(result));
        }

        // ② サービスのバリデーション由来のエラー取得
        List<String> customErrors = userService.editValidate(form);
        if (!customErrors.isEmpty()) {
            errorMessages.addAll(customErrors);
        }

        // ③ まとめたリストにエラーがあればまとめて表示
        if (!errorMessages.isEmpty()) {
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("userEditForm", form); // 入力保持
            mav.addObject("loginUser", session.getAttribute("loginUser"));
            return mav;
        }
        form.setId(id); //一応書いておく。
        //エラーがなければ入力内容をServiceに渡してDBに保存☞そのあとリダイレクトでユーザー管理画面表示
        userService.updateUser(form);
        return new ModelAndView("redirect:/user/list");
    }
}
