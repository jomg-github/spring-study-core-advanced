package juststudy.springadvanced.aop.pointcut;

import juststudy.springadvanced.aop.pointcut.ParameterTest.ParameterAspect;
import juststudy.springadvanced.aop.pointcut.annotations.ClassAop;
import juststudy.springadvanced.aop.pointcut.annotations.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ParameterAspect.class)
@SpringBootTest
class ParameterTest {

    @Autowired
    MemberServiceImpl memberService;

    @Test
    void pointcut_test() {
        log.info("memberService class={}", memberService.getClass());
        memberService.find("name");
    }

    @Aspect
    @Slf4j
    static class ParameterAspect {
        @Pointcut("execution(* juststudy.springadvanced.aop.pointcut..*.*(..))")
        private void inMemberPackages() {}

        // 매개변수를 전달
        @Around("inMemberPackages()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg = joinPoint.getArgs()[0];
            log.info("[logArgs1] {}, arg = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        // 매개변수를 전달
        @Around("inMemberPackages() && args(arg, ..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2] {}, arg = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        // 매개변수를 전달 축약 버전, String으로 타입 제한
        @Before("inMemberPackages() && args(arg, ..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] arg = {}", arg);
        }

        // 프록시 객체를 전달
        @Before("inMemberPackages() && this(obj)")
        public void logArgs4(JoinPoint joinPoint, MemberService obj) {
            log.info("[logArgs4] {}, [this] obj = {}", joinPoint.getSignature(), obj.getClass());
        }

        // 실제 프록시 대상 객체를 전달
        @Before("inMemberPackages() && target(obj)")
        public void logArgs5(JoinPoint joinPoint, MemberService obj) {
            log.info("[logArgs5] {}, [target] obj = {}", joinPoint.getSignature(), obj.getClass());
        }

        // 메소드의 애노테이션을 전달
        @Before("inMemberPackages() && @target(annotation)")
        public void logArgs6(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[logArgs6] {}, [annotation] annotation = {}", joinPoint.getSignature(), annotation);
        }

        // 메소드의 애노테이션을 전달
        @Before("inMemberPackages() && @within(annotation)")
        public void logArgs7(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[logArgs7] {}, [@within] annotation = {}", joinPoint.getSignature(), annotation);
        }

        // 메소드의 애노테이션을 전달
        @Before("inMemberPackages() && @annotation(annotation)")
        public void logArgs8(JoinPoint joinPoint, MethodAop annotation) {
            log.info("[logArgs8] {}, [@annotation] annotation = {}, annotationValue = {}", joinPoint.getSignature(), annotation, annotation.value());
        }
    }
}