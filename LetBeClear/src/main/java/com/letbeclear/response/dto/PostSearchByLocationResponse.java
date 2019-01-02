package com.letbeclear.response.dto;

import java.util.List;

public class PostSearchByLocationResponse 
{
	private String token;
	private String message;
	private boolean flag;
	private List<PostSearchByLocationResponseHelper> posts;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<PostSearchByLocationResponseHelper> getPosts() {
		return posts;
	}
	public void setPosts(List<PostSearchByLocationResponseHelper> posts) {
		this.posts = posts;
	}
}
