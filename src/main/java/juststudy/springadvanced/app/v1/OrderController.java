package juststudy.springadvanced.app.v1;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.TraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController("OrderControllerV1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final TraceService traceService;

    @GetMapping("/order")
    public ResponseEntity order(String itemId) {
        LogTraceStatus trace = null;

        try {
            trace = traceService.begin(this.getClass().getSimpleName() + ".order()");
            orderService.order(itemId);
            traceService.end(trace);
            return ResponseEntity.ok(itemId + " is ordered.");
        } catch (Exception e) {
            traceService.exception(trace, e);
            throw e;
        }
    }
}
