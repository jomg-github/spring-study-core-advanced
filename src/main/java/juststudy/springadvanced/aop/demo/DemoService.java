package juststudy.springadvanced.aop.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final DemoRepository demoRepository;

    @ApiLogging
    public void order(String itemId) {
        demoRepository.save(itemId);
    }

}
