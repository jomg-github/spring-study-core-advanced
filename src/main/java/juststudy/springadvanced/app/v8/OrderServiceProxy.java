package juststudy.springadvanced.app.v8;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import org.springframework.beans.factory.annotation.Qualifier;

public class OrderServiceProxy extends OrderService {

    private final OrderService target;
    private final LogTraceService logTraceService;

    public OrderServiceProxy(@Qualifier("orderRepositoryV8") OrderService orderService,
                             @Qualifier("logTraceServiceV8") LogTraceService logTraceService) {
        super(null);
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
