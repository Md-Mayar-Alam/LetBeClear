package com.letbeclear.response.dto;

import java.util.Date;

public class PostSearchByLocationResponseHelper 
{
	private String name;
	private String imagesUrl[]=new String[6];
	private Date postTime;
	private int likeCount;
	private int commentCount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String[] getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String[] imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
}
