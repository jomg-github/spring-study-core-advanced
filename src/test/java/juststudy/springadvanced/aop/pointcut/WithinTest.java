package juststudy.springadvanced.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method findMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        findMethod = MemberServiceImpl.class.getMethod("find", String.class);
    }

    // within은 타입 매치
    // execution에서 타입 부분만 사용한다고 보면 됨
    @Test
    @DisplayName("1 - within 매치")
    void pointcut_match_1() {
        pointcut.setExpression("within(juststudy.springadvanced.aop.pointcut.MemberServiceImpl)");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    // 다만 execution과 차이는 부모 타입을 지정하면 안됨
    @Test
    @DisplayName("2 - within 매치 실패")
    void pointcut_match_2() {
        pointcut.setExpression("within(juststudy.springadvanced.aop.pointcut.MemberService)");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("3 - execution 매치 성공")
    void pointcut_match_3() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.pointcut.MemberService.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }
}