package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.common.Common;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.imp.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServices implements UserImp {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private HashService hashService;

	@Override
	public Users getByUsername(String username) {
		return userMapper.getUserInfo(username);
	}

	@Override
	public int addUsers(Users users) {
		String encodeSaltbase64 = Common.ramdomEncodeBase64();
		String passHashed = hashService.getHashedValue(users.getPassword(), encodeSaltbase64);
		return userMapper.RegistUser(new Users(null, users.getUsername(), encodeSaltbase64, passHashed,
				users.getFirstName(), users.getLastName()));
	}

	@Override
	public String validationRegistUser(String username) {
		String errorMessage = "";
		if (StringUtils.isEmpty(username)) {
			errorMessage = "Username must be input!!!";
		} else if (getByUsername(username) != null) {
			errorMessage = "Username already exists, please enter another user!!!";
		}
		return errorMessage;
	}
}
