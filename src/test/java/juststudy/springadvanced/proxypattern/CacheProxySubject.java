package juststudy.springadvanced.proxypattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxySubject implements Subject {

    private Subject target;
    private String cacheValue;

    public CacheProxySubject(Subject target) {
        this.target = target;
    }

    /**
     *   프록시 패턴
     *   -  RealSubject와 클라이언트 코드를 전혀 변경하지 않고,
     *      프록시를 도입해서 '접근제어'를 한다는 것
     *
     *   -  클라이언트 코드의 변경 없이 자유롭게 프록시 적용/미적용 가능
     *
     *   -  클라이언트 입장에서는 프록시 객체를 사용하는 지, 실제 객체를 사용하는 지 알지 못함
     */
    @Override
    public String operation() {
        log.info("프록시 객체 호출");

        if (cacheValue == null) {
            cacheValue = target.operation();
        }

        return cacheValue;
    }

}
