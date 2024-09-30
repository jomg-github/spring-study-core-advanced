package juststudy.springadvanced.advisor;

import java.lang.reflect.Method;
import juststudy.springadvanced.common.Service;
import juststudy.springadvanced.common.ServiceImpl;
import juststudy.springadvanced.common.advice.TimeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author : jomg
 * @description :
 * @packageName : juststudy.springadvanced.advisor
 * @fileName : AdvisorTest
 * @date : 9/30/24
 */
@Slf4j
public class AdvisorTest {

    @Test
    public void defaultAdvisorTest() {
        // given
        Service service = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(service);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        Service proxy = (Service) proxyFactory.getProxy();

        // when
        // then
        proxy.find();
        proxy.save();
    }

    @Test
    public void manualPointCutAdvisorTest() {
        // given
        Service service = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(service);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        Service proxy = (Service) proxyFactory.getProxy();

        // when
        // then
        proxy.find();
        proxy.save();
    }

    static class MyPointCut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {

        private String matchName = "save";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result = method.getName().equals(matchName);
            log.info("포인트컷 호출 method = {}, targetClass = {}", method.getName(), targetClass);
            log.info("포인트컷 결과 result = {}", result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }

}
