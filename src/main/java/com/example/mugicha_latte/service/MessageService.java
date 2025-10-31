package com.example.mugicha_latte.service;

import com.example.mugicha_latte.controller.form.MessageForm;
import com.example.mugicha_latte.controller.form.MessageSearchForm;
import com.example.mugicha_latte.repository.MessageRepository;
import com.example.mugicha_latte.repository.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    //投稿全件取得メソッドを定義
    public List<MessageForm> findAllMessage(MessageSearchForm form){


        //★絞り込み受け取り値初期化
        //受け取った開始日に中身があればその日の０時を、なければデフォルト値を入れてLocalDateTime型にする
        LocalDateTime startDateTime = form.getStartDate() != null
                ? form.getStartDate().atStartOfDay()
                : LocalDateTime .of(2022, 1, 1, 0, 0, 0);

        //受け取った終了日に中身があればその日の夜中を、なければ今の時間を入れてLocalDateTime型にする
        LocalDateTime endDateTime = form.getEndDate() != null
                ? form.getEndDate().atTime( 23, 59, 59)
                : LocalDateTime.now();

        //受け取ったカテゴリーの中身がNULLじゃないかつ空欄でもない場合は、代入。
        //==nullで書くと右側でエラーが出る。
        String category = (form.getCategory() != null && !form.getCategory().isBlank())
                ? form.getCategory()
                : null;


        //★投稿全件取得メソッド(Repository版)を実行
        List<Message> messageList = messageRepository.findALLAMessage(startDateTime, endDateTime, category);
        List<MessageForm> formList= new ArrayList<>();

        //★MessagesをMessagesFormに詰め替えてる
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
