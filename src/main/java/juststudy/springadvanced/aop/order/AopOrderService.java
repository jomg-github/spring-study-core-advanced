package juststudy.springadvanced.aop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AopOrderService {

    private final AopOrderRepository aopOrderRepository;

    public void order(String itemId) {
        aopOrderRepository.save(itemId);
    }

}
