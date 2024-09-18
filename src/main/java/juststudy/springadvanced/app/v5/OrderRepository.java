package juststudy.springadvanced.app.v5;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import juststudy.springadvanced.trace.v5.AbstractLogTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("orderRepositoryV5")
public class OrderRepository {

    private final LogTraceService logTraceService;

    public OrderRepository(@Qualifier("logTraceServiceV5") LogTraceService logTraceService) {
        this.logTraceService = logTraceService;
    }

    public void save(String itemId) {
        AbstractLogTemplate<Void> template = new AbstractLogTemplate<>(logTraceService) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }

                sleep(1000);
                return null;
            }
        };

        template.execute(this.getClass().getSimpleName() + ".order()");
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
