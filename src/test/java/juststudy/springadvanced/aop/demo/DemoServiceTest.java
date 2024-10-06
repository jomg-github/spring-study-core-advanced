package juststudy.springadvanced.aop.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(DemoAspectConfig.class)
class DemoServiceTest {

    @Autowired
    DemoService demoService;

    @Test
    void order() {
        for (int i = 1; i <= 6; i++) {
            demoService.order("item0" + i);
        }
    }
}