package juststudy.springadvanced.aop.order.myaspect.v1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
//@Component("aspectV1")
public class MyAspect {

    @Around("execution(* juststudy.springadvanced.aop.order..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

}
