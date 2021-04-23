package com.shangma.cn.common.perm;

import com.shangma.cn.common.http.EnumStatus;
import com.shangma.cn.common.utils.ServletUtils;
import com.shangma.cn.common.utils.TokenService;
import com.shangma.cn.domin.entity.Menu;
import com.shangma.cn.exception.PermException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 17:31
 * 文件说明：
 */
@Aspect
@Component
public class MyAspect {


    @Autowired
    private TokenService tokenService;


    @Before("pointCut()")
    public void checkPerm(JoinPoint joinPoint) throws IOException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HasPerm annotation = method.getAnnotation(HasPerm.class);
        if (annotation != null) {
            List<Menu> perms = tokenService.getLoginAdmin().getPerms();
            String perm = annotation.perm();
            boolean b = perms.stream().anyMatch(menu -> perm.equals(menu.getPermission()));
            if (!b) {
                throw new PermException(EnumStatus.NO_PERM);
            }


        }


    }

    @Pointcut("@annotation(com.shangma.cn.common.perm.HasPerm)")
    public void pointCut() {

    }
}
