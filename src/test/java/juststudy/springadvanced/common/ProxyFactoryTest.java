package juststudy.springadvanced.common;

import static org.assertj.core.api.Assertions.assertThat;

import juststudy.springadvanced.common.advice.TimeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

/**
 * @author : jomg
 * @description :
 * @packageName : juststudy.springadvanced.common
 * @fileName : ProxyFactoryTest
 * @date : 9/30/24
 */
@Slf4j
public class ProxyFactoryTest {

    @Test
    public void interfaceProxy() {
        // given
        // when
        Service target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        Service proxy = (Service) proxyFactory.getProxy();
        log.info("target = {}", target.getClass());
        log.info("proxy = {}", proxy.getClass());

        proxy.find();
        proxy.save();

        // then
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    public void concreteProxy() {
        // given
        // when
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("target = {}", target.getClass());
        log.info("proxy = {}", proxy.getClass());

        proxy.call();

        // then
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("인터페이스 기반이지만 구현클래스를 기반으로 프록시를 생성하도록 설정")
    public void proxyTargetTest() {
        // given
        // when
        Service target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        proxyFactory.setProxyTargetClass(true);
        Service proxy = (Service) proxyFactory.getProxy();
        log.info("target = {}", target.getClass());
        log.info("proxy = {}", proxy.getClass());

        proxy.find();
        proxy.save();

        // then
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

}
