package juststudy.springadvanced.app.v2;

import juststudy.springadvanced.trace.LogTrace;
import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.LogTraceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository("OrderRepositoryV2")
@RequiredArgsConstructor
public class OrderRepository {
    private final LogTraceServiceImpl logTraceService;

    public void save(String itemId, LogTrace logTrace) {
        LogTraceStatus trace = null;

        try {
            trace = logTraceService.beginSync(logTrace.nextId(), this.getClass().getSimpleName() + ".order()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            sleep(1000);
            logTraceService.end(trace);
        } catch (Exception e) {
            logTraceService.exception(trace, e);
            throw e;
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
