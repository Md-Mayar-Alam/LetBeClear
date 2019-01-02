package com.letbeclear.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letbeclear.model.Address;
import com.letbeclear.model.PostLike;
import com.letbeclear.model.UserPost;
import com.letbeclear.model.UserProfile;
import com.letbeclear.model.Users;
import com.letbeclear.repository.AddressRepository;
import com.letbeclear.repository.PostLikeRepository;
import com.letbeclear.repository.UserProfileRepository;
import com.letbeclear.response.dto.PostLikeResponse;
import com.letbeclear.response.dto.PostResponse;
import com.letbeclear.utils.JwtGenerator;

@Service
public class PostLikeService {

	@Autowired
	private PostLikeRepository postLikeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	JwtGenerator jwtGenerator;
	
	public List<PostResponse> showPostLike(int postId)
	{
		System.out.println(postId);
		Users user;
		List<PostResponse> list=new ArrayList<>();
		List<PostLike> postLike=postLikeRepository.findByPostId(2L);
		Iterator<PostLike> itr=postLike.iterator();
		while(itr.hasNext())
		{
			user=itr.next().getUsers();
			list.add(getRequest(user));
			
		}
		return list;
	}
	
	public PostLikeResponse addPostLike(int postid)
	{
		System.out.println(postid);
		int f=0;
		Users user=null;
		PostLike pl=null;
		PostLikeResponse postLikeResponse=null;
		long postId=2;
		Long userId=3L;
		int count=0;
		String status="";
		List<PostLike> list=postLikeRepository.findByPostId(postId);
		if(list!=null)
		{
			Iterator<PostLike> itr=list.iterator();
			while(itr.hasNext())
			{
				count++;
				pl=itr.next();
				user=pl.getUsers();
				if(user.getUserId()==userId)
				{
					count--;
					status="dislike";
					remove(pl);
					f=1;
				}
			}
			if(f==0)
			{
				add(postId,userId);
				count++;
				status="like";
			}
		}
		else
		{
			add(postId,userId);
			count++;
			status="like";
		}
		postLikeResponse=new PostLikeResponse();
		postLikeResponse.setCount(count);
		postLikeResponse.setStatus(status);
		System.out.println(postLikeResponse);
		postLikeResponse.setToken(jwtGenerator.generate());
		return postLikeResponse;
	}
	
	public PostResponse getRequest(Users user)
	{
		Long userId=user.getUserId();
		System.out.println(userId);
		Address address=addressRepository.findByUserId(userId);
		UserProfile userProfile=userProfileRepository.findByUserId(userId);
		PostResponse postResponse=new PostResponse();
		postResponse.setFirstName(address.getFirstname());
		postResponse.setLastName(address.getLastname());
		postResponse.setProfilePath(userProfile.getPhoto());
		return postResponse;
	}
	
	public void remove(PostLike postLike)
	{
		postLikeRepository.delete(postLike);
	}
	
	public void add(long postId,long userId)
	{
		UserPost userPost=new UserPost();
		userPost.setPostId(postId);
		Users newUser=new Users();
		newUser.setUserId(userId);
		PostLike newLike=new PostLike();
		newLike.setUserPost(userPost);
		newLike.setUsers(newUser);
		postLikeRepository.save(newLike);
	}
	
	public int countPostLike(long postId)
	{
		int countLike=postLikeRepository.countByPostId(postId);
		
		System.out.println("Inside PostLikeService countPostLike() count is "+countLike);
		
		return countLike;
	}
}
