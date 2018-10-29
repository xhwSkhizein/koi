package com.hongv.koi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author xuhongwei5
 * @since 2018/10/29 17:53
 */
@Aspect
@Component
public class ServiceAspect {
    private final static Logger log = LoggerFactory.getLogger(ServiceAspect.class);

    /**
     * Pointcut designators(PCD)
     * 1. execution: matches method execution join points.
     * 以 [* com.hongv.koi.*(..)] 为例：
     * a. 第一个 * 代表所有返回值;
     * b. com.hongv.koi.* 代表匹配所有这个包路径下的方法名
     * c. (..) 代表匹配所有参数列表（0个或多个）
     * 以 [public String org.springframework.xx.Foo.getBar(Long)] 为例：
     * 绝对匹配 Foo 的 getBar 方法，且此方法入参为一个 Long 值
     * eg: @Pointcut("execution(* *..find*(Long,..))")
     * <p>
     * 2. within: 指定了范围
     * eg: @Pointcut("within(org.hongv.koi..)") koi 下所有包及其子包
     * eg: @Pointcut("within(org.hongv.dao.FooDao)") FooDao 下所有方法
     * <p>
     * 3. target
     * 4. this
     * 5. @target
     * eg: @Pointcut("@target(org.springframework.stereotype.Repository)")
     * 6. @args
     * eg: @Pointcut("@args(org.baeldung.aop.annotations.Entity)")
     * 7. @within
     * eg: @Pointcut("@within(org.springframework.stereotype.Repository)")
     * Which is equivalent to: @Pointcut("within(@org.springframework.stereotype.Repository *)")
     * <p>
     * 8. @annotation
     * eg: @Pointcut("@annotation(org.baeldung.aop.annotations.Loggable)")
     * <p>
     * 可以使用 || or && or ! 组合 pointcut expression,
     * eg: @Pointcut("foo() && bar() && !xyz()")
     */
    @Pointcut("execution(* com.hongv.koi.word.service..*(..))")
    public void aspect() {
    }

    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("before " + joinPoint);
        }
    }

    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("after " + joinPoint);
        }
    }

    @Around("aspect()")
    public Object around(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            Object returnObj = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
            return returnObj;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            }
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Class<?> returnType = method.getReturnType();
            if (returnType.isAssignableFrom(Collection.class)) {
                return Collections.emptyList();
            } else if (returnType.isAssignableFrom(Map.class)) {
                return Collections.emptyMap();
            } else {
                return null;
            }
        }
    }

    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("afterReturn " + joinPoint);
        }
    }

    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        if (log.isInfoEnabled()) {
            log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
        }
    }

}
