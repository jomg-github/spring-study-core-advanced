package juststudy.springadvanced.app.v2;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.TraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v2")
@RestController("OrderControllerV2")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final TraceService traceService;

    @GetMapping("/order")
    public ResponseEntity order(String itemId) {
        LogTraceStatus trace = null;

        try {
            trace = traceService.begin(this.getClass().getSimpleName() + ".order()");
            orderService.order(itemId, trace.getLogTrace());
            traceService.end(trace);
            return ResponseEntity.ok(itemId + " is ordered.");
        } catch (Exception e) {
            traceService.exception(trace, e);
            throw e;
        }
    }
}
