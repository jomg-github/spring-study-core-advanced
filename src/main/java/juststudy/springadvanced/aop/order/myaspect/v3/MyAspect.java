package juststudy.springadvanced.aop.order.myaspect.v3;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
//@Component("aspectV3")
public class MyAspect {

    @Pointcut("execution(* juststudy.springadvanced.aop.order..*(..))")
    private void inOrderProcess() {} // PointCut Signature

    @Pointcut("execution(* *..*Service.*(..))")
    private void serviceClass() {}

    @Around("inOrderProcess()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    // juststudy.springadvanced.aop.order 패키지 및 하위 패키지이면서
    // 클래스 이름 패턴이 *Service
    @Around("inOrderProcess() && serviceClass()")
    public Object transactional(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

}
