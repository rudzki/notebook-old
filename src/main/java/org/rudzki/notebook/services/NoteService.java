package org.rudzki.notebook.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.rudzki.notebook.data.NoteDao;
import org.rudzki.notebook.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
	@Autowired
	
	private NoteDao noteDao;
	
	public List<Note> getAllNotes() {
		List<Note> notes = noteDao.getAllNotes();
		return notes;
	}
	
	public List<Note> getPaginatedNotes(int page, int countPerPage) {
		List<Note> tenNotes = new ArrayList<Note>();
		List<Note> notes = noteDao.getAllNotes();
		int noteCount = notes.size();
		
		if (page < 1) {
			return null;
		}
		
		int startIndex = (page - 1) * countPerPage;
		int endIndex = startIndex + countPerPage;
		int i = startIndex; 

		while (i < endIndex) {
			if (noteCount > i) {
				Note note = notes.get(i);
				tenNotes.add(note);
			}
			i++;
		}
		
		return tenNotes;
	}
	
	public Note getNoteById(Long id) {
		Note note = noteDao.getNoteById(id);
		return note;
	}
	
	public Note getNoteBySlug(String slug) {
		Note note = noteDao.getNoteBySlug(slug);
		return note;
	}

	public void saveNote(Note note) {
		noteDao.saveNote(note);
	}

	public void deleteNote(Long id) {
		noteDao.deleteNote(id);
	}
	
	public String getMetaByNoteId(Long id) {
		String meta = noteDao.getMetaByNoteId(id);
		return meta;
	}
	
//	public List<Note> processNotes(List<Note> notes) {
//		List<Note> processedNotes = new ArrayList<Note>();
//		for (Note note : notes) {
//			Note processedNote = processNote(note);
//			processedNotes.add(processedNote);
//		}
//		return processedNotes;
//	}
//	
//	public Note processNote(Note note) {
//		LocalDateTime datetime = note.getPublished();
//		String datetimeAsString = datetime.getMonth().getDisplayName(null, null) + " " + datetime.getDayOfMonth() + ", " + datetime.getYear();
//		note.setMeta
//	}
	
}
