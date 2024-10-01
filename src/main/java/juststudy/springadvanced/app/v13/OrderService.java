package juststudy.springadvanced.app.v13;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV13")
@RequiredArgsConstructor
public class OrderService {

    @Qualifier("orderRepositoryV13")
    private final OrderRepository orderRepository;

    public void order(String itemId) {
        orderRepository.save(itemId);
    }
}
