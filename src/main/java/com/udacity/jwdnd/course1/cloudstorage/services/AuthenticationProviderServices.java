package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthenticationProviderServices implements AuthenticationProvider {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private HashService hashService;


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		Users users = userMapper.getUserInfo(username);
		if (users != null) {
			String salt = users.getSalt();
			String hashedPassword = hashService.getHashedValue(password, salt);
			if (users.getPassword().equals(hashedPassword)) {
				return new UsernamePasswordAuthenticationToken(users, password, new ArrayList<>());
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
