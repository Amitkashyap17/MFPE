package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUserName(String username);

}
