package com.cg.pwa.test;

import com.cg.pwa.bean.WalletUser;
import com.cg.pwa.service.WalletServiceImpl;
import com.cg.pwa.service.WalletServiceIntf;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class Testing {
	

 WalletUser user;
	WalletServiceIntf service;
	@BeforeAll
	public void setData() {
		service=new WalletServiceImpl();
		user =new WalletUser();
		user.setUserName("test");
		
	}
	
	@DisplayName("User Valid Testcase")
	@Test
	public void isUserValid() {
	assertEquals(true, user.getUserName().equals("test"));
	}
	@DisplayName("addition test case")
	@Test	
	public void firsttest() {
		assertEquals(2,1+1);
	}
}
