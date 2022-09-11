package com.cognizant.service;

import java.util.ArrayList;

import com.cognizant.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.model.Admin;
import com.cognizant.repository.AdminRepository;

@Service
public class AdminDetailService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminDetailService.class);

	private AdminRepository adminRepository;

	@Autowired
	public AdminDetailService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	/*loadbyUserName function loads user from the repository 
	 * returns UserDetails 
	 */

	
	//loading user name from user database passing to spring provided UserDetails  
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
		LOGGER.info("STARTED - loadUserByUsername");
		Admin admin = adminRepository.findByUserName(username);
		LOGGER.info("END - loadUserByUsername");
		return new org.springframework.security.core.userdetails.User(admin.getUserName(), admin.getPassword(),
				new ArrayList<>());
		
		}catch(Exception e)
		{
			LOGGER.error("ERROR-username not found");
			throw new ResourceNotFoundException("Username not found given by Admin");
		}
	}
}
