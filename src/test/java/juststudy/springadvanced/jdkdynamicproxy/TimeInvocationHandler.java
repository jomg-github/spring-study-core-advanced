package juststudy.springadvanced.jdkdynamicproxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    /**
     *  JDK 동적 프록시
     *
     *  -   직접 프록시 객체를 생성하는 경우, AInterface, BInterface에 해당하는 프록시를 각각 생성해줘야 한다.
     *      하지만 리플렉션 기반의 JDK 동적 프록시를 이용한다면 InvocationHandler만 구현하면 모든 target에 적용할 수 있다.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeInvocationHandler 객체 호출");
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        log.info("실행 시간 : {}ms", endTime - startTime);

        return result;
    }
}
