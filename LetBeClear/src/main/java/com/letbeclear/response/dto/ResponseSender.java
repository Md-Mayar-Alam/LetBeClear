package com.letbeclear.response.dto;

import java.util.List;

public class ResponseSender 
{
	private String token;
	private String message;
	private boolean flag;
	private List<PostResponse> list;
	
	public List<PostResponse> getList() {
		return list;
	}
	public void setList(List<PostResponse> list) {
		this.list = list;
	}
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
	
	
}
