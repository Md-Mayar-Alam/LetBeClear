package com.letbeclear.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.letbeclear.model.Address;
import com.letbeclear.model.UserProfile;
import com.letbeclear.model.UserReg;
import com.letbeclear.model.Users;
import com.letbeclear.repository.AddressRepository;
import com.letbeclear.repository.UsersRepository;
import com.letbeclear.request.dto.RegisterRequest;
import com.letbeclear.response.dto.RegisterResponse;
import com.letbeclear.security.FieldValidation;
import com.letbeclear.security.JwtAuthenticationToken;
import com.letbeclear.utils.JwtGenerator;

@Service
public class RegisterService {

	/*
	 * Repositories for save and retrieve the data  
	 */
	@Autowired
	UsersRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	
	/*
	 * TokenGenerator class for generating the token
	 */
	@Autowired
	JwtGenerator tokenGenerator;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(4);
	
	
	public RegisterResponse registered(RegisterRequest registerRequest)   
	{
		RegisterResponse registerResponse=new RegisterResponse();
		
		int status=FieldValidation.validate(registerRequest);    //calling validate() for validating the field data which is send by the users
		if(status==0)
		{
			registerResponse.setMessage("User is invalid");		//Create a response class to send the response.
			registerResponse.setStatus(0);
			return registerResponse;
			}
		
		
		
		/*
		 * Checking Duplicate email if exist user can't register  
		 */
		String email = registerRequest.getEmail();
		Address emailexit=addressRepository.findByEmail(email); 
		
		
		// if duplicate email is not found that means new user and it can register  
		if(emailexit==null)
		{
			
			Address addressTable=new Address(); //now create the object for saving the data into different-different tables
			Users users=new Users();
			System.out.println("x");
			UserReg userReg=new UserReg();
			UserProfile userProfile=new UserProfile();
			userProfile.setUsers(users);
			System.out.println(registerRequest);
			addressTable.setFirstname(registerRequest.getFirstName());
			addressTable.setLastname(registerRequest.getLastName());
			addressTable.setEmail(registerRequest.getEmail());
			addressTable.setUsers(users);
			addressTable.setMobilephone(registerRequest.getContact());
			Set<Address> address = new HashSet<Address>();
			address.add(addressTable);
		
			userReg.setLoginid(registerRequest.getEmail());
			System.out.println(registerRequest.getPassword());
			System.out.println(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
			userReg.setLogonpassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
			//userReg.setLogonpassword(registerRequest.getPassword());
			userReg.setUsers(users);
			users.setRegistertype("R");
			users.setProfiletype("U");
			users.setLanguageId("-1");
			users.setUserReg(userReg);
			users.setAddresses(address);
			users.setUserProfile(userProfile);
			userRepository.save(users);								//save the data
			System.out.println("yes");
			String token=tokenGenerator.generate();			//and generate the token 
			new JwtAuthenticationToken(token);
			registerResponse.setFirstName(registerRequest.getFirstName());
			registerResponse.setToken(token);
			registerResponse.setMessage("sucessfull");       		//set the data response data into response object
			registerResponse.setStatus(1);
		}
		
		else
		{
		registerResponse.setMessage("email is already used");
		registerResponse.setStatus(-1);
		}
		
		return registerResponse;				//return response object
	}
}
