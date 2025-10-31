package com.example.mugicha_latte.repository;

import com.example.mugicha_latte.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findByAccountAndPassword(String account, String password);
}
