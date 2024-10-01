package juststudy.springadvanced.advisor;

import juststudy.springadvanced.common.Service;
import juststudy.springadvanced.common.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MultiAdvisorTest {

    @Test
    @DisplayName("어드바이저별로 프록시 생성")
    public void multiAdvisorTest1() {
        // client -> proxy -> advisor2 -> advisor1 -> target
        Service service = new ServiceImpl();
        ProxyFactory proxyFactory1 = new ProxyFactory(service);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvice1());
        proxyFactory1.addAdvisor(advisor1);
        Service proxy1 = (Service) proxyFactory1.getProxy();

        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvice2());
        proxyFactory2.addAdvisor(advisor2);
        Service proxy2 = (Service) proxyFactory2.getProxy();

        proxy2.save();
    }

    @Test
    @DisplayName("하나의 프록시, 여러 어드바이저")
    public void multiAdvisorTest2() {
        // given
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvice2());

        Service service = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);
        Service proxy = (Service) proxyFactory.getProxy();

        // then
        proxy.save();
    }

    @Slf4j
    static class MyAdvice1 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("MyAdvice1 호출");
            return invocation.proceed();
        }
    }
    @Slf4j
    static class MyAdvice2 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("MyAdvice2 호출");
            return invocation.proceed();
        }
    }

}
