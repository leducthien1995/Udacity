package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.common.Common;
import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NotesController {

	@Autowired
	private NotesServices notesServices;

	@PostMapping("/saveNotes")
	public String saveNotes(Authentication authentication, Notes notesInput, Model model) {
		String errorValid = validInputNote(notesInput);
		Integer noteId = notesInput.getNoteId();
		if (noteId == null && !errorValid.isBlank()) {
			model.addAttribute("resultNG", errorValid);
			return "result";
		}
		Users users = (Users) authentication.getPrincipal();
		notesInput.setUserId(users.getUserId());

		boolean saveNotes = false;
		if (noteId == null) {
			saveNotes = notesServices.addNote(notesInput);
		} else {
			if (notesServices.getNoteById(notesInput.getNoteId(), users.getUserId()) != null) {
				saveNotes = notesServices.editNoteById(notesInput);
			}
		}

		if (!saveNotes) {
			model.addAttribute("resultNG", "Save note error");
			return "result";
		}
		model.addAttribute("resultOK", "Save note successfully");
		return "result";
	}

	private String validInputNote(Notes notesInput) {
		if (!Common.isValidString(notesInput.getNoteTitle())
				|| !Common.isValidString(notesInput.getNoteDescription())) {
			return "Input notes invalid";
		}

		List<Notes> notesCheckExits = notesServices.getNoteByTitle(notesInput.getNoteTitle());
		if (notesCheckExits != null && !notesCheckExits.isEmpty()) {
			return "Input notes Title is exits in DB";
		}
		return "";
	}
	@GetMapping("/deleteNotes")
	public String deleteNote(@RequestParam("noteId")Integer noteId, Authentication authentication, Model model) {
		Users users = (Users) authentication.getPrincipal();
		if (noteId != null) {
			boolean result = notesServices.delNoteById(noteId, users.getUserId());
			if (!result) {
				model.addAttribute("resultNG", "Detele note error");
				return "result";
			} else {
				model.addAttribute("resultOK", "Delete file successfully");
				return "result";
			}
		}
		return "error";
	}
}
