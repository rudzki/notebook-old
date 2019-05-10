package org.rudzki.notebook.data;

import java.util.List;

import org.rudzki.notebook.models.Note;
import org.springframework.stereotype.Component;

@Component
public interface NoteDao {
	
	public List<Note> getAllNotes();
	public Note getNote(long id);
	public Note getNoteBySlug(String slug);
	public void addNote(Note note);
	public void deleteNote(long id);

}
