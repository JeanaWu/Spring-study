package spring.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//把这个类声明为一个切面：把该类放入IOC容器中，在声明为一个切面
/**
 * 可以使用@Order注解指定切面的优先级，值越小，优先级越高
 */
@Order(2)
@Aspect
@Component
public class LoggingAspect {
    /**
     * 定义一个方法用于声明切入点表达式，一般该方法中不需要再添入其他的代码
     */
    @Pointcut("execution(public int spring.aop.impl.ArithmeticCalculator.*(int,int))")
    public void declareJoinPointExpression(){}

    /**
     * 声明该方法是一个前置通知：在目标方法开始之前执行
     * @param joinPoint
     */
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+ methodName+" begins "+args);

    }

    /**
     * 后置通知，在目标方法执行后（无论是否发挥异常），执行的通知
     * 在后置通知中，还 不能访问目标方法执行的结果
     * @param joinPoint
     */
    @After("execution(* spring.aop.impl.*.*(int,int))")
    public void afterMethod(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+ methodName+" ends "+args);
    }

    /**
     * 返回通知:在方法正常结束后执行的代码，返回通知可以是可以访问到方法的返回值
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value="execution(public int spring.aop.impl.*.*(..))",returning="result")
    public void afterreturning(JoinPoint joinPoint,Object result){
        String methodName=joinPoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+ methodName+" ends with (returns) "+result);
    }

    /**
     * 在目标方法出现异常时会执行的代码，而且可以访问到异常对象；可以指定在出现特定异常时再执行通知代码
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="execution(* spring.aop.impl.*.*(int,int))",throwing="ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        String methodName=joinPoint.getSignature().getName();
        System.out.println("The method "+ methodName+" occurs excetion: "+ex);
    }
    /**
     * 环绕通知需要携带ProceedingJoinPoint 类型的参数
     * 环绕通知类似于动态代理的全过程：ProceedingJointPoint 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值为目标方法的返回值
     */
    // @Around("execution(public int spring.aop.impl.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjd){
        System.out.println("aroundMethod");
        Object [] args=pjd.getArgs();
        return 100;

//        Object result=null;
//        String methodName=pjd.getSignature().getName();
//
//        try{
//            //前置通知
//            System.out.println("The method "+ methodName+" begins "+args);
//
//            //执行目标方法
//            pjd.proceed();
//
//            //后置通知
//            "The method "+ methodName+" ends "+args
//        }catch (Throwable e){
//            //异常通知
//            System.out.println("The method "+ methodName+" occurs excetion: "+e);
//        }
//        //后置通知
//        System.out.println("The method "+ methodName+" ends with (returns) "+result);
    }
}
