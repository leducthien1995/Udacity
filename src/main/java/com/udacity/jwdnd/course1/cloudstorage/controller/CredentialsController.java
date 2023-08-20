package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CredentialsController {

	@Autowired
	private CredentialsServices credentialsServices;

	@PostMapping("/saveCredentials")
	public String saveCredentials(Credentials credential, Authentication authentication, Model model) {
		Users users = (Users) authentication.getPrincipal();
		Integer credentialId = credential.getCredentialId();
		credential.setUserId(users.getUserId());
		boolean result = false;

		if (credentialId == null) {
			// Regist new credentials
			result = credentialsServices.addCredentials(credential);
		} else if (credentialsServices.isExistCredentialById(credentialId, users.getUserId()) > 0) {
			// Update credentials info
			result = credentialsServices.editCreById(credential);
		} else {
			model.addAttribute("resultNG", "Not exist Credential");
		}
		if (!result) {
			model.addAttribute("resultNG", "Save Credential has error");
		} else {
			model.addAttribute("resultOK", "Add Credential successfully");
		}
		return "result";
	}

	@GetMapping("/deleteCredentials")
	public String deleteCredentials(@RequestParam("credentialId") Integer credentialId, Authentication authentication,
			Model model) {
		Users users;
		boolean results;
		if (credentialId != null) {
			users = (Users) authentication.getPrincipal();
			results = credentialsServices.deleteCreById(credentialId, users.getUserId());
			if (!results) {
				model.addAttribute("resultNG", "Delete Credentials has error ");
				return "result";
			} else {
				model.addAttribute("resultOK", "Delete Credentials successfully");
				return "result";
			}
		}
		return "error";
	}

	@GetMapping("/viewCredentials")
	@ResponseBody
	public Credentials viewCredentials(@RequestParam("credentialId") Integer credentialId, Authentication authentication) {
		Users users = (Users) authentication.getPrincipal();
		Credentials credentials = credentialsServices.getCreById(credentialId, users.getUserId());

		return credentials == null ? null : credentialsServices.decryptPassword(credentials);
	}
}
