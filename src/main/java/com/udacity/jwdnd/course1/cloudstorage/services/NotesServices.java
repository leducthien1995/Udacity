package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServices {

	@Autowired
	private NotesMapper notesMapper;

	public List<Notes> getAllListNotes(Integer userId) {
		return notesMapper.getAllListNotes(userId);
	}

	public Notes getNoteById(Integer noteId, Integer userId) {
		return notesMapper.getNoteById(noteId, userId);
	}

	public boolean editNoteById(Notes note) {
		return notesMapper.editNoteById(note);
	}

	public boolean addNote(Notes note) {
		return notesMapper.addNote(note);
	}

	public boolean delNoteById(Integer noteId, Integer userId) {
		return notesMapper.delNoteById(noteId, userId);
	}

	public List<Notes> getNoteByTitle(String title) {
		// TODO Auto-generated method stub
		return notesMapper.getNoteByTitle(title);
	}
}
