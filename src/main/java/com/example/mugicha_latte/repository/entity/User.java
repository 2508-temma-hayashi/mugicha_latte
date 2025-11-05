package com.example.mugicha_latte.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String account;

    @Column
    private String password;

    @Column
    private String name;

    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "is_stopped")
    private int isStopped;

    @Column(name ="created_date")
    private LocalDateTime CreatedDate;

    @Column(name = "updated_date")
    private LocalDateTime UpdatedDate;

    //支店名
    @ManyToOne
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branch;

    //部署名
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private Department department;



}
