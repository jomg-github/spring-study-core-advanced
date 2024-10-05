package juststudy.springadvanced.aop.order.myaspect.v6;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component("myAspectV6")
public class MyAspect {

//    @Around("juststudy.springadvanced.aop.myaspect.v6.PointCutFactory.inOrderProcess()")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("[log] {}", joinPoint.getSignature());
//        return joinPoint.proceed();
//    }

//    @Around("juststudy.springadvanced.aop.myaspect.v6.PointCutFactory.serviceClassInOrderProcess()")
//    public Object transactional(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            // @Before
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//
//            Object result = joinPoint.proceed();
//
//            // @AfterReturning
//            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//            return result;
//        } catch (Exception e) {
//            // @AfterThrowing
//            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
//            throw e;
//        } finally {
//            // @After
//            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
//        }
//    }

    @Before("juststudy.springadvanced.aop.order.myaspect.v6.PointCutFactory.serviceClassInOrderProcess()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before log] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "juststudy.springadvanced.aop.order.myaspect.v6.PointCutFactory.serviceClassInOrderProcess()", returning = "result")
    public void doAfterReturn(JoinPoint joinPoint, Object result) {
        log.info("[after returning log] {}, return = {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "juststudy.springadvanced.aop.order.myaspect.v6.PointCutFactory.serviceClassInOrderProcess()", throwing = "ex")
    public void doAfterReturn(JoinPoint joinPoint, Exception ex) {
        log.info("[after throwing log] {}, exception = {}", joinPoint.getSignature(), ex);
    }

    @After(value = "juststudy.springadvanced.aop.order.myaspect.v6.PointCutFactory.serviceClassInOrderProcess()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after log] {}", joinPoint.getSignature());
    }
}
