package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.UpFile;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UpFileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UpFileController {

	@Autowired
	private UpFileServices upFileServices;

	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxFileSize;

	@PostMapping("/saveFile")
	public String saveFile(Authentication authentication, MultipartFile fileUpload, Model modal)
			throws IOException, MaxUploadSizeExceededException {
		Users users = (Users) authentication.getPrincipal();
		String errorFilesUpload = "";

		if (fileUpload.isEmpty()) {
			errorFilesUpload = "File upload is empty.";
		}

		int filesExits = upFileServices.isFileExist(fileUpload, users.getUserId());
		if (filesExits > 0) {
			errorFilesUpload = " File has been exits.";
		}

		if (!errorFilesUpload.isBlank()) {
			modal.addAttribute("errorSaveFiles", errorFilesUpload);
			return "result";
		}

		int saveFile = upFileServices.addFiles(fileUpload, users.getUserId());
		if (saveFile == 0) {
			modal.addAttribute("resultNG", "Upload failed, Please try again!!!");
			return "result";
		}
		modal.addAttribute("resultOK", "Upload file successfully!!!");
		return "result";
	}

	@GetMapping("/deleteFile")
	public String deleteFile(@RequestParam("fileId") Integer fileid, Authentication authentication, Model modal) {
		Users users = (Users) authentication.getPrincipal();
		if (fileid != null) {
			int results = upFileServices.deleteFileById(fileid, users.getUserId());
			if (results == 0) {
				modal.addAttribute("resultNG", "File Not exist file or error");
				return "result";
			}
			modal.addAttribute("resultOK", "Delete file successfully");
			return "result";
		}
		return "error";

	}

	@GetMapping("/viewFile")
	public ResponseEntity<byte[]> handleViewFile(@RequestParam("fileId") Integer fileId,
			Authentication authentication) {
		Users users = (Users) authentication.getPrincipal();
		UpFile file = upFileServices.getFileById(fileId, users.getUserId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file.getFileName());
		headers.setContentType(MediaType.parseMediaType(file.getContentType()));
		return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileUploadError(Model modal) {
		modal.addAttribute("resultNG", "File must not exceed " + this.maxFileSize + ", please try again!");
		return "redirect:/result?error";
	}
}
