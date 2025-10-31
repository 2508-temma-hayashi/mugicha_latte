package com.example.mugicha_latte.repository;

import com.example.mugicha_latte.repository.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//<>内でどのEntityを使うのか。そのEntityの主キーは何なのかを指定
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("""
            SELECT m FROM Message m
            JOIN FETCH m.user
            ORDER BY m.createdDate DESC""")

        //上のクエリを動かすメソッド
    List<Message> findALLAMessage();

}
