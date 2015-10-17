package com.smart;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.smart.service.UserService;

@ContextConfiguration(locations={"/applicationContext.xml"})
public class UserServiceTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private UserService userService;
	
	@Test
	public void testHasMatchUser() {
		
		boolean b1 = userService.hasMatchUser("admin", "admin123");
		boolean b2 = userService.hasMatchUser("admin", "admin");
		System.out.println(b1+"................."+b2);
		assertTrue(b1);
		assertTrue(!b2);

	}


}
