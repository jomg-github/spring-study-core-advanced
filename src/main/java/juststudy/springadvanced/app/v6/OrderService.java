package juststudy.springadvanced.app.v6;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.v6.LogTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV6")
public class OrderService {

    private final OrderRepository orderRepository;
    private final LogTemplate logTemplate;

    public OrderService(@Qualifier("orderRepositoryV6") OrderRepository orderRepository,
                        @Qualifier("logTraceServiceV6") LogTraceService logTraceService) {
        this.orderRepository = orderRepository;
        this.logTemplate = new LogTemplate(logTraceService);
    }

    public void order(String itemId) {
        logTemplate.execute(this.getClass().getSimpleName() + ".order()",
                () -> {
                    orderRepository.save(itemId);
                    return null;
                });
    }
}
