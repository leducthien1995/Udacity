package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
	@Select("SELECT * FROM NOTES WHERE userid = #{userId}")
	List<Notes> getAllListNotes(Integer userId);

	@Select("SELECT * FROM NOTES WHERE noteid = #{noteId} and userid = #{userId}")
	Notes getNoteById(Integer noteId, Integer userId);
	
	@Select("SELECT * FROM NOTES WHERE notetitle = #{title}")
	List<Notes> getNoteByTitle(String title);

	@Update("UPDATE NOTES SET notetitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteid = #{noteId} and userid = #{userId}")
	boolean editNoteById(Notes note);

	@Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
	boolean addNote(Notes note);

	@Delete("DELETE FROM NOTES WHERE noteid = #{noteId} and userid = #{userId}")
	boolean delNoteById(Integer noteId, Integer userId);
}
