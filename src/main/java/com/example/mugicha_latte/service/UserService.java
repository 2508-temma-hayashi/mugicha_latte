package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.UserForm;
import com.example.mugicha_latte.repository.UserRepository;
import com.example.mugicha_latte.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //ユーザー情報全件取得メソッド
    public List<UserForm> getAllUser(){
        List<User> userList = userRepository.findAllUsersWithBranchAndDepartment();
        ArrayList<UserForm> UserFormList = new ArrayList<>();

        //FORMに詰め替える
        for(User u : userList){
            UserForm form = new UserForm();
            form.setId(u.getId());
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

    //ユーザー情報一件取得メソッド
    public User getUser(int id){
        //何もなかった場合nullを返す
        return userRepository.findById(id).orElse(null);
    }

    //ユーザー状態更新メソッド
    public void update(int id){
        User user = getUser(id);
        //nullが返ってくる可能性があるので、その場合に動作しないようにする。
        if(user != null){
            int newStatus = (user.getIsStopped() == 1) ? 0 : 1 ;
            user.setIsStopped(newStatus);
            user.setUpdatedDate(LocalDateTime.now());
            userRepository.save(user);
        }
    }

    //ユーザー情報編集（更新メソッド）
    public UserForm updateUser(int id){
        User user = userRepository.findById(id).orElse(null);
        UserForm form = new UserForm();

        form.setId(user.getId());
        form.setName(user.getName());
        form.setAccount(user.getAccount());
        form.setPassword(user.getPassword());
        form.setDepartmentId(user.getDepartmentId());
        form.setBranchId(user.getBranchId());

        return form;
    }

}
