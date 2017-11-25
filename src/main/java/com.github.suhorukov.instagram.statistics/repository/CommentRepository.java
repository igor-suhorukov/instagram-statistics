package com.github.suhorukov.instagram.statistics.repository;

import me.postaddict.instagram.scraper.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
