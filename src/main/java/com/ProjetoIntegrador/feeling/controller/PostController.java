package com.ProjetoIntegrador.feeling.controller;

/*
  A classe "Controller" Foi criada para organizar as requisições que vão ser feitas, como "post"(postagem), "put"(atualizar)
  "delete"(deletar), e "GetMapping"(seleção dos dados).
*/

import java.util.List;

import javax.validation.Valid;

import com.ProjetoIntegrador.feeling.model.Post;
import com.ProjetoIntegrador.feeling.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAll(){
        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable long id){
        return repository.findById(id)
        .map(resp -> ResponseEntity.ok(resp))
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Post>> getByTitle(@PathVariable String title){
        return ResponseEntity.ok(repository.findAllByTitleContainingIgnoreCase(title));
    }
    @GetMapping("/text/{text}")
    public ResponseEntity<List<Post>> getByDescricao(@PathVariable String text){
        return ResponseEntity.ok(repository.findAllByTextContainingIgnoreCase(text));
        }
    @PostMapping
    public ResponseEntity<Post> post (@Valid @RequestBody Post post){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }
    @PutMapping
    public ResponseEntity<Post> put (@Valid @RequestBody Post post){
        return ResponseEntity.ok(repository.save(post));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        repository.deleteById(id);
    }

}
