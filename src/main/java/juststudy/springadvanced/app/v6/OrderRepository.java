package juststudy.springadvanced.app.v6;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.v6.LogTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("orderRepositoryV6")
public class OrderRepository {

    private final LogTemplate logTemplate;

    public OrderRepository(@Qualifier("logTraceServiceV6") LogTraceService logTraceService) {
        this.logTemplate = new LogTemplate(logTraceService);
    }

    public void save(String itemId) {
        logTemplate.execute(this.getClass().getSimpleName() + ".order()",
                () -> {
                    if (itemId.equals("ex")) {
                        throw new IllegalStateException("예외 발생!");
                    }

                    sleep(1000);
                    return null;
                });
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
