package cn.baizhi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CaCheAspect {

    @Around("execution(* cn.baizhi.service.*Impl.*(..))")
    public Object addCache(ProceedingJoinPoint joinPoint){
        System.out.println("进入环绕通知");

        return null;
    }

}
