package com.letbeclear.repository;

import org.springframework.data.repository.CrudRepository;

import com.letbeclear.model.Users;

public interface UsersRepository extends CrudRepository<Users,String>{

	//Optional<Users> findByEmail(String email);
		public Users findByUserId(Long userId);
}
