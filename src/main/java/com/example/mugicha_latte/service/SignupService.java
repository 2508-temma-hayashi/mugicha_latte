package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.SignupForm;
import com.example.mugicha_latte.repository.UsersRepository;
import com.example.mugicha_latte.repository.entity.User;
import com.example.mugicha_latte.utils.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignupService {
    @Autowired
    UsersRepository usersRepository;
    public boolean checkUser(SignupForm signupForm) {
        //accountの重複チェックを行う
        //重複チェックに引っかかった場合は、falseを返してControllerにエラーメッセージを入れる
        User checkUser = usersRepository.findByAccount(signupForm.getAccount());
        if (checkUser == null) {
            return true;
        } else {
            return false;
        }
    }

    public void saveUser(SignupForm signupForm) {
        User user = setUser(signupForm);
        usersRepository.save(user);
    }

    public User setUser(SignupForm signupForm) {
        User user = new User();
        user.setAccount(signupForm.getAccount());
        //パスワードはハッシュ化してからやる
        String encPassword = CipherUtil.encrypt(signupForm.getPassword());
        user.setPassword(encPassword);
        user.setName(signupForm.getName());
        user.setBranchId(signupForm.getBranchId());
        user.setDepartmentId(signupForm.getDepartmentId());
        user.setIsStopped(0);
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        return user;
    }

}
