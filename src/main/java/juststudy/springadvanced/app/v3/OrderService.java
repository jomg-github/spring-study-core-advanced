package juststudy.springadvanced.app.v3;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.LogTraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV3")
public class OrderService {

    private final OrderRepository orderRepository;

    private final LogTraceService logTraceService;

    public OrderService(@Qualifier("orderRepositoryV3") OrderRepository orderRepository,
                        @Qualifier("logTraceServiceV3") LogTraceService logTraceService) {
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
