package com.xqx.frame.config.auth;
import com.xqx.frame.model.TUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author 黄邦荣
 * @date 2019/8/2011:30
 */
public class CustomUserDetails implements UserDetails, Serializable {

    private TUser user;

    private Collection<GrantedAuthority> authorities;

    /**
     * 功能说明:构造
     *
     * @param user
     * @param authorities
     * @return
     * @author 黄邦荣
     * @date 2019/9/4 0004 13:52
     */
    CustomUserDetails(TUser user, Collection<GrantedAuthority> authorities){
        this.user        = user;
        this.authorities = authorities;
    }


    /**
     * 返回分配给用户的角色列表
     *
     * @return 返回分配给用户的角色列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 返回密码
     *
     * @return 返回密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 返回帐号
     *
     * @return 返回帐号
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否未过期
     *
     * @return 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return !user.getIsExpired();
    }

    /**
     * 账户是否未锁定
     *
     * @return 账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return !user.getIsLocked();
    }

    /**
     * 密码是否未过期
     *
     * @return 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getIsCredentialsNonExpired();
    }

    /**
     * 账户是否激活
     *
     * @return 账户是否激活
     */
    @Override
    public boolean isEnabled() {
        return user.getIsEnable();
    }

}
