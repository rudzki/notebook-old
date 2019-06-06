package org.rudzki.notebook.data;

import java.util.List;

import org.rudzki.notebook.models.Note;
import org.springframework.stereotype.Component;

@Component
public interface NoteDao {
	
	public List<Note> getAllNotes();
	public Note getNoteById(long id);
	public Note getNoteBySlug(String slug);
	public void saveNote(Note note);
	public void deleteNote(long id);
	
	public String getMetaByNoteId(long noteId);
	public void saveMeta(long noteId, String value);
}
