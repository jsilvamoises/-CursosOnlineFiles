package com.jsm.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsm.domain.Cliente;
import com.jsm.repositories.ClienteRepository;
import com.jsm.security.UserSS;

@Service
public class UserDatailServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Cliente> cliente = repository.findByEmail(username);
		UserSS user =null;
		if(cliente.isPresent()) {
		  user = new UserSS(cliente.get().getId(),cliente.get().getEmail(), cliente.get().getPassword(), cliente.get().getPerfis());
		}else {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

}
