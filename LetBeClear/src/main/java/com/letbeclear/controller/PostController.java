package com.letbeclear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letbeclear.request.dto.PostRequest;
import com.letbeclear.response.dto.PostResponse;
import com.letbeclear.response.dto.ResponseSender;
import com.letbeclear.services.PostService;
import com.letbeclear.utils.JwtGenerator;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	@Autowired
	JwtGenerator tokenGenerator;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="rest/post",method=RequestMethod.POST)
	public ResponseSender addPost(@RequestBody PostRequest postRequest)
	{
	
	ResponseSender responseSender=new ResponseSender();
	int Status=	postService.addPost(postRequest);
	if(Status==1)
	{
		
		responseSender.setMessage("successfully posted");
		responseSender.setToken(tokenGenerator.generate());
		responseSender.setFlag(true);
		return responseSender;		
	}
	
	responseSender.setMessage("you do some mistake");
	return responseSender;	
	}
	
	
	//for get all the post
	
	@CrossOrigin(origins="*")
	@RequestMapping("/rest/mypost")
	public ResponseSender getMyPost()
	{
		ResponseSender responseSender=new ResponseSender();
		List<PostResponse> list=postService.myPost();
		if(list!=null)
		{
			responseSender.setToken(tokenGenerator.generate());
			responseSender.setList(list);
			responseSender.setFlag(true);
			return responseSender;	
		}
			responseSender.setMessage("may user haven't post till now");
			return responseSender; 
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/postbyaddress",method=RequestMethod.POST)
	public ResponseSender getByAddress(@RequestBody PostRequest postRequest)
	{
		ResponseSender responseSender=new ResponseSender();
		List<PostResponse> list= postService.byAddress(postRequest);
		if(list!=null)
		{
			responseSender.setToken(tokenGenerator.generate());
			responseSender.setFlag(true);
			responseSender.setList(list);
			return responseSender;	
		}
			responseSender.setMessage("you do some mistake");
			return responseSender; 
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/allpost")
	public ResponseSender getAllPost()
	{
		ResponseSender responseSender=new ResponseSender();
		List<PostResponse> list= postService.getPost();
		if(list!=null)
		{
			responseSender.setToken(tokenGenerator.generate());
			responseSender.setFlag(true);
			responseSender.setList(list);
			return responseSender;	
		}
			responseSender.setMessage("you do some mistake");
			return responseSender; 
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/deletepost", method=RequestMethod.POST)
	public  ResponseSender deletePost(@RequestBody PostRequest postRequest)
	{
		ResponseSender responseSender=new ResponseSender();
		int status=	postService.deletePost(postRequest);
		if(status==1)
		{
			responseSender.setMessage("successfully deleted");
			responseSender.setToken(tokenGenerator.generate());
			responseSender.setFlag(true);
			return responseSender;
		}
		responseSender.setMessage("you do some mistake");
		return responseSender; 
	}
	
}
