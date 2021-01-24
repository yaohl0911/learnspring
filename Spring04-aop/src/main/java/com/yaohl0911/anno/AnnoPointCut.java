package com.yaohl0911.anno;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect  // 标注这个类是一个切面
public class AnnoPointCut {
    @Before("execution(* com.yaohl0911.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("Annotation before");
    }

    @After("execution(* com.yaohl0911.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("Annotation after");
    }
}
