package com.github.suhorukov.instagram.statistics.repository;

import com.github.suhorukov.instagram.statistics.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
