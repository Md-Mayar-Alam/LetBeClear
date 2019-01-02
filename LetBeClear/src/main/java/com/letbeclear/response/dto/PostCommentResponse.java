package com.letbeclear.response.dto;

public class PostCommentResponse 
{
	private long commentId;
	private long postId;
	private String comment;
	private String name;
	private int commentLike;
	private String commentedProfileImageUrl;
	private String isUserComment;
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCommentLike() {
		return commentLike;
	}
	public void setCommentLike(int commentLike) {
		this.commentLike = commentLike;
	}
	public String getCommentedProfileImageUrl() {
		return commentedProfileImageUrl;
	}
	public void setCommentedProfileImageUrl(String commentedProfileImageUrl) {
		this.commentedProfileImageUrl = commentedProfileImageUrl;
	}
	public String getIsUserComment() {
		return isUserComment;
	}
	public void setIsUserComment(String isUserComment) {
		this.isUserComment = isUserComment;
	}
}
