package com.yaohl0911.log;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//@Component
//@Aspect
public class BeforeLog implements MethodBeforeAdvice {
    // method表示要执行目标对象的方法；objects表示该方法的参数；o用target更合适，代表目标对象
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName() + "'s " +  method.getName() + " is executed.");
    }
}
