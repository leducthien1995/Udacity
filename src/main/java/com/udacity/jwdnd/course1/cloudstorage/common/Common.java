package com.udacity.jwdnd.course1.cloudstorage.common;

import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.Base64;

public class Common {

	/* Ramdom Encode using base64 */
	public static String ramdomEncodeBase64() {
		SecureRandom random = new SecureRandom();
		byte[] key = new byte[16];
		random.nextBytes(key);
		String encodedKey = Base64.getEncoder().encodeToString(key);
		return encodedKey;
	}

	public static boolean isValidString(String input) {
		if (input == null || input.isBlank() || input.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static String getURLFiles(MultipartFile files) {
        String fileName = files.getOriginalFilename();
        int startIndex = fileName.replaceAll("\\\\", "/").lastIndexOf("/");
        return fileName.substring(startIndex + 1);
	}
}
