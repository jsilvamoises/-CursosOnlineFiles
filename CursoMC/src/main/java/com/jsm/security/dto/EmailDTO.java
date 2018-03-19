package com.jsm.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO {
	
	@Email
	@NotEmpty
    private String email;

	public EmailDTO(String email) {
		super();
		this.email = email;
	}

	public EmailDTO() {
		super();
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
