教程地址：https://www.bilibili.com/video/BV1WE411d7Dv?p=2

# Spring



# IoC

## IoC基本原理介绍

对应模块Spring01-ioc

原始实现业务的逻辑：

1. UserDao接口
2. UserDaoImpl实现类
3. UserService业务接口
4. UserSeiviceImpl业务实现类

原本用户要切换Dao实现时，只能修改UserSeiviceImpl，这种实现是通过修改业务代码的方式实现的，这样是不方便的；通过修改UserSeiviceImpl，增加setUserDao，用户可以通过配置的方式使用不同的Dao实现。这就实现了控制反转：使哪个实现的控制权从service层（程序员）转移到了用户。

![image-20210124162127091](/Users/yaohailiang/Library/Application%20Support/typora-user-images/image-20210124162127091.png)

本module的代码体现了IoC最原始的设计思想：没有IoC我们使用面向对象编程时，对象的创建与对象间的依赖关系完全硬编码在程序中（例如service层），对象的创建由程序自己控制；控制反转之后，将对象的创建转移给第三方（例如客户，测试程序就可以认为是用户）。

## 第一个Spring程序

对应模块Spring02-hello-xml

配置文件实现Spring，通过修改配置文件，用户可以很容易控制userDao的实现

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 注册Bean，并给userServiceImpl注入属性 -->
    <bean id="userDaoImpl" class="com.yaohl0911.dao.UserDaoImpl"></bean>
    <bean id="userDaoMysqlImpl" class="com.yaohl0911.dao.UserDaoMysqlImpl"></bean>
    <bean id="userDaoOracleImpl" class="com.yaohl0911.dao.UserDaoOracleImpl"></bean>
    <bean id="userServiceImpl" class="com.yaohl0911.service.UserServiceImpl">
        <!-- 注意对象用ref，值用value-->
        <property name="userDao" ref="userDaoMysqlImpl"></property>
        <property name="name" value="Spring"></property>
    </bean>

</beans>
```

### IoC创建对象的方式

#### 无参构造方式

默认创建对象的方式

```xml
<bean id="userServiceImpl" class="com.yaohl0911.service.UserServiceImpl">
    <!-- 注意对象用ref，值用value-->
    <property name="userDao" ref="userDaoMysqlImpl"></property>
    <property name="name" value="Spring"></property>
</bean>
```

#### 有参构造方式

##### 通过参数的位置

不是特别直观，但是可以使用

```xml
<bean id="userServiceImpl" class="com.yaohl0911.service.UserServiceImpl">
    <!-- 注意对象用ref，值用value-->
    <constructor-arg index="0" ref="userDaoMysqlImpl"></constructor-arg>
    <constructor-arg index="1" value="Spring"></constructor-arg>
</bean>
```

##### 通过参数的类型

存在相同类型的多个参数时，没办法用，不推荐

```xml
<bean id="userServiceImpl" class="com.yaohl0911.service.UserServiceImpl">
    <!-- 注意对象用ref，值用value-->
    <constructor-arg type="com.yaohl0911.dao.UserDao" ref="userDaoMysqlImpl"></constructor-arg>
    <constructor-arg type="java.lang.String" value="Spring"></constructor-arg>
</bean>
```

##### 通过参数的名字

相对比较直观，建议使用

```xml
<bean id="userServiceImpl" class="com.yaohl0911.service.UserServiceImpl">
    <!-- 注意对象用ref，值用value-->
    <constructor-arg name="name" value="eee"></constructor-arg>
    <constructor-arg name="userDao" ref="userDaoOracleImpl"></constructor-arg>
</bean>
```

## 依赖注入(DI)的三种方式

### 构造器注入

前面已经介绍

### set方法

依赖：bean对象的创建依赖于容器

注入：bean对象中所有属性由容器来注入

### 拓展方式

P标签

C标签


## Bean的自动装配

自动装配是Spring满足bean依赖的一种方式。

Spring会在上下文中自动寻找并自动完成装配。

在Spring中有三种装配的方式：

1. 在xml中显式地配置  前边已经讲过
2. 在java中显式地配置  不需要配置文件，即通过类似于Appconfig.java的类来实现配置，后续笔记会涉及
3. 隐式地自动配置

自动装配的方式分为：

1. byName自动装配
2. byType自动装配

### byName自动装配

需要保证所有bean的id唯一，并且这个Bean需要和自动注入的属性的set方法的值一致。

```xml
<bean id="userServiceImpl" class="com.yaohl0911.service.UserServiceImpl" autowire="byName">
```

### byType自动装配

需要保证所有bean的class唯一，并且这个Bean需要和自动注入的属性的类型值一致。

```xml
<bean id="userServiceImpl" class="com.yaohl0911.service.UserServiceImpl" autowire="byType">
```

### 注解方式

模块Spring02-helloSpring和模块Spring03-anno是通过注解实现的。其中Spring02-helloSpring混用了xml配置文件和注解，Spring03-anno是完全按照注解的方式实现的。

### 混用xml配置文件和注

导入约束&配置注解的支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 注解支持导入-->
    <context:annotation-config/>
    <!-- 扫描范围配置-->
    <context:component-scan base-package="com.yaohl0911"></context:component-scan>

</beans>
```

