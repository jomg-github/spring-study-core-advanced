package juststudy.springadvanced.app.v7;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

public class OrderControllerProxy implements OrderController {

    private final OrderController target;
    private final LogTraceService logTraceService;

    public OrderControllerProxy(@Qualifier("orderControllerV7") OrderController target,
                                @Qualifier("logTraceServiceV7") LogTraceService logTraceService) {
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
