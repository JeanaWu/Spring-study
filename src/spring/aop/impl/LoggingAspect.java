package spring.aop.impl;


import org.springframework.stereotype.Component;

//把这个类声明为一个切面：需要把该类放入到IOC容器中
@Aspect
@Component
class LoggingAspect{
    @before
    public void beforeMehod(){
        System.out.println("The method begins");
    }
}
