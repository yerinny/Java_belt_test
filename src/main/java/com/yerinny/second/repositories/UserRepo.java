package com.yerinny.second.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yerinny.second.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	User findByEmail(String email);
	
	@Query("SELECT i.content,i.id , u.firstName, u.lastName FROM Idea i JOIN i.user u")
	List<Object[]>findAllIdeasWithTheirUser();
}
