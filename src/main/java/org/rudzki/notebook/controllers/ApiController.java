package org.rudzki.notebook.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.rudzki.notebook.data.NoteDao;
import org.rudzki.notebook.models.Note;
import org.rudzki.notebook.services.NoteService;

@RequestMapping("/api/")
@RestController
public class ApiController {

	@Autowired
	private NoteService noteService;
	
	@GetMapping("/notes/")
	public List<Note> displayHomePage(HttpServletRequest req) {
		List<Note> notes = noteService.getAllNotes();
		return notes;
	}

	@GetMapping("/notes/{id}/")
	public Note displayNote(HttpServletRequest req, @PathVariable("id") String id) {
		return noteService.getNoteById(Long.parseLong(id));
	}
	
	@GetMapping("/notes/{id}/meta/")
	public String displayMeta(HttpServletRequest req, @PathVariable("id") String id) {
		return noteService.getMetaByNoteId(Long.parseLong(id));
	}
	
	@GetMapping("/pages/{page}/")
	public List<Note> displayNoteRange(HttpServletRequest req,
			@PathVariable("page") String page) {
		
		return noteService.getPaginatedNotes(Integer.parseInt(page), 2);
	}

	@GetMapping("/notes/{id}/delete/")
	public void deleteNote(HttpServletRequest req, @PathVariable("id") String id) {
		try {
			Long idAsLong = Long.parseLong(id);
			noteService.deleteNote(idAsLong);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@PostMapping(path="/notes/add/", consumes = "application/json", produces = "application/json")
	public void addNote(@RequestBody Note note) {
		noteService.saveNote(note);
	}
}
