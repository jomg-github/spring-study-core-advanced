package juststudy.springadvanced.aop.order.myaspect.v5;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
public class MyAspect {

    @Aspect
    @Order(2)
//    @Component("logAspectV5")
    public static class LogAspect {
        @Around("juststudy.springadvanced.aop.order.myaspect.v5.PointCutFactory.inOrderProcess()")
        public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
//    @Component("transactionAspectV5")
    public static class TransactionAspect {
        @Around("juststudy.springadvanced.aop.order.myaspect.v5.PointCutFactory.serviceClassInOrderProcess()")
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



}
