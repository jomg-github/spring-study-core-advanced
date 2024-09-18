package juststudy.springadvanced.app.v3;

import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.LogTraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("orderRepositoryV3")
public class OrderRepository {

    private final LogTraceService logTraceService;

    public OrderRepository(@Qualifier("logTraceServiceV3") LogTraceService logTraceService) {
        this.logTraceService = logTraceService;
    }

    public void save(String itemId) {
        LogTraceStatus trace = null;

        try {
            trace = logTraceService.begin(this.getClass().getSimpleName() + ".order()");

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
