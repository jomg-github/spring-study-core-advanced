package juststudy.springadvanced.proxypattern;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        // given
        // when
        RealSubject realSubject = new RealSubject();
        ProxyClient proxyClient = new ProxyClient(realSubject);

        // then
        proxyClient.execute();
        proxyClient.execute();
        proxyClient.execute();
    }

    @Test
    void cacheProxyTest() {
        // given
        // when
        RealSubject target = new RealSubject();
        CacheProxySubject cacheProxySubject = new CacheProxySubject(target);
        ProxyClient proxyClient = new ProxyClient(cacheProxySubject);

        // then
        proxyClient.execute();
        proxyClient.execute();
        proxyClient.execute();
    }
}
