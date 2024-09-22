package juststudy.springadvanced.app.v7;

import org.springframework.stereotype.Repository;

@Repository("orderRepositoryV7")
public interface OrderRepository {
    void save(String itemId);
}