`@Autowired `  Spring的注解，通过byName的方式实现

`@Resource `  Java的注解，也可以实现自动装配，先通过byName的方式查找，找不到就用byType的方式查找



## 只用注解的方式

通过注解声明bean的方式

`@Component`

按照MVC三层架构衍生的三个注解

`@Controller`  控制层

`@Service` 服务层

`@Repository` dao层

```java
@Component
@Data
public class User {
    @Value("yaohl")
    private String name;
}
```

```java
@Controller
public class UserController {
}
```



通过注解注入值的方式

`@Value`

```java
@Component
@Data
public class User {
    @Value("yaohl")
    private String name;
}
```



通过注解配置作用域

`@Scope`

```java
@Component
@Scope("Singleton")
@Data
public class User {
    @Value("yaohl")
    private String name;
}
```



完全脱离配置文件需要的注解，在Spring4之后的主要使用方式

`@Configuration` 表示对应的类可以替代配置文件

`@ComponentScan` 设置扫描范围

`@Bean` 注册一个bean

```java
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
```

```java
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
```



# AOP

![image-20210124223318353](/Users/yaohailiang/Library/Application%20Support/typora-user-images/image-20210124223318353.png)

<img src="/Users/yaohailiang/Library/Application%20Support/typora-user-images/image-20210124223419203.png" alt="image-20210124223419203" style="zoom:150%;" />

## 使用Spring的API实现

需要导入织入包

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.5</version>
</dependency>
```

```java
public class BeforeLog implements MethodBeforeAdvice {
    // method表示要执行目标对象的方法；objects表示该方法的参数；o用target更合适，代表目标对象
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName() + "'s " +  method.getName() + " is executed.");
    }
}
```

```java
public class AfterLog implements AfterReturningAdvice {
    // o用returnValue更合适，代表方返回值；method代表目标对象的方法；objects代表该方法的参数；o1用target更合适，代表目标对象
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println(o1.getClass().getName() + "'s " + method.getName() + " is executed, and return value is " + o);
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userService" class="com.yaohl0911.service.UserServiceImpl"></bean>
    <bean id="beforeLog" class="com.yaohl0911.log.BeforeLog"></bean>
    <bean id="afterLog" class="com.yaohl0911.log.AfterLog"></bean>

    <aop:config>
        <aop:pointcut id="pointcut" expression="execution(* com.yaohl0911.service.UserServiceImpl.*(..))"/>
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"></aop:advisor>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"></aop:advisor>
    </aop:config>
</beans>
```



## 自定义的方式实现

```java
public class DiyPointCut {
    public void diyBefore() {
        System.out.println("diy before");
    }

    public void diyAfter() {
        System.out.println("diy after");
    }
}
```

```xml
<bean id="diyPointCut" class="com.yaohl0911.diy.DiyPointCut"></bean>
<aop:config>
    <aop:aspect ref="diyPointCut">
        <aop:pointcut id="pointcut1" expression="execution(* com.yaohl0911.service.UserServiceImpl.*(..))"/>
        <aop:before method="diyBefore" pointcut-ref="pointcut1"></aop:before>
        <aop:after method="diyAfter" pointcut-ref="pointcut1"></aop:after>
    </aop:aspect>
</aop:config>
```



## 使用Spring注解实现

`@Aspec`   标注为切面

`@Before(ececution(* com.yaohl0911.service.UserServiceImpl.*(..)))  execution`指定了切入的位置，即指定了切入点，第一个星号代表返回值，第二个星号代表该类的任意方法，“..”代表任意参数

`@After(ececution(* com.yaohl0911.service.UserServiceImpl.*(..)))`

```java
@Component  // 这里注意，即使用@Aspect标注为切面了，还是需要注册为bean的
@Aspect
public class AnnoPointCut {
    @Before("execution(* com.yaohl0911.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("before");
    }

    @AfterReturning("execution(* com.yaohl0911.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("after");
    }
}
```

