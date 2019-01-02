package com.letbeclear.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.letbeclear.model.Address;
import com.letbeclear.model.Users;

public interface AddressRepository extends CrudRepository<Address,String>{

	public Address findByEmail(String email);
	public Address findByUsers(Users users);
	
	@Query("select u from Address u where u.users.userId=?1")
	public Address findByUserId(Long userId);
	
	public Address findOneByUsers(Users user);
}
