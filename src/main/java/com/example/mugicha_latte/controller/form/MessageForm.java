package com.example.mugicha_latte.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
//HOME画面に渡し表示するためのFORM
public class MessageForm {

    //ID
    private Integer id;

    //件名
    private String title;

    //カテゴリ
    private String category;

    //本文
    private String text;

    //投稿日時
    private LocalDateTime createdDate;

    //投稿者（氏名）usersテーブルとJOINしてる。
    private String account;

    private int userId;

}
