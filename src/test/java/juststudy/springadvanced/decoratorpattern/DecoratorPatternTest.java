package juststudy.springadvanced.decoratorpattern;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DecoratorPatternTest {

    @Test
    void noDecoratorTest() {
        // given
        // when
        Component realComponent = new RealComponent();
        DecoratorClient decoratorClient = new DecoratorClient(realComponent);

        // then
        decoratorClient.execute();
    }

    @Test
    void decorator1() {
        // given
        // when
        Component target = new RealComponent();
        MessageDecorator decorator = new MessageDecorator(target);
        DecoratorClient decoratorClient = new DecoratorClient(decorator);

        // then
        decoratorClient.execute();
    }

    @Test
    void decorator2() {
        // given
        // when
        Component target = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(target);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorClient decoratorClient = new DecoratorClient(timeDecorator);

        // then
        decoratorClient.execute();
    }
}
