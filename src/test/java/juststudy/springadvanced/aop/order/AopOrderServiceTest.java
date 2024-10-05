package juststudy.springadvanced.aop.order;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopOrderServiceTest {

    @Autowired
    AopOrderService aopOrderService;

    @Autowired
    AopOrderRepository aopOrderRepository;

    @Test
    void aop() {
        assertThat(AopUtils.isAopProxy(aopOrderService)).isTrue();
        assertThat(AopUtils.isAopProxy(aopOrderRepository)).isTrue();
    }

    @Test
    void order_success() {
        assertThatNoException().isThrownBy(() -> aopOrderService.order("item"));

    }

    @Test
    void order_exception() {
        assertThrows(IllegalStateException.class, () -> aopOrderService.order("ex"));
    }

}