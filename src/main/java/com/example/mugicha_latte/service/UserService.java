package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.UserEditForm;
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

    //ユーザー情報表示メソッド（１件）
    public UserEditForm updateUser(int id){
        User user = userRepository.findById(id).orElse(null);
        UserEditForm form = new UserEditForm();

        form.setId(user.getId());
        form.setName(user.getName());
        form.setAccount(user.getAccount());
        form.setPassword(user.getPassword());
        form.setDepartmentId(user.getDepartmentId());
        form.setBranchId(user.getBranchId());

        return form;
    }

    //バリデーションメソッド（更新用）
    public List<String> editValidate(UserEditForm form){
        List<String> errorMessages = new ArrayList<>();
        Integer id = form.getId();
        String account = form.getAccount();
        Integer branchId = form.getBranchId();
        Integer departmentId = form.getDepartmentId();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();

        //アカウント名（必須入力・６～２０文字・半角英数字か）
        if(account == null || account.trim().isEmpty()){
            errorMessages.add("アカウントを入力してください");
        }else if(!account.matches("^[a-zA-Z0-9]{6,20}$")){
            errorMessages.add("アカウントは半角英数字かつ6文字以上20文字以下で入力してください");
        }

        // アカウント重複チェック
        User duplicate = userRepository.findByAccount(form.getAccount());
        if (duplicate != null && !duplicate.getId().equals(form.getId())) {
            errorMessages.add("アカウントが既に使用されています");
        }

        //パスワードに入力があった際に半角で6～20文字以内か確認
        if ((password != null && !password.trim().isEmpty())
                &&(!password.matches("^[a-zA-Z0-9]{6,20}$"))){
            errorMessages.add("パスワードは半角英数字かつ6文字以上20文字以下で入力してください");
        }

        //パスワードと確認用パスワードが一致してるか確認
        if (password != null && confirmPassword != null && !password.equals(confirmPassword)) {
            errorMessages.add("パスワードと確認用パスワードが一致しません");
        }

        if(branchId != null && departmentId != null){
            boolean invalid = false;

            switch(branchId){
                case 1 -> invalid = (departmentId != 1 && departmentId != 2);
                case 2, 3, 4 -> invalid = (departmentId != 3 && departmentId != 4);
                default -> invalid = true;
            }

            if (invalid) {
                errorMessages.add("支社と部署の組み合わせが不正です");
            }
        }
        return errorMessages;

    }

    //ユーザー情報更新メソッド
    public void updateUser(UserEditForm form) {
        // DBから既存ユーザーを取得
        User user = userRepository.findById(form.getId()).orElse(null);

        // 共通項目を上書き
        user.setAccount(form.getAccount());
        user.setName(form.getName());
        user.setBranchId(form.getBranchId());
        user.setDepartmentId(form.getDepartmentId());

        // パスワード欄に入力がある場合のみ更新
        if (form.getPassword() != null && !form.getPassword().trim().isEmpty()) {
            user.setPassword(form.getPassword());
        }

        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);
    }
}
