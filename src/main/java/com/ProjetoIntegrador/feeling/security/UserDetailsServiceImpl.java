package com.ProjetoIntegrador.feeling.security;

/*
 * Classe criada para verificação e validação de dados do usuario
 * Class created to verify and validate the user data 
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProjetoIntegrador.feeling.model.UserModel;
import com.ProjetoIntegrador.feeling.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserModel> user = userRepository.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException (email + " Not Found.. "));
		
		
		return user.map(UserDetailsImpl::new).get();
					
	}

}
