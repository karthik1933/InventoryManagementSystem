package com.nisum.ims.entity;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Category {

	@Id
	private String code;
	private String name;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
