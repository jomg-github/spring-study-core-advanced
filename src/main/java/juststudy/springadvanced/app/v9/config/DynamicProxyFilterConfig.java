package juststudy.springadvanced.app.v9.config;

import java.lang.reflect.Proxy;
import juststudy.springadvanced.app.v9.OrderController;
import juststudy.springadvanced.app.v9.OrderControllerImpl;
import juststudy.springadvanced.app.v9.OrderRepository;
import juststudy.springadvanced.app.v9.OrderRepositoryImpl;
import juststudy.springadvanced.app.v9.OrderService;
import juststudy.springadvanced.app.v9.OrderServiceImpl;
import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.v9.LogTraceBasicHandler;
import juststudy.springadvanced.trace.v9.LogTraceFilterHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"order*", "request*", "save*"};

    @Bean("orderControllerV9")
    public OrderController orderController(@Qualifier("logTraceServiceV9") LogTraceService logTraceService) {
        OrderController orderController = new OrderControllerImpl(orderService(logTraceService));

        return (OrderController) Proxy.newProxyInstance(orderController.getClass().getClassLoader(),
                new Class[]{OrderController.class},
                new LogTraceFilterHandler(orderController, logTraceService, PATTERNS)
        );
    }

    @Bean("orderServiceV9")
    public OrderService orderService(@Qualifier("logTraceServiceV9") LogTraceService logTraceService) {
        OrderService orderService = new OrderServiceImpl(orderRepository(logTraceService));

        return (OrderService) Proxy.newProxyInstance(orderService.getClass().getClassLoader(),
                new Class[]{OrderService.class},
                new LogTraceFilterHandler(orderService, logTraceService, PATTERNS)
        );
    }

    @Bean("orderRepositoryV9")
    public OrderRepository orderRepository(@Qualifier("logTraceServiceV9") LogTraceService logTraceService) {
        OrderRepository orderRepository = new OrderRepositoryImpl();

        return (OrderRepository) Proxy.newProxyInstance(orderRepository.getClass().getClassLoader(),
                new Class[]{OrderRepository.class},
                new LogTraceFilterHandler(orderRepository, logTraceService, PATTERNS)
        );
    }

}
