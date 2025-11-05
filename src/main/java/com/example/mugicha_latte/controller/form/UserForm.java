package com.example.mugicha_latte.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserForm {
    private int id;

    @NotBlank(message="アカウントを入力してください")
    private String account;

    @NotBlank(message="パスワードを入力してください")
    private String password;
    private String name;
    private int branchId;
    private int departmentId;
    private int isStopped;
    private LocalDateTime CreatedDate;
    private LocalDateTime UpdatedDate;
    //ユーザー管理画面表示で使うため追加
    private String branchName;
    private String departmentName;
}
