package com.letbeclear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letbeclear.request.dto.RegisterRequest;
import com.letbeclear.response.dto.RegisterResponse;
import com.letbeclear.services.RegisterService;

@RestController
public class RegisterController {
	
	@Autowired
	RegisterService registerservice;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public RegisterResponse addUser(@RequestBody RegisterRequest registerRequest)
	{
		RegisterResponse registerResponse=null;
		registerResponse=registerservice.registered(registerRequest);
		
		return registerResponse;
	}

}