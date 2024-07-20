package com.office.fourth.develop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.fourth.develop.dto.LoginRequestDTO;
import com.office.fourth.develop.dto.ResponseDTO;
import com.office.fourth.develop.service.LoginService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

	private final LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO<String>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return loginService.login(loginRequestDTO);
	}

}
