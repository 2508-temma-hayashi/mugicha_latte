package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.UserForm;
import com.example.mugicha_latte.repository.UsersRepository;
import com.example.mugicha_latte.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    public List<UserForm> getAllUser(){
        List<User> userList = usersRepository.findAllUsersWithBranchAndDepartment();
        ArrayList<UserForm> UserFormList = new ArrayList<>();

        //FORMに詰め替える
        for(User u : userList){
            UserForm form = new UserForm();
            form.setAccount(u.getAccount());
            form.setName(u.getName());
            form.setBranchName(u.getBranch().getName());
            form.setDepartmentName(u.getDepartment().getName());
            form.setIsStopped(u.getIsStopped());
            //一応部署IDと支店IDも送っておく
            form.setBranchId(u.getBranchId());
            form.setDepartmentId(u.getDepartmentId());
            UserFormList.add(form);
        }
        return UserFormList;

    }

}
