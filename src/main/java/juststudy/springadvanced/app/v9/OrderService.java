package juststudy.springadvanced.app.v9;

import org.springframework.stereotype.Service;

@Service("orderServiceV9")
public interface OrderService {

    void order(String itemId);
}
