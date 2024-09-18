package juststudy.springadvanced.app.v4;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV4")
public class OrderService {

    private final OrderRepository orderRepository;
    private final LogTraceService logTraceService;

    public OrderService(@Qualifier("orderRepositoryV4") OrderRepository orderRepository,
                        @Qualifier("logTraceServiceV4") LogTraceService logTraceService) {
        this.orderRepository = orderRepository;
        this.logTraceService = logTraceService;
    }

    public void order(String itemId) {
        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = logTraceService.begin(this.getClass().getSimpleName() + ".order()");
            orderRepository.save(itemId);
            logTraceService.end(logTraceStatus);
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }

    }
}
