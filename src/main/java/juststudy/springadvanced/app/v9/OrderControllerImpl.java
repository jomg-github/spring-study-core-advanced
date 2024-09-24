package juststudy.springadvanced.app.v9;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    public OrderControllerImpl(@Qualifier("orderServiceV9") OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity order(String itemId) {
        orderService.order(itemId);
        return ResponseEntity.ok("ok");
    }

    @Override
    public ResponseEntity noLogOrder(String itemId) {
        return ResponseEntity.ok("ok");
    }
}
