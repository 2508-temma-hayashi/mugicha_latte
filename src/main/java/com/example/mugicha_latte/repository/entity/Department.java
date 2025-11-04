package com.example.mugicha_latte.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 部署名（カラム名は name ）
    @Column(name = "name")
    private String name;

    @Column(name = "created_date", insertable = false, updatable = false)
    private Timestamp createdDate;

    @Column(name = "updated_date", insertable = false, updatable = false)
    private Timestamp updatedDate;
}
