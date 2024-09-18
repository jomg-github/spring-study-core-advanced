package juststudy.springadvanced.app.v4;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v4")
@RestController("orderControllerV4")
public class OrderController {
    private final OrderService orderService;

    private final LogTraceService logTraceService;

    public OrderController(@Qualifier("orderServiceV4") OrderService orderService,
                           @Qualifier("logTraceServiceV4") LogTraceService logTraceService) {
        this.orderService = orderService;
        this.logTraceService = logTraceService;
    }

    @GetMapping("/order")
    public ResponseEntity order(String itemId) {
        LogTraceStatus trace = null;

        try {
            trace = logTraceService.begin(this.getClass().getSimpleName() + ".order()");
            orderService.order(itemId);
            logTraceService.end(trace);
            return ResponseEntity.ok(itemId + " is ordered.");
        } catch (Exception e) {
            logTraceService.exception(trace, e);
            throw e;
        }
    }
}
