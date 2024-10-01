package juststudy.springadvanced.app.v13.config;

import juststudy.springadvanced.trace.LogTraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(@Qualifier("logTraceServiceV13") LogTraceService logTraceService) {
        return new LogTraceAspect(logTraceService);
    }
}
