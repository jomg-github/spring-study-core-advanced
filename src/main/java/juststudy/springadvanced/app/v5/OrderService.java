package juststudy.springadvanced.app.v5;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.v5.AbstractLogTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV5")
public class OrderService {

    private final OrderRepository orderRepository;
    private final LogTraceService logTraceService;

    public OrderService(@Qualifier("orderRepositoryV5") OrderRepository orderRepository,
                        @Qualifier("logTraceServiceV5") LogTraceService logTraceService) {
        this.orderRepository = orderRepository;
        this.logTraceService = logTraceService;
    }

    public void order(String itemId) {
        AbstractLogTemplate<Void> template = new AbstractLogTemplate<>(logTraceService) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute(this.getClass().getSimpleName() + ".order()");
    }
}
