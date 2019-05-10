package org.rudzki.notebook.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.rudzki.notebook.data.NoteDao;
import org.rudzki.notebook.models.Note;

@RequestMapping("/api/")
@RestController
public class ApiController {

	@Autowired
	private NoteDao dao;

	@GetMapping("/notes/")
	public List<Note> displayHomePage(HttpServletRequest req) {
		List<Note> notes = dao.getAllNotes();
		return notes;
	}

	@GetMapping("/note/{id}/")
	public Note displayNote(HttpServletRequest req, @PathVariable("id") String id) {
		return dao.getNote(Long.parseLong(id));
	}

	@GetMapping("/note/{id}/delete/")
	public void deleteNote(HttpServletRequest req, @PathVariable("id") String id) {
		try {
			dao.deleteNote(Long.parseLong(id));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@PostMapping("/note/add/")
	public void addNote(@RequestParam("title") String title,
						@RequestParam("content") String content,
						@RequestParam("slug") String slug) {
		Note note = new Note();
		note.setTitle(title);
		note.setContent(content);
		dao.addNote(note);
	}
}
