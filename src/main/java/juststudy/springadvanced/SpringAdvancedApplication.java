package juststudy.springadvanced;

import juststudy.springadvanced.app.v7.config.ProxyConfig;
import juststudy.springadvanced.app.v8.config.ConcreteProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(ConcreteProxyConfig.class)
@SpringBootApplication
public class SpringAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdvancedApplication.class, args);
    }

}
