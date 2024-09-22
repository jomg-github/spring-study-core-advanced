package juststudy.springadvanced.app.v7;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("orderControllerV7")
@RequestMapping("/v7")
@ResponseBody
public interface OrderController {
    @GetMapping("/order")
    ResponseEntity order(String itemId);

    @GetMapping("/order/noLog")
    ResponseEntity noLogOrder(String itemId);
}
