package com.letbeclear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letbeclear.response.dto.PostLikeResponse;
import com.letbeclear.response.dto.PostResponse;
import com.letbeclear.response.dto.ResponseSender;
import com.letbeclear.services.PostLikeService;
import com.letbeclear.utils.JwtGenerator;

@RestController
public class PostLikeController {

	@Autowired
	private PostLikeService postLikeService;
	@Autowired 
	private JwtGenerator jwtGenerator;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/postLike/{postId}",method=RequestMethod.GET)
	public ResponseSender  showPostLike(@PathVariable("postId")String postId) {
		 List<PostResponse> list=postLikeService.showPostLike(Integer.parseInt(postId));
		ResponseSender responseSender=new ResponseSender();
		responseSender.setList(list);
		responseSender.setMessage("successful");
		responseSender.setToken(jwtGenerator.generate());
		responseSender.setFlag(true);
		return responseSender;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/postLike/{addLike}/{postId}",method=RequestMethod.GET)
	public PostLikeResponse addPostLike(@PathVariable("postId")String postId)
	{
		return postLikeService.addPostLike(Integer.parseInt(postId));
	}
	
}
