package com.letbeclear.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letbeclear.domain.JwtUser;
import com.letbeclear.model.Address;
import com.letbeclear.model.PostComment;
import com.letbeclear.model.Users;
import com.letbeclear.repository.AddressRepository;
import com.letbeclear.repository.PostCommentRepository;
import com.letbeclear.response.dto.PostCommentResponse;

@Service	
public class PostCommentService 
{
	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Autowired
	PostCommentsLikeService postCommentsLikeService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired 
	JwtUser jwtUser;
	
	@Autowired
	AddressRepository addressRepository;
	
//	public void insertPostComment(PostComment postComment)
//	{
//		postCommentRepository.save(postComment);
//	}
	
	public int insertPostComment(long postId,String comment,long userId)
	{
		try
		{
			return postCommentRepository.insertCommentPost(postId, comment, userId);
		}
		catch(Exception e)
		{
			System.out.println("Exception in PostCommentService insertPostComment()");
			e.printStackTrace();
			return 0;
		}
	}

	public List<PostCommentResponse> getAllCommentsOnPost(long postId) 
	{
		System.out.println("Inside PostCommentService getAllCommentsOnPost() ");
		//return postCommentRepository.findAll();
		List<PostComment> list=postCommentRepository.getAllComments(postId);
		List<PostCommentResponse> listComment=new ArrayList<>();
		
		try
		{
			for(PostComment pc:list)
			{
				long commentId=pc.getCommentId();
				int likesCount=postCommentsLikeService.countLikesByCommentId(commentId);
	
				long commentedUserId=postCommentRepository.getUserIdByCommentId(commentId);
				
				Users user=new Users();
				user.setUserId(commentedUserId);
				
				Address addressCommentedUser=addressRepository.findOneByUsers(user);
				String name=addressCommentedUser.getFirstname()+" "+addressCommentedUser.getLastname();
				System.out.println("Inside PostCommentService getAllCommentsOnPost() name is "+name);
				
				//This one is retreived from the JwtToken therefore setting it constant for testing
				//long loginUserId=jwtUser.getUserId();
				long loginUserId=2;
				
				System.out.println("Inside PostCommentService userId is "+commentedUserId);
				String profileImageUrl=userProfileService.getProfileImageUrlByUserId(commentedUserId);
				
				PostCommentResponse postCommentResponse=new PostCommentResponse();
				postCommentResponse.setComment(pc.getComment());
				postCommentResponse.setCommentId(pc.getCommentId());
				postCommentResponse.setName(name);
				postCommentResponse.setPostId(pc.getUserPost().getPostId());
				postCommentResponse.setCommentLike(likesCount);
				postCommentResponse.setCommentedProfileImageUrl(profileImageUrl);
				postCommentResponse.setIsUserComment(commentedUserId==loginUserId?"yes":"no");
				
				listComment.add(postCommentResponse);
			}
			return listComment;
		}
		catch(Exception e)
		{
			System.out.println("Exception in PostCommentService getAllCommentsOnPost()");
			e.printStackTrace();
			return null;
		}
	}

	public int editComment(long commentId, String comment) 
	{
		return postCommentRepository.editOldComment(commentId, comment);
	}

	public int deleteComment(long commentId) 
	{
		return postCommentRepository.deleteComment(commentId);
	}
	
	public int countCommentByPostId(long postId)
	{
		return postCommentRepository.countByPostId(postId);
	}
}
