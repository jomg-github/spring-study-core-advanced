package juststudy.springadvanced.cglib;

import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author : jomg
 * @description :
 * @packageName : juststudy.springadvanced.cglib
 * @fileName : TimeMethodIntercepto
 * @date : 9/25/24
 */
@RequiredArgsConstructor
@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("TimeMethodInterceptor 객체 호출");
        long startTime = System.currentTimeMillis();
        Object result = proxy.invoke(target, args);
        long endTime = System.currentTimeMillis();
        log.info("실행 시간 : {}ms", endTime - startTime);

        return result;
    }
}
