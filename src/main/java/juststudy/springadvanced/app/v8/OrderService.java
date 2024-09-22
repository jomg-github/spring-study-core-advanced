package juststudy.springadvanced.app.v8;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV8")
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(@Qualifier("orderRepositoryV8") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void order(String itemId) {
        orderRepository.save(itemId);
    }
}
