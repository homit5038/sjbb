package com.xqx.frame.security;

import com.xqx.frame.config.JWTUtil;
import com.xqx.frame.config.auth.CustomUserDetailsServiceImpl;
import com.xqx.frame.model.TUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class TokenHandleInterceptor implements HandlerInterceptor {

    @Resource
    private CustomUserDetailsServiceImpl customuserDetailsService;

    @Resource
    private JWTUtil jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        String uri = request.getRequestURI();
        String authHeader = request.getHeader(jwtUtils.getHeader());
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        // jwt不为空则验证用户,否则直接转发到下一个filter
        if (StringUtils.isNotEmpty(authHeader)) {
            // 根据JWT获取用户名
            String username = jwtUtils.getUsernameFromToken(authHeader);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 检查用户是否存在
                UserDetails userDetails = customuserDetailsService.loadUserByUsername(username);
                if (jwtUtils.validateToken(authHeader, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }
        }else {
            //return false;
        }

     System.out.println("===============33333333333===================="+  jwtUtils.getUsernameFromToken(authHeader));

        System.out.println("===============555===================="+(principal));
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle run!");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion run!");
    }

}