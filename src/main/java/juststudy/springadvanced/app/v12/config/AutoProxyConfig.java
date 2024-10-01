package juststudy.springadvanced.app.v12.config;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.v12.LogTraceAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AutoProxyConfig {

//    @Bean
//    public Advisor advisor(@Qualifier("logTraceServiceV12") LogTraceService logTraceService) {
//        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
//        pointcut.setMappedNames("order*", "request*", "save*");
//
//        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTraceService);
//        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
//    }

    @Bean
    public Advisor advisor(@Qualifier("logTraceServiceV12") LogTraceService logTraceService) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* juststudy.springadvanced.app..*(..)) && !execution(* juststudy.springadvanced.app..noLogOrder(..))");

        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTraceService);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
