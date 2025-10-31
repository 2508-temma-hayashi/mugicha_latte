package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.CommentForm;
import com.example.mugicha_latte.repository.CommentRepository;
import com.example.mugicha_latte.repository.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findComment() {
        return commentRepository.findAllComments();
    }

}
