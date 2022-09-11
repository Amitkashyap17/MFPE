package com.cognizant;


import com.cognizant.service.test.AdminDetailServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cognizant.controller.test.AuthControllerTests;
import com.cognizant.model.test.AuthorizationModelTest;

@RunWith(Suite.class)
@SuiteClasses({ AuthControllerTests.class,AuthorizationModelTest.class, AdminDetailServiceTest.class })
public class AllTests {}
