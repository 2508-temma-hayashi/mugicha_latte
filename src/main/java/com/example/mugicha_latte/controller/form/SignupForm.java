package com.example.mugicha_latte.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {
    private String account;
    private String password;
    private String passwordConfirm;
    private String name;
    private Integer branchId;
    private Integer departmentId;
}
