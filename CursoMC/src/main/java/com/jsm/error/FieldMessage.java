package com.jsm.error;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String fieldMessage;

	public FieldMessage() {
		super();

	}

	public FieldMessage(String fieldName, String fielMessage) {
		super();
		this.fieldName = fieldName;
		this.fieldMessage = fielMessage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFielMessage() {
		return fieldMessage;
	}

	public void setFielMessage(String fielMessage) {
		this.fieldMessage = fielMessage;
	}

}
