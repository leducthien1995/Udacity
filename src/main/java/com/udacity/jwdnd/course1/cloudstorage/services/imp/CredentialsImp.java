package com.udacity.jwdnd.course1.cloudstorage.services.imp;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;

import java.util.List;

public interface CredentialsImp {
	public List<Credentials> getAllListCredentials(Integer userId);

	public int isExistCredentialById(Integer creId, Integer userId);

	public Credentials getCreById(Integer creId, Integer userId);

	public boolean addCredentials(Credentials credential);

	public boolean deleteCreById(Integer creId, Integer userId);

	public boolean editCreById(Credentials credential);
}
