package com.xqx.frame.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xqx.frame.model.TUser;
import com.xqx.frame.service.UserManager;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserManager um;

	@Override
	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		TUser tuser = um.findTUserByLoginName(loginName);
		if (tuser == null) {
			throw new UsernameNotFoundException("用户名不正确。");
		}

		return tuser;
	}

}
