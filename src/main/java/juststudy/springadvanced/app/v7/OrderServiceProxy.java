package juststudy.springadvanced.app.v7;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import org.springframework.beans.factory.annotation.Qualifier;

public class OrderServiceProxy implements OrderService {

    private final OrderService target;
    private final LogTraceService logTraceService;

    public OrderServiceProxy(@Qualifier("orderRepositoryV7") OrderService orderService,
                             @Qualifier("logTraceServiceV7") LogTraceService logTraceService) {
        this.target = orderService;
        this.logTraceService = logTraceService;
    }

    @Override
    public void order(String itemId) {
        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = logTraceService.begin("OrderService.order()");
            target.order(itemId);
            logTraceService.end(logTraceStatus);
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }
    }
}
