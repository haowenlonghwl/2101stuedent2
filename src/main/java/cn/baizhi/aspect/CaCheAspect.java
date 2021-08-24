package cn.baizhi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CaCheAspect {
    @Autowired
    private RedisTemplate redisTemplate;



    @Around("execution(* cn.baizhi.service.*Impl.*(..))")
    public Object addCache(ProceedingJoinPoint joinPoint) {
        System.out.println("进入环绕通知");



        ValueOperations valueOperations = redisTemplate.opsForValue();



        Object obj = null;

        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();



        String name = joinPoint.getTarget().getClass().getName();
        System.out.println(name);


        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);


        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
            sb.append(arg);
        }

        valueOperations.set(sb.toString(), obj);

        System.out.println(sb);

        return obj;
    }
}
