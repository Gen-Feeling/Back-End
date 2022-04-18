package com.ProjetoIntegrador.feeling.repository;

import com.ProjetoIntegrador.feeling.model.Comments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    
}
