package juststudy.springadvanced.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author : jomg
 * @description :
 * @packageName : juststudy.springadvanced.common.advice
 * @fileName : TimeAdvice
 * @date : 9/30/24
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice 객체 호출");
        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        log.info("실행시간 = {}", endTime - startTime);
        return result;
    }
}
