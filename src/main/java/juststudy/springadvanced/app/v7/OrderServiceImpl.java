package juststudy.springadvanced.app.v7;

import org.springframework.beans.factory.annotation.Qualifier;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(@Qualifier("orderRepositoryV7") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void order(String itemId) {
        orderRepository.save(itemId);
    }
}
