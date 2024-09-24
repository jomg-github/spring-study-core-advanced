package juststudy.springadvanced.trace.v9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTraceService logTraceService;
    private final String[] patterns;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = logTraceService.begin(method.getDeclaringClass().getSimpleName() + "." + methodName + "()");
            Object result = method.invoke(target, args);
            logTraceService.end(logTraceStatus);
            return result;
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }
    }
}
