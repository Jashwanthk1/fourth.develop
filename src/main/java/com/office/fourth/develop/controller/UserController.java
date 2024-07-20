package com.office.fourth.develop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.fourth.develop.dto.ResponseDTO;
import com.office.fourth.develop.entity.User;
import com.office.fourth.develop.service.UserService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/getUsers")
	public ResponseEntity<ResponseDTO<List<User>>> getUsers() {
		return userService.getUsers();
	}

}
