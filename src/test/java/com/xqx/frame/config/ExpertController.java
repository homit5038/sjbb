package com.xqx.frame.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TUser;
import com.xqx.frame.service.UserManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/root-context.xml"}) 
public class ExpertController {
	
	@Autowired
	UserManager userDao;
	
	@Test
	public void addExpert(){
		TUser user =new TUser();
		user.setLoginName("test22");
		user.setName("iosindsjhb");
		user.setPassword("1234");
		try {
			userDao.saveUser(user,null);
		} catch (ParameterCheckException e) {
			e.printStackTrace();
		}
	}
	

	public static void  main(String[] args){
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("1234"));
	}
}
