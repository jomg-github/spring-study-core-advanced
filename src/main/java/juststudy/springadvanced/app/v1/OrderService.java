package juststudy.springadvanced.app.v1;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.v2.LogTraceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("OrderServiceV1")
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final LogTraceServiceImpl logTraceService;

    public void order(String itemId) {
        LogTraceStatus trace = null;

        try {
            trace = logTraceService.begin(this.getClass().getSimpleName() + ".order()");
            orderRepository.save(itemId);
            logTraceService.end(trace);
        } catch (Exception e) {
            logTraceService.exception(trace, e);
            throw e;
        }

    }
}
