package com.kverchi.diary.model.entity;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="posts")
//http://www.greggbolinger.com/ignoring-hibernate-garbage-via-jsonignoreproperties/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	@Column(name="post_datetime")
	private ZonedDateTime postDatetime;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="text")
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sight_id")
	private CountriesSight countriesSight;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	/*@OneToMany(fetch=FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name="post_id")
	private Set<Comment> postComments;*/

	public Post() {};
	public Post(int postId, String title, String text) {
		this.postId = postId;
		this.title = title;
		this.text = text;
	}

	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public ZonedDateTime getPostDatetime() {
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		String text = postDatetime.format(formatter);
		postDatetime = ZonedDateTime.parse(text, formatter);
		return postDatetime;
	}

	@PrePersist
	@PreUpdate
	public void setPostDatetime() {
		this.postDatetime = ZonedDateTime.now();
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public CountriesSight getCountriesSight() {
		return countriesSight;
	}

	public void setCountriesSight(CountriesSight countriesSight) {
		this.countriesSight = countriesSight;
	}
	/*public Set<Comment> getPostComments() {
		return postComments;
	}
	public void setPostComments(Set<Comment> postComments) {
		this.postComments = postComments;
	}*/
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString(){
		return "id="+ postId +", title="+title+", text="+text;
	}
	
}
