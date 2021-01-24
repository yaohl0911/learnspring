package com.yaohl0911;

import com.yaohl0911.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    @Test
    public void UserTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 前一个参数代表@Bean注解对应方法的名字，就相当于bean标签中的id属性
        // 后一个参数代表@Bean注解对应方法的返回值，就相当于bean标签中的class属性
        User user = context.getBean("User", User.class);
        System.out.println(user.getName());
    }
}
