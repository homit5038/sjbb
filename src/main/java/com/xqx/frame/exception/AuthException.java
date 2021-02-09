package com.xqx.frame.exception;
import com.xqx.frame.form.QueryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 关于身份验证相关的异常处理
 *
 * @author 黄邦荣
 * @date 2019/8/20 16:27
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class AuthException {

    @ExceptionHandler
    public QueryResult accessDeniedException(AccessDeniedException e) {
//        return new Result(Result.FAILURE, "", "无权限访问");
        return new QueryResult<>("无权限访问");
    }

    @ExceptionHandler
    public QueryResult usernameNotFoundException(UsernameNotFoundException e) {
//        return new Result(Result.FAILURE, "", "用户不存在");
        return new QueryResult<>("用户不存在");
    }

    @ExceptionHandler
    public QueryResult disabledException(DisabledException e) {
//        return new Result(Result.FAILURE, "", "用户已被禁用");
        return new QueryResult<>("用户已被禁用");
    }

    @ExceptionHandler
    public QueryResult badCredentialsException(BadCredentialsException e) {
//        return new Result(Result.FAILURE, "", "密码错22误");
        return new QueryResult<>("密码错22误");
    }

    @ExceptionHandler
    public QueryResult lockedException(LockedException e) {
//        return new Result(Result.FAILURE, "", "账户被锁定");
        return new QueryResult<>("账户被锁定");
    }

    @ExceptionHandler
    public QueryResult accountExpiredException(AccountExpiredException e) {
//        return new Result(Result.FAILURE, "", "账户已过期");
        return new QueryResult<>("账户已过期");
    }

    @ExceptionHandler
    public QueryResult credentialsExpiredException(CredentialsExpiredException e) {
//        return new Result(Result.FAILURE, "", "证书已过期");
        return new QueryResult<>("证书已过期");
    }


}
