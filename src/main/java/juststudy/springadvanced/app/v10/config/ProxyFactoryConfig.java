package juststudy.springadvanced.app.v10.config;

import juststudy.springadvanced.app.v10.OrderController;
import juststudy.springadvanced.app.v10.OrderRepository;
import juststudy.springadvanced.app.v10.OrderService;
import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.v10.LogTraceAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfig {

    @Bean("orderControllerV10")
    public OrderController orderController(@Qualifier("logTraceServiceV10") LogTraceService logTraceService) {
        OrderController orderController = new OrderController(orderService(logTraceService));
        ProxyFactory proxyFactory = new ProxyFactory(orderController);
        proxyFactory.addAdvisor(advisor(logTraceService));

        return (OrderController) proxyFactory.getProxy();
    }

    @Bean("orderServiceV10")
    public OrderService orderService(@Qualifier("logTraceServiceV10") LogTraceService logTraceService) {
        OrderService orderService = new OrderService(orderRepository(logTraceService));
        ProxyFactory proxyFactory = new ProxyFactory(orderService);
        proxyFactory.addAdvisor(advisor(logTraceService));

        return (OrderService) proxyFactory.getProxy();
    }

    @Bean("orderRepositoryV10")
    public OrderRepository orderRepository(@Qualifier("logTraceServiceV10") LogTraceService logTraceService) {
        OrderRepository orderRepository = new OrderRepository();
        ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
        proxyFactory.addAdvisor(advisor(logTraceService));

        return (OrderRepository) proxyFactory.getProxy();
    }

    private Advisor advisor(LogTraceService logTraceService) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("order*", "request*", "save*");

        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTraceService);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

}
