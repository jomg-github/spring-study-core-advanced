package juststudy.springadvanced.aop.pointcut;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
class ArgsTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method findMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        findMethod = MemberServiceImpl.class.getMethod("find", String.class);
    }

    // args : 인자가 주어진 타입의 인스턴스인 조인 포인트로 매칭
    // 기본 문법은 execution의 args 부분과 같음
    //
    // execution는 파라미터 타입이 정확하게 매칭되어야 함
    // args는 부모타입을 허용, 실제 넘어온 파라미터 객체 인스턴스를 보고 판단
    //
    // (결론)
    // execution는 선언된 파라미터(시그니쳐)로만 판단하기 때문에 부모타입 허용 X
    // args는 런타임에 넘어온 파라미터로만 판단하며 부모타입 허용 O

    @Test
    @DisplayName("다양한 args expression 테스트")
    void pointcut_match_1() {
        assertThat(pointcut("args(String)").matches(findMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(findMethod, MemberServiceImpl.class)).isTrue(); // 부모 타입도 허용
        assertThat(pointcut("args()").matches(findMethod, MemberServiceImpl.class)).isFalse();
        assertThat(pointcut("args(..)").matches(findMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(*)").matches(findMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(String, ..)").matches(findMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(String, *)").matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    // execution(* *(java.io.Serializable)) : 메소드의 시그니처로 판단, 정적
    // args(java.io.Serializable) : 런타임에 전달된 인수로 판단, 동적
    @Test
    @DisplayName("args-execution 비교 테스트")
    void pointcut_match_2() {
        // #######################
        // ###      args        ##
        // #######################

        // String은 Serializable를 구현하고 있음
        // Object는 모든 객체의 상위 객체

        assertThat(pointcut("args(String)").matches(findMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(java.io.Serializable)").matches(findMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(findMethod, MemberServiceImpl.class)).isTrue();


        // #######################
        // ###     execution    ##
        // #######################
        assertThat(pointcut("execution(* *(String))").matches(findMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("execution(* *(java.io.Serializable))").matches(findMethod, MemberServiceImpl.class)).isFalse();
        assertThat(pointcut("execution(* *(Object))").matches(findMethod, MemberServiceImpl.class)).isFalse();
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

}