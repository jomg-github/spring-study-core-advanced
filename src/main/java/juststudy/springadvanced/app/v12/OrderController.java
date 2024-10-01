package juststudy.springadvanced.app.v12;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("orderControllerV12")
@RequiredArgsConstructor
@RequestMapping("/v12")
public class OrderController {

    @Qualifier("orderServiceV12")
    private final OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity order(String itemId) {
        orderService.order(itemId);
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/order/noLog")
    public ResponseEntity noLogOrder(String itemId) {
        orderService.order(itemId);
        return ResponseEntity.ok("ok");
    }
}
