package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhle.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
