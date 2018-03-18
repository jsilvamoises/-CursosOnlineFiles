package com.jsm.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsm.security.enums.Perfil;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	

	public UserSS() {
		super();
		
	}
	
	

	public UserSS(Long id, String username, String password, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;		
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getRole().toString())).collect(Collectors.toList());
	}



	public Long getId() {
		return this.id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public boolean hasHole(Perfil perfil) {		
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getRole()));
	}

}
