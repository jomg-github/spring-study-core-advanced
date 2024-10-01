package juststudy.springadvanced.app.v10;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("orderServiceV10")
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void order(String itemId) {
        orderRepository.save(itemId);
    }
}
