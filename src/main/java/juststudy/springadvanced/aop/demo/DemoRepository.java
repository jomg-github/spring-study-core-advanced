package juststudy.springadvanced.aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class DemoRepository {

    private static int seq = 1;
    private final static int THROWABLE_COUNT = 3;

    @Retry
    public String save(String itemId) {
        // THROWABLE_COUNT에 한번씩 실패하는 요청
        if (seq % THROWABLE_COUNT == 0) {
            throw new IllegalStateException("예외 발생");
        }

        log.info(itemId + " save ok.");
        seq++;

        return "ok";
    }

}
