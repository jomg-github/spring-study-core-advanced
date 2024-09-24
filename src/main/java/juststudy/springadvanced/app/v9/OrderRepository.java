package juststudy.springadvanced.app.v9;

import org.springframework.stereotype.Repository;

@Repository("orderRepositoryV9")
public interface OrderRepository {
    void save(String itemId);
}
