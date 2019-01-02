package com.letbeclear.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.letbeclear.model.UserPost;
import com.letbeclear.model.Users;

public interface UserPostRepository extends CrudRepository<UserPost,Long> {

	public List<UserPost> findAllByUsers(Users users);
	public List<UserPost> findAllByLongitudeAndLatitude(BigDecimal longitude,BigDecimal latitude);
	public List<UserPost> findAllByOrderByPostTimeDesc();
	public UserPost findByOrderByPostIdDesc();
	@Query("Select coalesce(max(p.postId),0) FROM UserPost p")
	public Long getMax();
	public UserPost findByPostId(Long postId);
	public List<UserPost> findALLByPostId(Long postId);
}
