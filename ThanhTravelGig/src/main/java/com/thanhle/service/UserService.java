package com.thanhle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thanhle.domain.User;


public interface UserService {

	public List<User> findAll();
	public User save(User user);
	public void deleteUserById(long uId);
	public User findByUserId(long uId);
	public User findByUserName(String userName);
	public User findByEmail(String email);
	//public User getUserByUsername(String username);
	
	
}
