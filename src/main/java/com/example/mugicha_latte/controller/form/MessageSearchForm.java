package com.example.mugicha_latte.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
//HOME画面の入力された「絞り込み条件」を受け取るFORM
public class MessageSearchForm {
    //絞り込み開始日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    //絞り込み末日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    //カテゴリ
    private String category;
}
