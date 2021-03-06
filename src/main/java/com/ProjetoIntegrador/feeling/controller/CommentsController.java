package com.ProjetoIntegrador.feeling.controller;

import java.util.List;

import javax.validation.Valid;

import com.ProjetoIntegrador.feeling.model.Comments;
import com.ProjetoIntegrador.feeling.repository.CommentsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentsController {

    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Comments>> getAllComments() {
        return ResponseEntity.ok(commentsRepository.findAll());
    }

    @PostMapping("/post")
    public ResponseEntity<Comments> postComments(@Valid @RequestBody Comments comments) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsRepository.save(comments));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentsRepository.deleteById(id);
    }

}
