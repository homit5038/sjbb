package com.xqx.frame.config.auth;

import com.xqx.frame.model.TUser;
import com.xqx.frame.model.TUserRole;
import com.xqx.frame.service.UserManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        TUser tuser = userManager.findTUserByLoginName(loginName);
        if (tuser == null) {
            throw new UsernameNotFoundException("用户名不正确。"+loginName);
        }


        List<TUserRole> userRoles = userManager.selectByUserId(tuser.getId());
        for (TUserRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
        }


        return new CustomUserDetails(tuser, authorities);
    }




}
