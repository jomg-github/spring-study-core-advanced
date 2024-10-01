package juststudy.springadvanced.trace.v10;

import java.lang.reflect.Method;
import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

    private final LogTraceService logTraceService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LogTraceStatus logTraceStatus = null;

        try {
            Method method = invocation.getMethod();
            String message = method.getDeclaringClass().getSimpleName() + method.getName() + "()";
            logTraceStatus = logTraceService.begin(message);
            Object result = invocation.proceed();
            logTraceService.end(logTraceStatus);

            return result;
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }
    }
}
