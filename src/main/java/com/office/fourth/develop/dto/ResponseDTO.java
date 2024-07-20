package com.office.fourth.develop.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {

	private int status;
	private String message;
	private T data;

}
