package spring.aop.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//把这个类声明为一个切面：把该类放入IOC容器中，在声明为一个切面
@Aspect
@Component
public class LoggingAspect {

    //声明该方法是一个前置通知：在目标方法开始之前执行
    @Before("execution(public int spring.aop.impl.ArithmeticCalculator.add(int,int))")
    public void beforeMethod(){
        System.out.println("The method begins");

    }
}
