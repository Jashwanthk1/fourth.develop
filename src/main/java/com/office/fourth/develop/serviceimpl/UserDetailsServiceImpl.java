package com.office.fourth.develop.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.office.fourth.develop.dto.CustomUserDetails;
import com.office.fourth.develop.entity.User;
import com.office.fourth.develop.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("user not found...");
		}
		return new CustomUserDetails(user.get());
	}
}
