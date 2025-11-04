package com.example.mugicha_latte.service;

import com.example.mugicha_latte.repository.MessageRepository;
import com.example.mugicha_latte.repository.entity.Message;
import com.example.mugicha_latte.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteMessageService {
    @Autowired
    MessageRepository messageRepository;

    public boolean deleteMessage(int messageId, User user) {
        //メッセージIDをもとにmessageのentityを取り出す
        List<Message> messageList = new ArrayList<>();
        messageList.add(messageRepository.findById(messageId).orElse(null));
        Message message = messageList.get(0);

        //messageのユーザーIDとUserのIdが一致してるか確認
        if (message.getUser().getId() == user.getId()) {
            messageRepository.deleteById(messageId);
            return true;
        } else {
            return false;
        }
    }
}
