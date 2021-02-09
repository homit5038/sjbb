package com.xqx.frame.config.auth;

import com.xqx.frame.config.jwt.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Created by hp on 2017/3/4.
 */
@Configuration
@EnableWebSecurity //启用Web安全性配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    /*
    * 定义用户存储，可选的有内存、数据库、LDAP、自定义
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhou").password("123").roles("USER")  //赋予权限
                .and()
                .withUser("admin").password("admin").roles("ADMIN","USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 关闭session,因为使用jwt不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 通过authorizeRequests()方法来开始请求权限配置
                .and().authorizeRequests()
                // 放行OPTIONS请求,因为前后端分离,造成跨域
                // 在放心/** 请求,也就是所有请求. 使用注解控制访问
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and().headers().cacheControl();
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        // 让Spring security放行所有preflight request
      //  registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
    }
}
