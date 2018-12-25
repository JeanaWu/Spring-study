package spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;


public class ValidationAspect {

    @Before("LoggingAspect.declareJoinPointExpression()")
    public void validationArgs(JoinPoint jointPoint){
        System.out.println("-->validate:" + Arrays.asList(jointPoint.getArgs()));
    }
}
