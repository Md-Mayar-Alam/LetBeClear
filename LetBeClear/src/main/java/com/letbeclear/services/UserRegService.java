package com.letbeclear.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.letbeclear.model.UserReg;
import com.letbeclear.repository.UserRegRepository;

@Service
public class UserRegService
{
	@Autowired
	UserRegRepository userRegRepository;
	
	public UserReg getUserRegByEmail(String email)
	{
		UserReg userReg=null;
		try
		{
			System.out.println("Inside UserRegService getUserRegByEmail() email is "+email);
			
			userReg=userRegRepository.findByLoginid(email);
			
			System.out.println("Inside UserRegService getUserRegByEmail() userReg is "+userReg);
			return userReg;
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserRegService getUserByEmail() ");
			e.printStackTrace();
			return null;
		}
	}
	
	public String changeForgotPassword(long userId, String newEncryptedPassword)
	{
		try
		{
			int status=userRegRepository.updateNewPassword(userId, newEncryptedPassword);
			
			System.out.println("Inside UserRegService changeForgotPassword status is "+status);
			
			if(status==1)
			{
				return "Success";
			}
			else
			{
				return "Failed";
			}
		}
		catch(Exception e)
		{
			System.err.println("Exception Inside UserRegService changeForgotPassword()");
			e.printStackTrace();
			
			return "Failed due to Exception";
		}
	}
}
