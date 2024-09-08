package juststudy.springadvanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v0")
@RestController("OrderControllerV0")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity order(String itemId) {
        orderService.order(itemId);
        return ResponseEntity.ok(itemId + " is ordered.");
    }
}
