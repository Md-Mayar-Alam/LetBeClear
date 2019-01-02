package com.letbeclear.response.dto;

import org.springframework.stereotype.Component;

@Component
public class PostLikeResponse {

	private int count;
	private String status;
	private String token;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PostLikeResponse [count=" + count + ", status=" + status + "]";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
