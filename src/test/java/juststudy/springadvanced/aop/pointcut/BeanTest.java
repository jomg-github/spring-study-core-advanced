package juststudy.springadvanced.aop.pointcut;

import juststudy.springadvanced.aop.pointcut.BeanTest.BeanAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(BeanAspect.class)
@SpringBootTest
class BeanTest {

    @Autowired
    MemberServiceImpl memberService;

    @Test
    void pointcut_test() {
        log.info("memberService class={}", memberService.getClass());
        memberService.find("name");
    }

    @Aspect
    @Slf4j
    static class BeanAspect {
        @Around("bean(memberServ*)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[bean] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}