package com.demo.comentoStatistic.aop;

import com.demo.comentoStatistic.exception.DaoAccessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DaoAspect {

    // around에 넣은 것이 pointcut(advice가 적용될 장소 지정)
    // execution: 메소드 실행을 위해서 포인트 컷을 정의하는 키워드
    @Around("execution(* com.demo.comentoStatistic.dao.*.*(..))")
    public Object daoAround(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable t) {
            t.getStackTrace();
            log.error("Exception in method {}: {}", joinPoint.getSignature(), t.getMessage());
            throw new DaoAccessException();
        }
    }

}
