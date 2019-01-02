package com.letbeclear.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.letbeclear.model.UserReg;

public interface UserRegRepository extends CrudRepository<UserReg,String> {

	public UserReg findByLoginid(String email);

	@Query("update UserReg u set u.logonpassword=:logonpassword where u.userId=:userId")
	public int updateNewPassword(@Param("userId")long userId, @Param("logonpassword")String logonpassword);
}
