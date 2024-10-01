package juststudy.springadvanced.app.v12;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderServiceV12")
@RequiredArgsConstructor
public class OrderService {

    @Qualifier("orderRepositoryV12")
    private final OrderRepository orderRepository;

    public void order(String itemId) {
        orderRepository.save(itemId);
    }
}
