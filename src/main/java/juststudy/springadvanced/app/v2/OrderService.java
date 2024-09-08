package juststudy.springadvanced.app.v2;

import juststudy.springadvanced.trace.LogTrace;
import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.TraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("OrderServiceV2")
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TraceService traceService;

    public void order(String itemId, LogTrace logTrace) {
        LogTraceStatus logTraceStatus = null;

        try {
            logTraceStatus = traceService.beginSync(logTrace, this.getClass().getSimpleName() + ".order()");
            orderRepository.save(itemId, logTraceStatus.getLogTrace());
            traceService.end(logTraceStatus);
        } catch (Exception e) {
            traceService.exception(logTraceStatus, e);
            throw e;
        }

    }
}
