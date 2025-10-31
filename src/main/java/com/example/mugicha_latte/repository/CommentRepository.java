package com.example.mugicha_latte.repository;

import com.example.mugicha_latte.repository.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("""
    SELECT c FROM Comment c
    JOIN FETCH c.user
    ORDER BY c.createdDate ASC
""")
    List<Comment> findAllComments();
}
