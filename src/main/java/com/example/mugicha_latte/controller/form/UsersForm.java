package com.example.mugicha_latte.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsersForm {
    private int id;
    private String account;
    private String password;
    private String name;
    private int branchId;
    private int departmentId;
    private int isStopped;
    private LocalDateTime CreatedDate;
    private LocalDateTime UpdatedDate;
}
