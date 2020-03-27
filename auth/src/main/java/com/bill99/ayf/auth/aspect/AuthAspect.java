package com.bill99.ayf.auth.aspect;

import com.bill99.ayf.auth.exception.PermissionException;
import com.bill99.ayf.auth.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * andy.an
 * 2020/3/27
 */
@Component
@Aspect
public class AuthAspect {

    private AuthService authService;

    public AuthAspect(AuthService authService) {
        this.authService = authService;
    }

    @Around("execution(* *(..)) && @annotation(com.bill99.ayf.auth.aspect.RequirePermission)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)) {
            throw new PermissionException();
        }

        // TODO
        Long uid = auth2UId(authorization);

        RequirePermission permission = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(RequirePermission.class);
        String value = permission.value();

        // TODO redis?
        boolean flag = authService.checkUserPermission(uid, value);
        if (!flag) {
            throw new PermissionException();
        }

        return point.proceed();
    }

    //TODO redis?
    private Long auth2UId(String authorization) {
        return Long.valueOf(authorization);
    }
}
