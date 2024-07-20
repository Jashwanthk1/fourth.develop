package com.office.fourth.develop.service;

import org.springframework.http.ResponseEntity;

import com.office.fourth.develop.dto.LoginRequestDTO;
import com.office.fourth.develop.dto.ResponseDTO;

public interface LoginService {

	public ResponseEntity<ResponseDTO<String>> login(LoginRequestDTO loginRequestDTO);

}
