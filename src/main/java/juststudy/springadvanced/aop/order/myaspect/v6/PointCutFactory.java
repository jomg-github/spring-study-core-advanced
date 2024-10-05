package juststudy.springadvanced.aop.order.myaspect.v6;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutFactory {

    @Pointcut("execution(* juststudy.springadvanced.aop.order..*(..))")
    public void inOrderProcess() {} // PointCut Signature

    @Pointcut("execution(* *..*Service.*(..))")
    public void serviceClass() {}

    @Pointcut("serviceClass() && inOrderProcess()")
    public void serviceClassInOrderProcess() {}

}
