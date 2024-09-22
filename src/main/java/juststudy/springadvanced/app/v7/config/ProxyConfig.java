package juststudy.springadvanced.app.v7.config;

import juststudy.springadvanced.app.v7.*;
import juststudy.springadvanced.trace.LogTraceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {

    @Bean
    public OrderController orderController(@Qualifier("logTraceServiceV7") LogTraceService logTraceService) {

        OrderControllerImpl orderController = new OrderControllerImpl(orderService(logTraceService));
        return new OrderControllerProxy(orderController, logTraceService);
    }

    @Bean
    public OrderService orderService(@Qualifier("logTraceServiceV7") LogTraceService logTraceService) {
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository(logTraceService));
        return new OrderServiceProxy(orderService, logTraceService);
    }

    @Bean
    public OrderRepository orderRepository(@Qualifier("logTraceServiceV7") LogTraceService logTraceService) {
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        return new OrderRepositoryProxy(orderRepository, logTraceService);
    }

}
