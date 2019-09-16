package com.yerinny.second.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.yerinny.second.models.Idea;
import com.yerinny.second.models.User;
import com.yerinny.second.repositories.IdeaRepo;
import com.yerinny.second.repositories.UserRepo;

@Service
public class UserIdeaService {
	
	private final UserRepo uR;
	private final IdeaRepo iR;
	
	public UserIdeaService(UserRepo uR, IdeaRepo iR) {
		this.uR = uR;
		this.iR = iR;
	}
	
	//find user by email 
	public User findByEmail(String email) {
		return uR.findByEmail(email);
		}
		//find user by id
	public User findUserById(Long id) {
		Optional<User> u = uR.findById(id);
			
		if(u.isPresent()) {
			return u.get();
		}else {
			return null;
		}
	}
	//register user and hash their password
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return uR.save(user);
	}
		
		//authenticate user
	public boolean checkAuthenticated(String email,String password) {
		User user = uR.findByEmail(email);
		if(user == null) {
			return false;
		}
		else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	//finding all courses
		public Iterable<Idea> allIdeas(){
			return iR.findAll();
		}
		
	public Idea addIdea(Idea idea) {
		return iR.save(idea);
	}
	public Idea addCourse(Idea idea) {
		return iR.save(idea);
	}

	
	//finding the course by id
	public Idea findIdeaById(Long id) {
		 if (iR.findById(id).isPresent()) {
			 return iR.findById(id).get();
		 }
		 else {
			 return null;
		 }
	}

	public void deleteIdeaById(Long id) {
		iR.deleteById(id);
	}
	
	public List<Object[]>getAllIdeasWithTheirUser(){
		return uR.findAllIdeasWithTheirUser();
	}
	

	
}
