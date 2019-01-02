package com.letbeclear.request.dto;

import java.math.BigDecimal;

public class PostSearchByLocationRequest 
{
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) 
	{
		this.latitude = latitude;
	}
	
	public BigDecimal getLongitude() 
	{
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	
}
