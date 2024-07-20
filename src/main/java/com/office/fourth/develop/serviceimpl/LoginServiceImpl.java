package com.office.fourth.develop.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.office.fourth.develop.dto.LoginRequestDTO;
import com.office.fourth.develop.dto.ResponseDTO;
import com.office.fourth.develop.entity.User;
import com.office.fourth.develop.repository.UserRepository;
import com.office.fourth.develop.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final JwtService jwtService;

	private final UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseDTO<String>> login(LoginRequestDTO loginRequestDTO) {
		ResponseDTO<String> res = new ResponseDTO<>();
		Optional<User> userOptional = userRepository.findByEmail(loginRequestDTO.getEmail());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getPassword().equals(loginRequestDTO.getPassword())) {
				res.setData(generateJWT(user));
				res.setStatus(HttpStatus.OK.value());
				res.setMessage("Login successful");
				return ResponseEntity.ok(res);
			}
		}
		res.setMessage("Login failed");
		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
	}

	private String generateJWT(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", user.getEmail());
		claims.put("name", user.getUserName());
		return jwtService.generateToken(user.getEmail(), claims);
	}

}
