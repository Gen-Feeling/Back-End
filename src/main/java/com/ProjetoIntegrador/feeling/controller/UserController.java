package com.ProjetoIntegrador.feeling.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.feeling.model.UserLoginDTO;
import com.ProjetoIntegrador.feeling.model.UserModel;
import com.ProjetoIntegrador.feeling.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * Metodo logar
	 */
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginDTO> autentication(@Valid @RequestBody Optional <UserLoginDTO> user){
		return userService.userLogin(user).map(resp ->ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());				
		
	}
	
	/*
	 * Metodo cadastrar
	 */
	
	@PostMapping("/register")
	public ResponseEntity<UserModel>register(@Valid @RequestBody UserModel user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));		
	}

}
