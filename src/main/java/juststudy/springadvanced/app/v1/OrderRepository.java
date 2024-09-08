package juststudy.springadvanced.app.v1;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.TraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository("OrderRepositoryV1")
@RequiredArgsConstructor
public class OrderRepository {
    private final TraceService traceService;

    public void save(String itemId) {
        LogTraceStatus trace = null;

        try {
            trace = traceService.begin(this.getClass().getSimpleName() + ".order()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            sleep(1000);
            traceService.end(trace);
        } catch (Exception e) {
            traceService.exception(trace, e);
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
