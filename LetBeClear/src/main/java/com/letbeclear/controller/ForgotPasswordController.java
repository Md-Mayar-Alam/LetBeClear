package com.letbeclear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letbeclear.mail.ForgotPasswordEmailSender;
import com.letbeclear.model.Address;
import com.letbeclear.model.UserReg;
import com.letbeclear.request.dto.ForgotPasswordDto;
import com.letbeclear.response.dto.ResponseSender;
import com.letbeclear.services.UserRegService;

public class ForgotPasswordController 
{
	@Autowired
	UserRegService userRegService;
	
	@Autowired
	private ForgotPasswordEmailSender forgotPasswordEmailSender;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private long userId;
	
	/////////////////     FORGOT PASSWORD/////////////////////
		
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/forgotPassword", method=RequestMethod.POST)
	public ResponseSender forgotEmail(@RequestBody Address address)
	{
		System.out.println("Inside forgot Password "+address.getEmail());
		
		//Verifying User from DataBase
		//UserReg userReg=loginDetailsValidator.validateEmail(addressTable.getEmail());
		
		UserReg userReg=userRegService.getUserRegByEmail(address.getEmail());
		
		//saving userId of the user
		this.userId=userReg.getUserId();
		
		ResponseSender responseSender = new ResponseSender();
		
		if(userReg!=null)
		{
			System.out.println("Inside ForgotPasswordController  email "+userReg.getLoginid());
			//System.out.println("Inside ForgotPasswordController  password "+userReg.getLogonpassword());
			
			//Send Password to the Email
			forgotPasswordEmailSender.sendEmail(userReg.getLoginid(),"http://www.hello.com");
			
			responseSender.setMessage("Success");
			responseSender.setFlag(true);
		}
		else if(userReg==null)
		{
			//HttpStatus.NOT_FOUND error code 404
			
			responseSender.setMessage("Failed");
			responseSender.setFlag(false);
		}
		return responseSender;
	//return new ResponseEntity<String>("Mail Send Successfully",HttpStatus.OK);
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/updatePassword", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(@RequestBody ForgotPasswordDto forgotPasswordDto)
	{
		String newPassword=forgotPasswordDto.getNewPassword();
		String confirmNewPassword=forgotPasswordDto.getConfirmNewPassword();
		
		if(newPassword.equals(confirmNewPassword))
		{
			System.out.println("Inside ForgotPasswordController updatePassword() bCryptPasswordEncoder is "+bCryptPasswordEncoder);
			
			String encryptedPassword=bCryptPasswordEncoder.encode(newPassword);
			
			System.out.println("Inside ForgotPasswordController updatePassword() userId is "+userId);
			System.out.println("Inside ForgotPasswordController updatePassword() encryptedPassword is "+encryptedPassword);
			
			String message=userRegService.changeForgotPassword(userId, encryptedPassword);
			
			return message;
		}
		else
		{
			return "Password and ConfirmPassword doesn't matches";
		}
	}
}
