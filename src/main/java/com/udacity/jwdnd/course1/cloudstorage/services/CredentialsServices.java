package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.common.Common;
import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.imp.CredentialsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsServices implements CredentialsImp {

	@Autowired
	private CredentialsMapper credentialsMapper;

	@Autowired
	private EncryptionService encryptionService;

	@Override
	public List<Credentials> getAllListCredentials(Integer userId) {
		return credentialsMapper.getAllListCredentials(userId);
	}

	@Override
	public int isExistCredentialById(Integer creId, Integer userId) {
		return credentialsMapper.isExistCredentialById(creId, userId);
	}

	public Credentials getCreById(Integer creId, Integer userId) {
		return credentialsMapper.getCreById(creId, userId);
	}

	@Override
	public boolean addCredentials(Credentials credential) {
		encryptPassword(credential);
		return credentialsMapper.addCredentials(credential);
	}

	@Override
	public boolean deleteCreById(Integer creId, Integer userId) {
		return credentialsMapper.deleteCreById(creId, userId);
	}

	@Override
	public boolean editCreById(Credentials credential) {
		encryptPassword(credential);
		return credentialsMapper.editCreById(credential);
	}

	public void encryptPassword(Credentials credentials) {
		String encodedKey = Common.ramdomEncodeBase64();
		credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(), encodedKey));
		credentials.setKey(encodedKey);
	}

	public Credentials decryptPassword(Credentials credential) {
		if (credential.getKey() == null) {
			return credential;
		}
		credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
		return credential;
	}

}
