package com.office.fourth.develop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.office.fourth.develop.dto.ResponseDTO;
import com.office.fourth.develop.entity.User;

public interface UserService {

	public ResponseEntity<ResponseDTO<List<User>>> getUsers();

}
