package juststudy.springadvanced.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfigTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);

        MyBeanA myBeanA = ac.getBean("beanA", MyBeanA.class);
        myBeanA.hello();

        Assertions.assertThatThrownBy(() -> ac.getBean("beanB"));
    }

    @Configuration
    static class BasicConfig {
        @Bean("beanA")
        public MyBeanA myBean() {
            return new MyBeanA();
        }
    }

    @Slf4j
    private static class MyBeanA {
        public void hello() {
            log.info("Hello, i'm A.");
        }
    }


    @Slf4j
    private static class MyBeanB {
        public void hello() {
            log.info("Hello, i'm B.");
        }
    }
}
