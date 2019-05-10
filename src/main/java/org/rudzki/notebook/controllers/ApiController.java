package org.rudzki.notebook.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
//	@GetMapping("/site-info/")
//	public String displayInfo() {
//		dao.
//	}
//	dao.get
}
