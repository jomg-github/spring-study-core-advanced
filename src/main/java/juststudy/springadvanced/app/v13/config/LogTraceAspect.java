package juststudy.springadvanced.app.v13.config;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogTraceService logTraceService;

    @Around("execution(* juststudy.springadvanced.app..*(..)) "
        + "&& !execution(* juststudy.springadvanced.app..noLogOrder(..)) "
        + "&& !execution(* juststudy.springadvanced.app.v13.config.LogTraceAspect(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        LogTraceStatus logTraceStatus = null;

        try {
            String message = joinPoint.getSignature().toShortString();
            logTraceStatus = logTraceService.begin(message);
            Object result = joinPoint.proceed();
            logTraceService.end(logTraceStatus);

            return result;
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }
    }

}
