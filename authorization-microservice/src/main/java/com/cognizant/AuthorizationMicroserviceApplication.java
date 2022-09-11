package com.cognizant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.model.Admin;
import com.cognizant.repository.AdminRepository;

@SpringBootApplication
@EnableFeignClients
public class AuthorizationMicroserviceApplication {

	private AdminRepository repository;
	
	
	@Autowired
	public AuthorizationMicroserviceApplication(AdminRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void initUser() {
		List<Admin> admins = Stream.of(new Admin(1, "Amit", "amit1234"),
				new Admin(2, "Abhishek", "abhi1234"),
				new Admin(3,"Kanishk","dwaj1234"),
				new Admin(4,"Vasisht","vasi1234")).collect(Collectors.toList());
		        repository.saveAll(admins);
	}

	public static void main(String[] args) {

		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
	}
}
