package spring.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Order(1)
@Aspect
@Component
public class VidationAspect {

    @Before("LoggingAspect.declareJoinPointExpression()")
    public void validationArgs(JoinPoint jointPoint){
        System.out.println("-->validate:" + Arrays.asList(jointPoint.getArgs()));
    }
}
