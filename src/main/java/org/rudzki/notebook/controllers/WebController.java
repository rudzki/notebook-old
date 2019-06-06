package org.rudzki.notebook.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.rudzki.notebook.models.Note;
import org.rudzki.notebook.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebController {

	@Autowired
	private NoteService noteService;

	@GetMapping("/")
	public ModelAndView displayAllNotes(HttpServletRequest req) {
		List<Note> notes = noteService.getAllNotes();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("notes", notes);
		return new ModelAndView("index", model);
	}
	
	@GetMapping("/notes/{slug}/")
	public ModelAndView displayNote(HttpServletRequest req, @PathVariable("slug") String slug) {
		Note note = noteService.getNoteBySlug(slug);
		if (note == null) {
			ModelAndView notFound = new ModelAndView("not-found");
			notFound.setStatus(HttpStatus.NOT_FOUND);
			return notFound;
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("note", note);
		return new ModelAndView("note", model);
	}
}
