package juststudy.springadvanced.app.v11;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV11")
@RequiredArgsConstructor
public class OrderService {

    @Qualifier("orderRepositoryV11")
    private final OrderRepository orderRepository;

    public void order(String itemId) {
        orderRepository.save(itemId);
    }
}
