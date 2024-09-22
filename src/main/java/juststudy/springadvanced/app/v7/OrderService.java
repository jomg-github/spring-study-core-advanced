package juststudy.springadvanced.app.v7;

import org.springframework.stereotype.Service;

@Service("orderServiceV7")
public interface OrderService {

    void order(String itemId);
}
