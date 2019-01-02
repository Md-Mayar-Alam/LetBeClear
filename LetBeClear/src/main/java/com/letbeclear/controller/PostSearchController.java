package com.letbeclear.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letbeclear.model.Address;
import com.letbeclear.model.UserPost;
import com.letbeclear.request.dto.PostSearchByLocationRequest;
import com.letbeclear.response.dto.PostSearchByLocationResponse;
import com.letbeclear.response.dto.PostSearchByLocationResponseHelper;
import com.letbeclear.services.AddressService;
import com.letbeclear.services.PostCommentService;
import com.letbeclear.services.PostLikeService;
import com.letbeclear.services.UserPostService;
import com.letbeclear.utils.JwtGenerator;

@Controller
public class PostSearchController 
{
	@Autowired
	UserPostService userPostService;
	
	@Autowired 
	AddressService addressService;
	
	@Autowired 
	PostLikeService postLikeService;
	
	@Autowired
	PostCommentService postCommentService;
	
	@Autowired 
	JwtGenerator jwtGenerator;
	
	@RequestMapping(value="/rest/searchByLocation", method=RequestMethod.POST)
	public @ResponseBody PostSearchByLocationResponse getAllPostByLocation(@RequestBody PostSearchByLocationRequest postSearchByLocationRequest )
	{
		BigDecimal latitude=postSearchByLocationRequest.getLatitude();
		BigDecimal longitude=postSearchByLocationRequest.getLongitude();
		
		List<UserPost> listUserPost=userPostService.findPostByLatitudeAndLongitude(latitude, longitude);
	
		PostSearchByLocationResponse postSearchByLocationResponse=new PostSearchByLocationResponse();
		
		List<PostSearchByLocationResponseHelper> listPostSearchByLocationResponse=new ArrayList<>();
		PostSearchByLocationResponseHelper postSearchByLocationResponseHelper=new PostSearchByLocationResponseHelper();
		
		if(listUserPost!=null)
		{
			Iterator<UserPost> iterator=listUserPost.iterator();
			
			while(iterator.hasNext())
			{
				UserPost userPost=iterator.next();
				
				//getting userId of the user associated with these location posts
				long userId=userPost.getUsers().getUserId();
				
				//getting name from Address
				Address address=addressService.getAddressByUserId(userId);
				
				String firstName=address.getFirstname();
				String lastName=address.getLastname();
				
				String name=firstName+" "+lastName;
				
				String imagePath=userPost.getImagePath();
				
				StringTokenizer stringTokenizer=new StringTokenizer(imagePath, ",");
				
				String imagesUrl[]=postSearchByLocationResponseHelper.getImagesUrl();
				
				int imageCount=0;
				while(stringTokenizer.hasMoreTokens())
				{
					String imageUrl=stringTokenizer.nextToken();
					System.out.println("Inside PostSearchController getAllPostByLocation inside while imageUrl is "+imageUrl);

					imagesUrl[imageCount]=imageUrl;
					
					imageCount++;
				}
				
				Date postTime=userPost.getPostTime();
				
				long postId=userPost.getPostId();
				
				int postLikes=postLikeService.countPostLike(postId);
				
				System.out.println("Inside PostSearchController getAllPostByLocation postLikes is "+postLikes);
				
				int postComments=postCommentService.countCommentByPostId(postId);
				
				System.out.println("Inside PostSearchController getAllPostByLocation postComments is "+postComments);
				
				
				//Adding all required results to ListPostSearchByLocationResponse
				
				postSearchByLocationResponseHelper.setName(name);
				postSearchByLocationResponseHelper.setImagesUrl(imagesUrl);
				postSearchByLocationResponseHelper.setPostTime(postTime);
				postSearchByLocationResponseHelper.setLikeCount(postLikes);
				postSearchByLocationResponseHelper.setCommentCount(postComments);
				
				listPostSearchByLocationResponse.add(postSearchByLocationResponseHelper);
			}
			
			String token=jwtGenerator.generate();
			
			postSearchByLocationResponse.setToken(token);
			postSearchByLocationResponse.setMessage("Success");
			postSearchByLocationResponse.setFlag(true);
			postSearchByLocationResponse.setPosts(listPostSearchByLocationResponse);
		}
		else
		{
			postSearchByLocationResponse.setMessage("Failed");
			postSearchByLocationResponse.setFlag(false);
		}
		return postSearchByLocationResponse;
	}
}
