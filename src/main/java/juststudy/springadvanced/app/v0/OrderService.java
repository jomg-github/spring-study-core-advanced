package juststudy.springadvanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("OrderServiceV0")
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void order(String itemId) {
        orderRepository.save(itemId);
    }
}
