package com.itgate.tunijobs.repository;

import com.itgate.tunijobs.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}