package com.udacity.jwdnd.course1.cloudstorage.services.imp;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;

public interface UserImp {
	public Users getByUsername(String username);

	public int addUsers(Users users);

	public String validationRegistUser(String username);
}
