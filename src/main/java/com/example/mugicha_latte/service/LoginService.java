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
    public UserForm findUsers(UserForm userForm) {
        //loginUsersFormのユーザー情報、ユーザーが止まってるかどうかをここで確認する
        String encPassword = CipherUtil.encrypt(userForm.getPassword());
        userForm.setPassword(encPassword);
        List<User> userList = usersRepository.findByAccountAndPassword(userForm.getAccount(), userForm.getPassword());
        if (checkUsers(userList)) {
            User user = userList.get(0);
            return setUsersForm(user);
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

    public UserForm setUsersForm(User user) {
        UserForm userForm = new UserForm();
        userForm.setId(user.getId());
        userForm.setAccount(user.getAccount());
        userForm.setName(user.getName());
        userForm.setPassword(user.getPassword());
        userForm.setBranchId(user.getBranchId());
        userForm.setCreatedDate(user.getCreatedDate());
        userForm.setUpdatedDate(user.getUpdatedDate());
        userForm.setIsStopped(user.getIsStopped());
        return userForm;
    }

}
