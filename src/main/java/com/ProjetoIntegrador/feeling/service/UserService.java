package com.ProjetoIntegrador.feeling.service;

import java.nio.charset.Charset;
import java.util.Optional;

import com.ProjetoIntegrador.feeling.model.UserLoginDTO;
import com.ProjetoIntegrador.feeling.model.UserModel;
import com.ProjetoIntegrador.feeling.repository.UserRepository;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel registerUser(UserModel user) {

        Optional<UserModel> userM = repository.findByEmail(user.getEmail());

        if (userM.isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ja cadastrado");

        } else {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            String passwordEncoder = encoder.encode(user.getPassword());
            user.setPassword(passwordEncoder);

            return repository.save(user);
        }
    }

    public Optional<UserLoginDTO> userLogin(Optional<UserLoginDTO> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<UserModel> userM = repository.findByEmail(user.get().getEmail());

        if (userM.isPresent()) {
            if (encoder.matches(user.get().getPassword(), userM.get().getPassword())) {
                String auth = user.get().getEmail() + ":" + user.get().getPassword();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodeAuth);

                user.get().setToken(authHeader);
                user.get().setId(userM.get().getId());
                user.get().setName(userM.get().getName());
            }
            return user;
        }
        return null;
    }

}
