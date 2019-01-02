package com.letbeclear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.letbeclear.model.PostLike;
import com.letbeclear.model.UserPost;

public interface PostLikeRepository extends CrudRepository<PostLike, Long> {

	@Query("select u from PostLike u where u.userPost.postId=?1")
	public List<PostLike> findByPostId(Long postId);
	
	@Query("select u from PostLike u where u.users.userId=?1")
	public PostLike findByUserId(Long userId);
	
	public int countByUserPost(UserPost userPost);
	
	@Query("select count(u) from PostLike u where u.userPost.postId=?1")
	public int countByPostId(long postId);
}
