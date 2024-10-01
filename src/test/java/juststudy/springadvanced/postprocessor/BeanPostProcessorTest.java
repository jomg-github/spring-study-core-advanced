package juststudy.springadvanced.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfigTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        MyBeanB myBeanB = ac.getBean("beanA", MyBeanB.class);
        myBeanB.hello();

        Assertions.assertThatThrownBy(() -> ac.getBean(MyBeanA.class));
    }

    @Configuration
    static class BeanPostProcessorConfig {
        @Bean("beanA")
        public MyBeanA myBean() {
            return new MyBeanA();
        }

        @Bean
        public MyBeanPostProcessor myBeanPostProcessor() {
            return new MyBeanPostProcessor();
        }
    }

    @Slf4j
    static class MyBeanPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);
            if (bean instanceof MyBeanA) {
                return new MyBeanB();
            }

            return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
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
