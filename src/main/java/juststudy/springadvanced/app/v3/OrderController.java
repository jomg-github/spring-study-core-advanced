package juststudy.springadvanced.app.v3;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.LogTraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v3")
@RestController("OrderControllerV3")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Qualifier("logTraceServiceV3")
    private final LogTraceService logTraceService;

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
