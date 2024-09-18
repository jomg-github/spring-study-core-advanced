package juststudy.springadvanced.app.v6;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.v6.LogTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v6")
@RestController("orderControllerV6")
public class OrderController {
    private final OrderService orderService;
    private final LogTemplate logTemplate;

    public OrderController(@Qualifier("orderServiceV6") OrderService orderService,
                           @Qualifier("logTraceServiceV6") LogTraceService logTraceService) {
        this.orderService = orderService;
        this.logTemplate = new LogTemplate(logTraceService);
    }

    @GetMapping("/order")
    public ResponseEntity order(String itemId) {
        return ResponseEntity.ok(logTemplate.execute(this.getClass().getSimpleName() + ".order()",
                () -> {
                    orderService.order(itemId);
                    return "ok";
                }
        ));
    }

}
