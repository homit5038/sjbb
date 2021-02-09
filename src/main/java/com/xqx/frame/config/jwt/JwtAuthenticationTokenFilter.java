package com.xqx.frame.config.jwt;

import com.xqx.frame.config.auth.CustomUserDetailsServiceImpl;
import com.xqx.frame.config.JWTUtil;
import org.apache.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 黄邦荣
 * @date 2019/8/21 9:33
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private CustomUserDetailsServiceImpl userDetailsService;

    @Resource
    private JWTUtil jwtUtils;

    /**
     * 这个其实就是用来验证令牌的是否合法
     *
     * @param request  request
     * @param response response
     * @param chain    chain
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取jwt
        String authHeader = request.getHeader(jwtUtils.getHeader());

        // jwt不为空则验证用户,否则直接转发到下一个filter

        if (StringUtils.isNotEmpty(authHeader)) {
            // 根据JWT获取用户名
            String username = jwtUtils.getUsernameFromToken(authHeader);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 检查用户是否存在
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtUtils.validateToken(authHeader, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("===============33333333333===================="+SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.println("===============555===================="+principal);
        // 转发到下一个filter
        chain.doFilter(request, response);
    }

}
