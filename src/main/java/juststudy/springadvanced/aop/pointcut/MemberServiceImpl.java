package juststudy.springadvanced.aop.pointcut;

import juststudy.springadvanced.aop.pointcut.annotations.ClassAop;
import juststudy.springadvanced.aop.pointcut.annotations.MethodAop;
import org.springframework.stereotype.Service;

@ClassAop
@Service
public class MemberServiceImpl implements MemberService {

    @MethodAop("method aop applies.")
    @Override
    public String find(String name) {
        return "member";
    }

    public String internal() {
        return "internal";
    }
}
