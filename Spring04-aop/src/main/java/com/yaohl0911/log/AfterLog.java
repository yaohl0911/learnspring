package com.yaohl0911.log;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//@Component
//@Aspect
public class AfterLog implements AfterReturningAdvice {
    // o用returnValue更合适，代表方返回值；method代表目标对象的方法；objects代表该方法的参数；o1用target更合适，代表目标对象
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println(o1.getClass().getName() + "'s " + method.getName() + " is executed, and return value is " + o);
    }
}
