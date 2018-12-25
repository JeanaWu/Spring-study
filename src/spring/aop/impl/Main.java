package spring.aop.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //1.创建spring的IOC容器
        ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.从IOC容器中获取bean实例
       // ArithmeticCalculatorImpl arithmeticCalculator = ctx.getBean(ArithmeticCalculatorImpl.class);
        ArithmeticCalculator arithmeticCalculator = ctx.getBean(ArithmeticCalculator.class);
        //3,使用bean
        int result=arithmeticCalculator.add(3,6);
        System.out.println("result:"+result);
    }
}
