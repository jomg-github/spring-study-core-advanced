package juststudy.springadvanced.app.v2;

import juststudy.springadvanced.trace.LogTrace;
import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.v2.LogTraceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("OrderServiceV2")
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final LogTraceServiceImpl logTraceService;

    public void order(String itemId, LogTrace logTrace) {
        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = logTraceService.beginSync(logTrace, this.getClass().getSimpleName() + ".order()");
            orderRepository.save(itemId, logTraceStatus.getLogTrace());
            logTraceService.end(logTraceStatus);
        } catch (Exception e) {
            logTraceService.exception(logTraceStatus, e);
            throw e;
        }

    }
}
