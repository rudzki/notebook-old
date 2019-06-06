package org.rudzki.notebook.models;

import java.time.LocalDateTime;
import java.util.List;

public class Note {
	
	private Long id;
	private LocalDateTime published;
	private String title;
	private String content;
	private String meta;
	private List<String> tags;
	private String slug;
	
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getPublished() {
		return published;
	}
	public void setPublished(LocalDateTime published) {
		this.published = published;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}


}
