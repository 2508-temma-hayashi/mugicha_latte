package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.UserForm;
import com.example.mugicha_latte.repository.UsersRepository;
import com.example.mugicha_latte.repository.entity.User;
import com.example.mugicha_latte.utils.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    UsersRepository usersRepository;
    public User findUsers(UserForm userForm) {
        //loginUsersFormのユーザー情報、ユーザーが止まってるかどうかをここで確認する
        String encPassword = CipherUtil.encrypt(userForm.getPassword());
        userForm.setPassword(encPassword);
        List<User> userList = usersRepository.findByAccountAndPassword(userForm.getAccount(), userForm.getPassword());
        if (checkUsers(userList)) {
            User user = userList.get(0);
            return user;
        } else {
            return null;
        }
    }

    public boolean checkUsers(List<User> userList) {
        if (userList.isEmpty()) {
            return false;
        }
        else if (userList.get(0).getIsStopped() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
