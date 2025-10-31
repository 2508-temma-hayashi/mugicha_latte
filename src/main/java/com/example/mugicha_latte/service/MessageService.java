package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.MessageForm;
import com.example.mugicha_latte.repository.MessageRepository;
import com.example.mugicha_latte.repository.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<MessageForm> findAllMessage(){

        //投稿全件取得メソッドを定義
        List<Message> messageList = messageRepository.findALLAMessage();
        List<MessageForm> formList= new ArrayList<>();

        //MessagesをMessagesFormに詰め替えてる
        for(Message m : messageList){
            MessageForm f = new MessageForm();
            f.setId(m.getId());
            f.setTitle(m.getTitle());
            f.setCategory(m.getCategory());
            f.setText(m.getText());
            f.setCreatedDate(m.getCreatedDate());
            f.setAccount(m.getUser().getName());
            formList.add(f);
        }
        return formList;

    }

}
