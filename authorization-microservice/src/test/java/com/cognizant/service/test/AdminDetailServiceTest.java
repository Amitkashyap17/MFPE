package com.cognizant.service.test ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.cognizant.model.Admin;
import com.cognizant.repository.AdminRepository;
import com.cognizant.service.AdminDetailService;

@SpringBootTest
public class AdminDetailServiceTest {

	
	@Mock
	private AdminRepository adminRepository;

	@InjectMocks
	private AdminDetailService service;
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void loadUserByUserNameShouldUserNameTest() {
		when(adminRepository.findByUserName("admin")).thenReturn(new Admin(1,"admin","admin"));
		assertThat(service.loadUserByUsername("admin")).isNotNull();
		verify(adminRepository, Mockito.times(1)).findByUserName("admin");
	}
	
	

	
}
