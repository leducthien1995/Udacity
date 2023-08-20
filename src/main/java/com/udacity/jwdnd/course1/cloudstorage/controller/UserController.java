package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.common.Common;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserServices userServices;

	@GetMapping("/signup")
	public String getViewSignup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String doSignup(Users users, Model model, RedirectAttributes redirectAttributes) {
		String error = "";
		if (Common.isValidString(users.getFirstName()) && Common.isValidString(users.getLastName())
				&& Common.isValidString(users.getPassword()) && Common.isValidString(users.getUsername())) {
			error = userServices.validationRegistUser(users.getUsername());

			if (error.isBlank()) {
				int results = userServices.addUsers(users);
				if (results == 0) {
					error = "An error occurred, please try again";
				}
			}
		} else {
			error = "Please input valid First name, Last name, username, password";
		}

		if (!error.isBlank()) {
			model.addAttribute("errorSignup", error);
			return "signup";
		}
		redirectAttributes.addFlashAttribute("signupOK", "OK");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String getViewLogin() {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home";
	}
}
