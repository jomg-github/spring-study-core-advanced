package juststudy.springadvanced.app.v5;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.v5.AbstractLogTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v5")
@RestController("orderControllerV5")
public class OrderController {
    private final OrderService orderService;

    private final LogTraceService logTraceService;

    public OrderController(@Qualifier("orderServiceV5") OrderService orderService,
                           @Qualifier("logTraceServiceV5") LogTraceService logTraceService) {
        this.orderService = orderService;
        this.logTraceService = logTraceService;
    }

    @GetMapping("/order")
    public ResponseEntity order(String itemId) {
        AbstractLogTemplate<String> template = new AbstractLogTemplate<>(logTraceService) {
            @Override
            protected String call() {
                orderService.order(itemId);
                return "ok";
            }
        };

        return ResponseEntity.ok(template.execute(this.getClass().getSimpleName() + ".order()"));
    }
}
