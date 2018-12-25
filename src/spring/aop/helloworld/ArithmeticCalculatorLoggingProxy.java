package spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {

    //需要代理对象
    private ArithmeticCalculator target;

    ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target){
        this.target=target;
    }

    public ArithmeticCalculator getLoggingProxy() {
        ArithmeticCalculator proxy = null;

        //代理对象由哪一个类加载器负责加载
        ClassLoader loader = target.getClass().getClassLoader();
        //代理对象的类型，即其中有哪些方法
        Class[] interfaces = new Class[]{ArithmeticCalculator.class};
        //当调用代理对象其中的方法时，该实行的代码
        InvocationHandler h = new InvocationHandler() {
            /**
             *
             * @param proxy：正在返回的那个对象，一般情况下，在invoke方法中都不使用该对象
             * @param method：正在被调用的方法
             * @param args：调用方法时，传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName=method.getName();
                System.out.println("xiuxiu-->The method "+methodName+" begins with"+ Arrays.asList(args));
                Object result= method.invoke(target,args);
                System.out.println("xiuxiu-->The method "+methodName+" ends with "+result);
                System.out.println("invoke....");
                return result;
            }
        };
        proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader,interfaces, h);
        return proxy;
    }
}
