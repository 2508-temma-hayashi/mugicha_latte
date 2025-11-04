package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.PostForm;
import com.example.mugicha_latte.repository.MessageRepository;
import com.example.mugicha_latte.repository.entity.Message;
import com.example.mugicha_latte.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    MessageRepository messageRepository;

    public void savePost(PostForm postForm, int userId) {
        Message message = setMessage(postForm, userId);
        messageRepository.save(message);
    }
    //
    private Message setMessage(PostForm postForm, int userId) {
        User user = new User();
        user.setId(userId);
        Message message = new Message();
        message.setTitle(postForm.getTitle());
        message.setText(postForm.getText());
        message.setCategory(postForm.getCategory());
        message.setUser(user);
        message.setCreatedDate(LocalDateTime.now());
        message.setUpdatedDate(LocalDateTime.now());
        return message;
    }
}
