package com.letbeclear.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letbeclear.model.UserPost;
import com.letbeclear.repository.UserPostRepository;

@Service
public class UserPostService 
{
	@Autowired
	UserPostRepository userPostRepository;
	
	public UserPost getUserPostObjByPostId(long postId)
	{
		return userPostRepository.findByPostId(postId);
	}
	
	public List<UserPost> findPostByLatitudeAndLongitude(BigDecimal latitude,BigDecimal longitude)
	{
		List<UserPost> listUserPostFinal=new ArrayList<UserPost>();
		
		List<UserPost> listUserPost=userPostRepository.findAllByLongitudeAndLatitude(longitude, latitude);
		
		if(listUserPost!=null)
		{
			for(UserPost userPost:listUserPost)
			{
				listUserPostFinal.add(userPost);
			}
			return listUserPostFinal;
		}
		else
		{
			return null;
		}
	}
}
