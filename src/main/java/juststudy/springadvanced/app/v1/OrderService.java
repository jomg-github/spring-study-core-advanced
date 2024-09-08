package juststudy.springadvanced.app.v1;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.TraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("OrderServiceV1")
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TraceService traceService;

    public void order(String itemId) {
        LogTraceStatus trace = null;

        try {
            trace = traceService.begin(this.getClass().getSimpleName() + ".order()");
            orderRepository.save(itemId);
            traceService.end(trace);
        } catch (Exception e) {
            traceService.exception(trace, e);
            throw e;
        }

    }
}
