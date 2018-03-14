package com.jsm.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Erro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer statusCode, String msg, Long timestamp) {
		super(statusCode, msg, timestamp);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName,message));
	}

	
	

}
