package juststudy.springadvanced.aop.pointcut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import juststudy.springadvanced.aop.pointcut.AtTargetAndAtWithinTest.AtTargetAndAtWithinConfig;
import juststudy.springadvanced.aop.pointcut.annotations.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
class AtTargetAndAtWithinTest {

    @Autowired
    ChildClass childClass;

    // @target : 인스턴스 기준 모든 메소드의 조인 포인트를 선정하며 부모타입의 메소드에도 적용
    // @within : 선택된 클래스 내부에 있는 메소드만 조인 포인트로 선정, 부모타입의 메소드는 적용되지 않음

    @Test
    void pointcut_test() {
        log.info("child class={}", childClass.getClass());
        childClass.childMethod();
        childClass.parentMethod();
    }

    static class ParentClass {
        public void parentMethod() {
            log.info("parentMethod()");
        }
    }

    @TestAnnotation
    static class ChildClass extends ParentClass {
        public void childMethod() {
            log.info("childMethod()");
        }
    }

    @Aspect
    @Slf4j
    static class AtTargetAndAtWithinAspect {
        // 부모 타입 메소드에도 적용되는 AOP
        @Around("execution(* juststudy.springadvanced.aop.pointcut.AtTargetAndAtWithinTest..*.*(..)) && @target(juststudy.springadvanced.aop.pointcut.AtTargetAndAtWithinTest.TestAnnotation)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // 선택된 클래스의 메소드에만 적용되는 AOP
        @Around("execution(* juststudy.springadvanced.aop.pointcut.AtTargetAndAtWithinTest..*.*(..)) && @within(juststudy.springadvanced.aop.pointcut.AtTargetAndAtWithinTest.TestAnnotation)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @TestConfiguration
    static class AtTargetAndAtWithinConfig {
        @Bean
        public ParentClass parentClass() {
            return new ParentClass();
        }

        @Bean
        public ChildClass childClass() {
            return new ChildClass();
        }

        @Bean
        public AtTargetAndAtWithinAspect atTargetAndAtWithinAspect() {
            return new AtTargetAndAtWithinAspect();
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {}
}