package juststudy.springadvanced.aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class DemoAspectConfig {

    @Pointcut("@annotation(juststudy.springadvanced.aop.demo.ApiLogging)")
    private void apiLoggingAnnotation() {}

    @Pointcut("@annotation(retry)")
    private void retryAnnotation(Retry retry) {}

    @Around("apiLoggingAnnotation() && args(args)")
    public Object loggingAspect(ProceedingJoinPoint joinPoint, Object args) throws Throwable {
        log.info("[REQUEST] {}", args);
        Object result = joinPoint.proceed();
        log.info("[RESPONSE] {}", result);
        return result;
    }

    @Around("retryAnnotation(retry)")
    public Object retryAspect(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int maxTryCount = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1 ; retryCount <= maxTryCount; retryCount++) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                log.info("[retry] try count = {}/{}", retryCount, maxTryCount);
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }

}
