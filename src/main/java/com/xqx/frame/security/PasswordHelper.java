package com.xqx.frame.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * @author yc
 * 
 * @date 2017-03-14
 * 
 * @Description 密码加密
 *
 */

public class PasswordHelper {
	private static final PasswordEncoder encoder = new StandardPasswordEncoder("my-secret-key");//秘钥值  
    
    public static String encrypt(String rawPassword) {  
         return encoder.encode(rawPassword);  
    }  
   
    public static boolean match(String rawPassword, String password) {  
         return encoder.matches(rawPassword, password);  
    }
}
