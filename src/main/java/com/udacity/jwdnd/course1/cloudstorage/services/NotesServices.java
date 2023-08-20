package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.imp.NotesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServices implements NotesImp{

	@Autowired
	private NotesMapper notesMapper;

	@Override
	public List<Notes> getAllListNotes(Integer userId) {
		return notesMapper.getAllListNotes(userId);
	}

	@Override
	public Notes getNoteById(Integer noteId, Integer userId) {
		return notesMapper.getNoteById(noteId, userId);
	}

	@Override
	public boolean editNoteById(Notes note) {
		return notesMapper.editNoteById(note);
	}

	@Override
	public boolean addNote(Notes note) {
		return notesMapper.addNote(note);
	}

	@Override
	public boolean delNoteById(Integer noteId, Integer userId) {
		return notesMapper.delNoteById(noteId, userId);
	}

	@Override
	public List<Notes> getNoteByTitle(String title) {
		// TODO Auto-generated method stub
		return notesMapper.getNoteByTitle(title);
	}
}
