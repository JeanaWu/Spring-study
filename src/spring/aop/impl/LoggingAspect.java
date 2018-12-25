package spring.aop.impl;

import org.springframework.stereotype.Component;

public class LoggingAspect {

    @Aspect
    @Component
    public void beforeMethod(){
        System.out.println("The method begins");
    }
}
