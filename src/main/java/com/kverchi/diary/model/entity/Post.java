package com.kverchi.diary.model.entity;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="posts")
@NamedEntityGraph(name = "post-entity-graph", attributeNodes = {
		@NamedAttributeNode("author"),
		@NamedAttributeNode("sight")
})

public class Post {
	private static final Logger logger = LoggerFactory.getLogger(Post.class);
	private static final int TEXT_WITH_IMG_OFFSET = 200;
	private static final int TEXT_NO_IMG_OFFSET = 250;
	private static final String IMG_TAG_START = "<img";
	private static final String IMG_TAG_END = ">";

	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	@Column(name="created_at")
	private ZonedDateTime createdAt;
	@Column(name="updated_at")
	private ZonedDateTime updatedAt;
	@Column(name="title")
	private String title;
	@Column(name="preview_image_url")
	private String previewImageUrl;
	@Column(name="description")
	private String description;
	@Column(name="text")
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sight_id")
	private Sight sight;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User author;
	public Post() {};
	public Post(int postId, String title, String text) {
		this.postId = postId;
		this.title = title;
		this.text = text;
	}
	public Post(int postId, String title, String text, String previewImageUrl,
				Sight countriesSight, User author, ZonedDateTime createdAt) {
		this.postId = postId;
		this.title = title;
		this.text = text;
		this.previewImageUrl = previewImageUrl;
		this.sight = countriesSight;
		this.author = author;
		this.createdAt = createdAt;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public ZonedDateTime getCreatedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		String text = createdAt.format(formatter);
		createdAt = ZonedDateTime.parse(text, formatter);
		return createdAt;
	}
	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public ZonedDateTime getUpdatedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		String text = updatedAt.format(formatter);
		updatedAt = ZonedDateTime.parse(text, formatter);
		return updatedAt;
	}
	@PrePersist
	public void prePersistCallback() {
		setCreatedDate();
		prepareDescription();
	}
	@PreUpdate
	public void preUpdateCallback() {
		setUpdatedDate();
		prepareDescription();
	}
	private void setCreatedDate() {
		this.createdAt = ZonedDateTime.now();
		this.updatedAt = ZonedDateTime.now();
	}
	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	private void setUpdatedDate() {
		this.updatedAt = ZonedDateTime.now();
	}
	private void prepareDescription() {
		if(this.description == null) {
			int substringIndex = TEXT_NO_IMG_OFFSET;
			if(this.getPreviewImageUrl() != null) {
				substringIndex = TEXT_WITH_IMG_OFFSET;
			}
			StringBuilder previewBuilder = new StringBuilder();
			previewBuilder.append(this.getText().substring(0, substringIndex));
			int imgStartIndex = previewBuilder.indexOf(IMG_TAG_START);
			if(imgStartIndex != -1 && imgStartIndex != 0) {
				previewBuilder.replace(0, previewBuilder.length(), previewBuilder.substring(0, imgStartIndex));
			} else if(imgStartIndex == 0) {
				int imgEndIndex = previewBuilder.indexOf(IMG_TAG_END);
				previewBuilder.replace(0, previewBuilder.length(), previewBuilder.substring(imgEndIndex++));
			}
			this.setDescription(previewBuilder.toString());
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreviewImageUrl() {
		return previewImageUrl;
	}
	public void setPreviewImageUrl(String previewImageUrl) {
		this.previewImageUrl = previewImageUrl;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Sight getSight() {
		return sight;
	}

	public void setSight(Sight sight) {
		this.sight = sight;
	}

	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString(){
		return "id="+ postId +", title="+title+", text="+text;
	}
	
}
