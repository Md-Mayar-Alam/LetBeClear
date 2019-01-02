package com.letbeclear.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letbeclear.repository.UserProfileRepository;

@Service
public class UserProfileService 
{	
	@Autowired 
	UserProfileRepository userProfileRepository;
	
	public String getProfileImageUrlByUserId(long userId)
	{
		return userProfileRepository.getProfileImage(userId);
	}
}
