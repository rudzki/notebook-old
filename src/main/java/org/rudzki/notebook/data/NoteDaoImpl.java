package org.rudzki.notebook.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.rudzki.notebook.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.github.slugify.Slugify;

@Component
public class NoteDaoImpl implements NoteDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public NoteDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Note> getAllNotes() {
		List<Note> notes = new ArrayList<Note>();
		String query = "SELECT * FROM notes";
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(query);
		while (rowset.next()) {
			Note note = mapRowToNote(rowset);
			notes.add(note);
		}
		return notes;
	}

	@Override
	public Note getNote(long id) {
		String query = "SELECT * FROM notes WHERE id=" + id;
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(query);
		if (rowset.first()) {
			Note note = mapRowToNote(rowset);
			return note;
		}
		return null;
	}
	
	@Override
	public Note getNoteBySlug(String slug) {
		String query = "SELECT * FROM notes WHERE slug=" + slug;
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(query);
		if (rowset.first()) {
			Note note = mapRowToNote(rowset);
			return note;
		}
		return null;
	}

	@Override
	public void deleteNote(long id) {
		String query = "delete from notes where id=?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public void addNote(Note note) {
		Long id = getNextId();
		String slug = generateSlug(note.getTitle());
		String query = "INSERT INTO notes(id, published, title, content, tags, slug) VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(query, id, note.getPublished(), note.getTitle(), note.getContent(), note.getTags(), slug);
	}
	
	private String generateSlug(String title) {
		Slugify slg = new Slugify();
		String result = slg.slugify(title);
		if (getNoteBySlug(result) != null) {
			String uuid = UUID.randomUUID().toString().substring(0,5);
			return result + "-" + uuid;
		}
		return result;
	}

	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_note_id')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if (results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next id from sequence");
		}
		return id;
	}

	private Note mapRowToNote(SqlRowSet row) {
		Note note = new Note();

		Long id = row.getLong("id");
		note.setId(id);

		LocalDateTime published = row.getTimestamp("published").toLocalDateTime();
		note.setPublished(published);

		String title = row.getString("title");
		note.setTitle(title);

		String content = row.getString("content");
		note.setContent(content);

		List<String> tags = new ArrayList<String>();
		note.setTags(tags);

		String slug = row.getString("slug");
		note.setSlug(slug);
		
		return note;

	}
}
