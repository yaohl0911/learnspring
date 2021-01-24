package com.yaohl0911;

import com.yaohl0911.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 用@Configuration注解的类可以替代配置文件applicationContext.xml
 * 实际上他是一个Component注解，所以它也会被Spring容器托管
 *
 * 用@Bean注解的方法就相当与之前在applicationContext.xml中的一个bean标签
 * 对应方法的名字，就相当于bean标签中的id属性
 * 对应方法的返回值，就相当于bean标签的class属性
 */
@Configuration
@ComponentScan("com.yaohl0911")
public class AppConfig {
    @Bean
    public User User() {
        return new User();
    }
}
