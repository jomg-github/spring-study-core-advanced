package juststudy.springadvanced.trace.v9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final LogTraceService logTraceService;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = logTraceService.begin(method.getDeclaringClass().getSimpleName() + method.getName() + "()");
            Object result = method.invoke(target, args);
            logTraceService.end(logTraceStatus);
            return result;
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }
    }
}
