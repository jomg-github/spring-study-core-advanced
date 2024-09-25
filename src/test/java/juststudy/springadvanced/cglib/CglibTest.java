package juststudy.springadvanced.cglib;

import juststudy.springadvanced.common.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author : jomg
 * @description :
 * @packageName : juststudy.springadvanced.cglib
 * @fileName : CglibTest
 * @date : 9/25/24
 */
@Slf4j
public class CglibTest {

    @Test
    public void concrete_cglib() {
        // given
        // when
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));

        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("target class = {}", target.getClass());
        log.info("proxy class = {}", proxy.getClass());

        // then
        proxy.call();
    }

}
