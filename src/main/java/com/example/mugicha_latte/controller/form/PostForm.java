package com.example.mugicha_latte.controller.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {
    //投稿処理用のFORM

    //タイトル
    @NotBlank(message = "件名を入力してください")
    @Size(max = 30, message = "件名は30文字以内で入力してください")
    private String title;

    //テキスト
    @NotBlank(message = "本文を入力してください")
    @Size(max = 1000, message = "本文は1000文字以内で入力してください")
    private String text;

    //カテゴリ
    @NotBlank(message = "カテゴリを入力してください")
    @Size(max = 10, message = "カテゴリは10文字以内で入力してください")
    private String category;

    //全角チェック用のメソッド
    @AssertTrue(message = "件名を入力してください")
    public boolean isTItleValidated() {
        if (title.equals("　")) {
            return false;
        } else {
            return true;
        }
    }
    @AssertTrue(message = "本文を入力してください")
    public boolean isTextValidated() {
        if (text.equals("　")) {
            return false;
        } else {
            return true;
        }
    }
    @AssertTrue(message = "カテゴリを入力してください")
    public boolean isCategoryValidated() {
        if (category.equals("　")) {
            return false;
        } else {
            return true;
        }
    }

}
