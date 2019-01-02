package com.letbeclear.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letbeclear.response.dto.ResponseSender;

@Controller
public class LogoutController 
{
	@RequestMapping(value="/rest/logout", method=RequestMethod.GET)
	public @ResponseBody ResponseSender logoutUser(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.invalidate();
		
		ResponseSender responseSender=new ResponseSender();
		responseSender.setToken("");
		responseSender.setMessage("Successfully Logout");
		responseSender.setFlag(true);
		
		return responseSender;
	}
	
//	@RequestMapping(value="/rest/logout", method=RequestMethod.GET)
//	public ResponseSender logoutUser(HttpServletRequest request)
//	{
////		HttpSession session=request.getSession(false);
////		System.out.println("Insdide LoginController logoutUser session is "+session);
//		
//		String token=jwtAuthenticationToken.getToken();
//		
//		System.out.println("Inside LoginController logoutUser() before token is "+token);
//		
//		ResponseSender responseSender=new ResponseSender();
//		
//		if(token!=null)
//		{
//			//session.invalidate();
//			jwtAuthenticationToken.setToken(null);
//			System.out.println("Inside LoginController logoutUser() after token is "+jwtAuthenticationToken.getToken());
//			responseSender.setMessage("Sucessfully LoggedOut");
//			responseSender.setFlag(true);
//		}
//		else
//		{
//			responseSender.setMessage("Please Login First");
//			responseSender.setFlag(false);
//		}
//		return responseSender;
//	}	
}
