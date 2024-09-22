package juststudy.springadvanced.decoratorpattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 객체 호출");
        String operation = component.operation();
        String decoratedOperation = "*******" + operation + "*******";

        log.info("{} => {}", operation, decoratedOperation);
        return decoratedOperation;
    }
}
