package juststudy.springadvanced.decoratorpattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    /**
     *   데코레이터 패턴 - 프록시 패턴
     *
     *   -  프록시 패턴과 데코레이터 패턴은 그 모양이 거의 같다. (똑같을 때도 있음)
     *
     *   -  두 패턴을 구분 짓는 것은 의도(intent)
     *
     *   -  프록시 패턴 : 다른 객체에 대한 '접근을 제어' 하기 위한 대리자 제공
     *
     *   -  데코레이터 패턴 : 객체에 '추가 기능을 동적으로 추가'
     *
     */
    @Override
    public String operation() {
        log.info("TimeDecorator 객체 호출");
        long startTime = System.currentTimeMillis();
        String result = component.operation();
        long endTime = System.currentTimeMillis();
        log.info("실행시간 = {}", endTime - startTime);
        return result;
    }
}
