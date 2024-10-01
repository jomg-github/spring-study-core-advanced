package juststudy.springadvanced.app.v11.config;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.v10.LogTraceAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PostProcessorConfig {

    private static final String BASE_PACKAGE = "juststudy.springadvanced.app.v11";

    @Bean
    public LogTracePostProcessor logTracePostProcessor(@Qualifier("logTraceServiceV10") LogTraceService logTraceService) {
        return new LogTracePostProcessor(BASE_PACKAGE, advisor(logTraceService));
    }

    private Advisor advisor(LogTraceService logTraceService) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("order*", "request*", "save*");

        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTraceService);
        return new DefaultPointcutAdvisor(logTraceAdvice);
    }
}
