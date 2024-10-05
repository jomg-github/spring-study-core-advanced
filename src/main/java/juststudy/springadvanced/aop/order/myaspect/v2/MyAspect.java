package juststudy.springadvanced.aop.order.myaspect.v2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
//@Component("aspectV2")
public class MyAspect {

    @Pointcut("execution(* juststudy.springadvanced.aop.order..*(..))")
    private void inOderProcess() {} // PointCut Signature

    @Around("inOderProcess()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

}
