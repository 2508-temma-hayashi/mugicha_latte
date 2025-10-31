package com.example.mugicha_latte.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="messages")
@Getter
@Setter
public class Message {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //件名
    @Column(name = "title", length = 30, nullable = false)
    private String title;

    //本文
    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    //カテゴリ
    @Column(name = "category", length = 10, nullable = false)
    private String category;

    //ユーザー情報
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //投稿日
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    //更新日
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
