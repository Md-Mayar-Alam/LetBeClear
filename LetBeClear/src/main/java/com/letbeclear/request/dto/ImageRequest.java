package com.letbeclear.request.dto;

import java.util.List;

public class ImageRequest {

	private List<String> imageAsString;
	private String image;
	
	
	public List<String> getImageAsString() {
		return imageAsString;
	}
	public void setImageAsString(List<String> imageAsString) {
		this.imageAsString = imageAsString;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
