package com.yaohl0911;

import com.yaohl0911.service.UserService;
import com.yaohl0911.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    @Test
    public void XmlTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 前一个参数代表@Bean注解对应方法的名字，就相当于bean标签中的id属性
        // 后一个参数代表@Bean注解对应方法的返回值，就相当于bean标签中的class属性
        UserService userService = context.getBean("userServiceImpl", UserService.class);  // 注意：代理的是接口，所以第二个参数不能是UserServiceImpl.class
        userService.add();
    }

    @Test
    public void AnnoTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.add();
    }
}
