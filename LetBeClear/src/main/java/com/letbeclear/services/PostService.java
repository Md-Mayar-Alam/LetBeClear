package com.letbeclear.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letbeclear.domain.JwtUser;
import com.letbeclear.model.Address;
import com.letbeclear.model.UserPost;
import com.letbeclear.model.UserProfile;
import com.letbeclear.model.Users;
import com.letbeclear.repository.AddressRepository;
import com.letbeclear.repository.PostCommentRepository;
import com.letbeclear.repository.PostLikeRepository;
import com.letbeclear.repository.UserPostRepository;
import com.letbeclear.repository.UserProfileRepository;
import com.letbeclear.request.dto.PostRequest;
import com.letbeclear.response.dto.PostResponse;

@Service
public class PostService {
	
	
	@Autowired 
	AddressRepository addressRepo;
	
	@Autowired
	PostCommentRepository postCommentRepo;
	
	@Autowired
	PostLikeRepository postLikeRepo;
	
	@Autowired
	UserPostRepository userPostRepo;		//Repository class for posts 
	
	@Autowired 
	UserProfileRepository userProfileRepository;
	
	@Autowired
	JwtUser jwtUser;
	/* 
	 * method for ADD Post by the user
	 */
	public int addPost(PostRequest postRequest)
	{
		Users users=new Users();
		//users.setUserId(jwtUser.getUserId());
		users.setUserId(2l);
		if(postRequest.getPostId()!=0)
		{
			/* UserPost userPost=userPostRepo.findOne(postRequest.getPostId()); */		//get all the detail about that postId for protecting the data loss
		
		UserPost userPost=userPostRepo.findByPostId(postRequest.getPostId());
		
		String imagePath=userPost.getImagePath();
		String primaryImage=userPost.getPrimaryImage();
		int noOfImage=userPost.getNoOfImage();
		
     	userPost.setImagePath(imagePath);  				// And set again give data and retrieve  into the userPost object 
     	userPost.setPrimaryImage(primaryImage);
     	userPost.setNoOfImage(noOfImage);
		userPost.setPostId(postRequest.getPostId());
	   	userPost.setUsers(users);
	   	userPost.setLatitude(postRequest.getLatitude());
		userPost.setLongitude(postRequest.getLongitude());
		userPost.setDescription(postRequest.getDescription());
		userPostRepo.save(userPost);					// Now save into database	
		return 1;
		}
		return 0;
}

	/*
	 * Method for get the post posted by the that particular user 
	 */
	public List<PostResponse> myPost() {
		
		Users users=new Users();
		users.setUserId(2l);
		//users.setUserId(jwtUser.getUserId());
		List<UserPost> userPostList=userPostRepo.findAllByUsers(users);
		List<PostResponse> list=new ArrayList<>();
	//set the recode into the post response object
		try
		{
		for(UserPost userPost:userPostList)
		{
			PostResponse postResponse=new PostResponse();
			postResponse.setPostId(userPost.getPostId());
			postResponse.setDescription(userPost.getDescription());
			postResponse.setLatitude(userPost.getLatitude());
			postResponse.setLongitude(userPost.getLongitude());
			postResponse.setPostTime(String.valueOf(userPost.getPostTime()));
			postResponse.setImageCount(userPost.getNoOfImage());

			String allImages=userPost.getImagePath();
			StringTokenizer token=new StringTokenizer(allImages,",");			//we get all the image path in this variable
			/*
			 * now tokenize this paths and add into the list
			 */
			List<String> imagePath=new ArrayList<>();
			while(token.hasMoreTokens())
			{
				 	imagePath.add(token.nextToken());
			}
			postResponse.setPath(imagePath);
			postResponse.setPrimaryPath(userPost.getPrimaryImage());
			System.out.println(userPost.getPrimaryImage());
			System.out.println(postResponse.getPrimaryPath());
			postResponse.setLike(postLikeRepo.countByUserPost(userPost));
			postResponse.setComment(postCommentRepo.countByUserPost(userPost));
			UserProfile userProfile=userProfileRepository.findByUserId(users.getUserId());
			postResponse.setProfilePath(userProfile.getPhoto());
			Address address=addressRepo.findOneByUsers(users);
			postResponse.setFirstName(address.getFirstname());
			postResponse.setLastName(address.getLastname());
			list.add(postResponse);						// add post Response into the list
		}
	
	return list;	//return the postRequest object
		}
		catch(Exception e){System.out.println(e);
		return null;
		}
	}

