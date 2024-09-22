package juststudy.springadvanced.app.v8.config;

import juststudy.springadvanced.app.v8.*;
import juststudy.springadvanced.trace.LogTraceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean("orderControllerV8")
    public OrderController orderController(@Qualifier("logTraceServiceV8") LogTraceService logTraceService) {
        OrderController orderController = new OrderController(orderService(logTraceService));
        return new OrderControllerProxy(orderController, logTraceService);
    }

    @Bean("orderServiceV8")
    public OrderService orderService(@Qualifier("logTraceServiceV8") LogTraceService logTraceService) {
        OrderService orderService = new OrderService(orderRepository(logTraceService));
        return new OrderServiceProxy(orderService, logTraceService);
    }

    @Bean("orderRepositoryV8")
    public OrderRepository orderRepository(@Qualifier("logTraceServiceV8") LogTraceService logTraceService) {
        OrderRepository orderRepository = new OrderRepository();
        return new OrderRepositoryProxy(orderRepository, logTraceService);
    }

}
