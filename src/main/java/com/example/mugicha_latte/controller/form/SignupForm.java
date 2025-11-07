package com.example.mugicha_latte.controller.form;

import com.example.mugicha_latte.repository.UserRepository;
import com.example.mugicha_latte.repository.entity.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class SignupForm {

    @NotBlank(message = "アカウントを入力してください")
    @Pattern(
            regexp = "^$|^[a-zA-Z0-9]{6,20}$",
            message = "アカウントは半角英数字かつ6文字以上20文字以下で入力してください"
    )
    private String account;

    @NotBlank(message = "パスワードを入力してください")
    @Pattern(
            regexp = "^$|^[a-zA-Z0-9]{6,20}$",
            message = "パスワードは半角文字かつ6文字以上20文字以下で入力してください"
    )
    private String password;

    //確認用パスワードはisPasswordMatching()で判定している
    private String passwordConfirm;

    @NotBlank(message = "氏名を入力してください")
    @Size(max = 10, message = "氏名は10文字以下で入力してください")
    private String name;

    //Integer型はNotNullを使用
    @NotNull(message = "支社を選択してください")
    private Integer branchId;

    @NotNull(message = "部署を選択してください")
    private Integer departmentId;

    //パスワード確認
    @AssertTrue(message="パスワードと確認用パスワードが一致しません")
    public boolean isPasswordMatching() {
        if (password == null || passwordConfirm == null) {
            return true; // 未入力時のバリデーションは他で行う
        }
        return password.equals(passwordConfirm);
    }

    //部署チェック
    @AssertFalse(message = "支社と部署の組み合わせが不正です")
    private boolean isInvalidDepartment() {
        if (branchId == null || departmentId == null) {
            return false;
        }
        switch (branchId) {
            case 1 -> {
                return (departmentId != 1 && departmentId != 2);
            }
            case 2, 3, 4 -> {
                return (departmentId != 3 && departmentId != 4);
            }
            default -> {
                return true;
            }
        }
    }

}
