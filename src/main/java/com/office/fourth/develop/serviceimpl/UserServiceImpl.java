package com.office.fourth.develop.serviceimpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.office.fourth.develop.dto.ResponseDTO;
import com.office.fourth.develop.entity.User;
import com.office.fourth.develop.repository.UserRepository;
import com.office.fourth.develop.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseDTO<List<User>>> getUsers() {
		ResponseDTO<List<User>> res = new ResponseDTO<>();
		res.setData(userRepository.findAll());
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("success");
		return ResponseEntity.ok(res);
	}

}
