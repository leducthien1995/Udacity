package com.udacity.jwdnd.course1.cloudstorage.services.imp;

import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;

import java.util.List;

public interface NotesImp {
	public List<Notes> getAllListNotes(Integer userId);

	public List<Notes> getNoteByTitle(String title);
	
	public Notes getNoteById(Integer noteId, Integer userId);

	public boolean editNoteById(Notes note);

	public boolean addNote(Notes note);

	public boolean delNoteById(Integer noteId, Integer userId);
}
