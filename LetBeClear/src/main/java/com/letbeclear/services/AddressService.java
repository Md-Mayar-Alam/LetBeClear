package com.letbeclear.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letbeclear.model.Address;
import com.letbeclear.repository.AddressRepository;

@Service
public class AddressService 
{
	@Autowired
	AddressRepository addressRepository;
	
	public Address getAddressTableByEmail(String email)
	{
		return addressRepository.findByEmail(email);
	}
	
	public Address getAddressByUserId(long userId)
	{
		return addressRepository.findByUserId(userId);
	}
}
