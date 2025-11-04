package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.CommentForm;
import com.example.mugicha_latte.repository.CommentRepository;
import com.example.mugicha_latte.repository.entity.Comment;
import com.example.mugicha_latte.repository.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findComment() {
        return commentRepository.findAllComments();
    }

    //コメント投稿
    public void saveComment(CommentForm form, HttpSession session){
        //セッションからログインユーザーの情報（entity）を取得。コメント者IDが欲しいので
        //HTMLから受け取らないのはセキュリティのため。
        User user = (User) session.getAttribute("loginUser");
        int userId = user.getId();

        //commentオブジェクトに情報を詰めて保存
        Comment comment = new Comment();
        comment.setText(form.getText());
        comment.setUserId(userId);
        comment.setMessageId(form.getMessageId());
        comment.setCreatedDate(LocalDateTime.now());
        comment.setUpdatedDate(LocalDateTime.now());
        commentRepository.save(comment);

    }

    //コメント削除
    public void deleteComment(int id){
        commentRepository.deleteById(id);
    }

}