	/*
	 * 	Method for get post according to some particular location
	 */
	public List<PostResponse> byAddress(PostRequest postRequest) {
		
		BigDecimal longitude=postRequest.getLongitude();
		BigDecimal latitude=postRequest.getLatitude();
		List<UserPost> userPostList=userPostRepo.findAllByLongitudeAndLatitude(longitude, latitude);
		List<PostResponse> list=new ArrayList<PostResponse>();
		
		for(UserPost userPost:userPostList)
		{
		PostResponse postResponse=new PostResponse();
		postResponse.setPostId(userPost.getPostId());
		postResponse.setDescription(userPost.getDescription());
		postResponse.setLatitude(userPost.getLatitude());
		postResponse.setLongitude(userPost.getLongitude());
		postResponse.setPostTime(String.valueOf(userPost.getPostTime()));
		String allImages=userPost.getImagePath();					//we get all the image path in this variable
		/*
		 * now tokenize this paths and add into the list
		 */
		StringTokenizer token=new StringTokenizer(allImages,",");	
		List<String> imagePath=new ArrayList<>();
		while(token.hasMoreTokens())
		{
			imagePath.add(token.nextToken());
		}
		postResponse.setPath(imagePath);
		
		postResponse.setPrimaryPath(userPost.getPrimaryImage());
		postResponse.setLike(postLikeRepo.countByUserPost(userPost));		//count the likes and add into the postResponse
		postResponse.setComment(postCommentRepo.countByUserPost(userPost));  //count the comment and add into the postResponse
		list.add(postResponse);							// add post Response into the list
	}
		return list;
	}
	
	/*
	 * get all post in descending order by the post time for the home page 
	 */
	public List<PostResponse> getPost() {
		
		Users users=new Users();
		users.setUserId(2l);
		//users.setUserId(jwtUser.getUserId());
		List<UserPost> userPostList=userPostRepo.findAllByOrderByPostTimeDesc();
		List<PostResponse> list=new ArrayList<PostResponse>();
		try{
		for(UserPost userPost:userPostList)
		{ 
			
		PostResponse postResponse=new PostResponse();
		postResponse.setPostId(userPost.getPostId());
		postResponse.setDescription(userPost.getDescription());
		postResponse.setLatitude(userPost.getLatitude());
		postResponse.setLongitude(userPost.getLongitude());
		postResponse.setImageCount(userPost.getNoOfImage());
		System.out.println(userPost.getPostTime());
		postResponse.setPostTime(String.valueOf(userPost.getPostTime()));
		String allImages=userPost.getImagePath(); 			//we get all the image path in this variable
		/*
		 * now tokenize this paths and add into the list
		 */
		StringTokenizer token=new StringTokenizer(allImages,",");
		List<String> imagePath=new ArrayList<>();
		while(token.hasMoreTokens())
		{
			imagePath.add(token.nextToken());
		}
		postResponse.setPath(imagePath);
		postResponse.setPrimaryPath(userPost.getPrimaryImage());
		UserProfile userProfile=userProfileRepository.findByUserId(users.getUserId());
		postResponse.setProfilePath(userProfile.getPhoto());
		Address address=addressRepo.findOneByUsers(users);
		postResponse.setFirstName(address.getFirstname());
		postResponse.setLastName(address.getLastname());
	
		postResponse.setLike(postLikeRepo.countByUserPost(userPost));		//count the likes and add into the postResponse
		postResponse.setComment(postCommentRepo.countByUserPost(userPost));  //count the comment and add into the postResponse
		list.add(postResponse);												// add post Response into the list

		}
		return list; 						//return the list
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	public int deletePost(PostRequest postRequest) {
		 
	long postId=postRequest.getPostId();
	userPostRepo.deleteById(postId);
		return 1;
	}
}
