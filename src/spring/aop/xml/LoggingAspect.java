package spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

//把这个类声明为一个切面：把该类放入IOC容器中，在声明为一个切面

/**
 * 可以使用@Order注解指定切面的优先级，值越小，优先级越高
 */

public class LoggingAspect {



    public void beforeMethod(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+ methodName+" begins "+args);

    }


    public void afterMethod(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+ methodName+" ends "+args);
    }


    public void afterreturning(JoinPoint joinPoint,Object result){
        String methodName=joinPoint.getSignature().getName();
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        System.out.println("The method "+ methodName+" ends with (returns) "+result);
    }



    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        String methodName=joinPoint.getSignature().getName();
        System.out.println("The method "+ methodName+" occurs excetion: "+ex);
    }

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
