package com.letbeclear.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.letbeclear.model.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

	public UserProfile findByUserId(long userId);

	@Query("Select max(p.userId) FROM UserProfile p")
	public Long getMax();

	@Query("select u.photo from UserProfile u where u.userId=?1")
	public String getProfileImage(long userId);
}
