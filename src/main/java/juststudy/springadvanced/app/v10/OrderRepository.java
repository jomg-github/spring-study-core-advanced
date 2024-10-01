package juststudy.springadvanced.app.v10;

import org.springframework.stereotype.Repository;

@Repository("orderRepositoryV10")
public class OrderRepository {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }

        sleep(1000);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
