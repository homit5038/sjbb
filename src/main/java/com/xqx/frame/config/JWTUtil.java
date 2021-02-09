package com.xqx.frame.config;

import com.xqx.frame.config.auth.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**tokenHead
 * JWT工具类:用于生成Token，和Token验证
 * @author 黄邦荣
 * @date 2019/8/2015:52
 */

@Slf4j
@Component
@Data
public class JWTUtil {

	/**
	 * 密钥
	 */
//	header: Authorization
//  # 密匙key
//	secret: it10000
//  # 过期时间   单位秒
//	expiration: 3600
//			# 自定义token 前缀字符
//	tokenHead: lwx-
//			# 超时时间   单位秒
//	accessToken: 3600
//			# 刷新token时间   单位秒
//	refreshToken: 3600

	private static String secret = "InMySchoolOnline";
	/**
	 * 存放Token的Header
	 */
	private String header = "Authorization";
	/**
	 * 过期时间
	 */
	private long expiration = 3600;
	/**
	 * token前缀
	 */
	private String tokenHead;
	/**
	 * 超过时间
	 */
	private long accessToken;
	/**
	 * 刷新时间
	 */
	private long refreshToken;

	/**
	 * 从数据声明生成令牌
	 *
	 * @param claims 数据声明
	 * @return 令牌
	 */
	private String generateToken(Map<String, Object> claims) {
		Date expirationDate = new Date(System.currentTimeMillis() + (expiration * 1000));
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * 从令牌中获取数据声明
	 *
	 * @param token 令牌
	 * @return 数据声明
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * 生成令牌
	 *
	 * @param userDetails 用户
	 * @return 令牌
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>(2);
		claims.put("key", userDetails.getUsername());
		claims.put("sub", userDetails.getUsername());
		claims.put("exp", new Date());
		return generateToken(claims);
	}

	/**
	 * 从令牌中获取用户名
	 *
	 * @param token 令牌
	 * @return 用户名
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * 判断令牌是否过期
	 *
	 * @param token 令牌
	 * @return 是否过期
	 */
	public Boolean isTokenExpired(String token) {
		try {
			Claims claims = getClaimsFromToken(token);
			Date expiration = claims.getExpiration();
			return expiration.before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 刷新令牌
	 *
	 * @param token 原令牌
	 * @return 新令牌
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put("created", new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * 验证令牌
	 *
	 * @param token       令牌
	 * @param userDetails 用户
	 * @return 是否有效
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		CustomUserDetails user = (CustomUserDetails) userDetails;
		String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}

}
 