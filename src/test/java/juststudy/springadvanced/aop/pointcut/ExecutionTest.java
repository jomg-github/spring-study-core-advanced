package juststudy.springadvanced.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method findMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        findMethod = MemberServiceImpl.class.getMethod("find", String.class);
    }

    @Test
    void find() {
        log.info("findMethod = {}", findMethod);
//        public java.lang.String juststudy.springadvanced.aop.member.MemberServiceImpl.find(java.lang.String)
    }

    @Test
    @DisplayName("1 - 가장 정확한 매치")
    void pointcut_match_1() {
        // execution(접근제어자? 반환타입 선언타입? 메소드이름 (파라미터) 예외?)
        pointcut.setExpression("execution(public String juststudy.springadvanced.aop.pointcut.MemberServiceImpl.find(String))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("2 - 가장 열려있는 매치")
    void pointcut_match_2() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("3 - 메소드 이름 매치 (1)")
    void pointcut_match_3() {
        pointcut.setExpression("execution(* fi*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("4 - 메소드 이름 매치 (2)")
    void pointcut_match_4() {
        pointcut.setExpression("execution(* *in*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("5 - 메소드 이름 매치 실패")
    void pointcut_match_5() {
        pointcut.setExpression("execution(* *ABCDEF*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("6 - 패키지 매치 (1)")
    void pointcut_match_6() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.pointcut.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("7 - 패키지 매치 (2)")
    void pointcut_match_7() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.pointcut.*.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("8 - 패키지 매치 실패 (1)")
    void pointcut_match_8() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.order.*.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("9 - 패키지 매치 실패 (2)")
    void pointcut_match_9() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.*.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("10 - 패키지 매치 (3)")
    void pointcut_match_10() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop..*.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("11 - 타입 매치 (1)")
    void pointcut_match_11() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.pointcut.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("12 - 타입 매치 (2)")
    void pointcut_match_12() {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.pointcut.MemberService.*(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("13 - 타입 매치 실패")
    void pointcut_match_13() throws NoSuchMethodException {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.pointcut.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal");
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("14 - 타입 매치 (3)")
    void pointcut_match_14() throws NoSuchMethodException {
        pointcut.setExpression("execution(* juststudy.springadvanced.aop.pointcut.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal");
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("15 - 파라미터 매치 (1)")
    void pointcut_match_15() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("16 - 파라미터 매치 (2)")
    void pointcut_match_16() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("17 - 파라미터 매치 (3)")
    void pointcut_match_17() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("18 - 파라미터 매치 (4)")
    void pointcut_match_18() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("19 - 파라미터 매치 실패 (1)")
    void pointcut_match_19() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("20 - 파라미터 매치 실패 (2)")
    void pointcut_match_20() {
        pointcut.setExpression("execution(* *(Integer))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("21 - 파라미터 매치 실패 (3)")
    void pointcut_match_21() {
        pointcut.setExpression("execution(* *(*, *))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("22 - 파라미터 매치 실패 (4)")
    void pointcut_match_22() {
        pointcut.setExpression("execution(* *(String, *))");
        assertThat(pointcut.matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

}