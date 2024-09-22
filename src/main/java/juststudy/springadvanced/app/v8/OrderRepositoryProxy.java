package juststudy.springadvanced.app.v8;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import org.springframework.beans.factory.annotation.Qualifier;

public class OrderRepositoryProxy extends OrderRepository {

    private final OrderRepository target;
    private final LogTraceService logTraceService;

    public OrderRepositoryProxy(@Qualifier("orderRepositoryV8") OrderRepository orderRepository,
                                @Qualifier("logTraceServiceV8") LogTraceService logTraceService) {
        this.target = orderRepository;
        this.logTraceService = logTraceService;
    }

    @Override
    public void save(String itemId) {
        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = logTraceService.begin("OrderRepository.save()");
            target.save(itemId);
            logTraceService.end(logTraceStatus);
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }
    }
}
