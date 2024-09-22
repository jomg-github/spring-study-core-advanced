package juststudy.springadvanced.app.v8;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

public class OrderControllerProxy extends OrderController {

    private final OrderController target;
    private final LogTraceService logTraceService;

    public OrderControllerProxy(@Qualifier("orderControllerV8") OrderController target,
                                @Qualifier("logTraceServiceV8") LogTraceService logTraceService) {
        super(null);
        this.target = target;
        this.logTraceService = logTraceService;
    }

    @Override
    public ResponseEntity order(String itemId) {
        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = logTraceService.begin("OrderController.order()");
            ResponseEntity result = target.order(itemId);
            logTraceService.end(logTraceStatus);
            return result;
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }
    }

    @Override
    public ResponseEntity noLogOrder(String itemId) {
        return target.noLogOrder(itemId);
    }
}
