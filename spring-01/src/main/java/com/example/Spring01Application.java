package com.example;

import com.example.controller.UserController;
import com.example.dao.DeliveryDao;
import com.example.dao.UserDao;
import com.example.entity.*;
import com.example.service.XXXService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class Spring01Application {

    /**
     * 测试生命周期
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        System.out.println("-----------ioc容器创建完成--------------");

        User bean = context.getBean(User.class);
        System.out.println(bean);
    }

    public static void test10(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        System.out.println("-------------------------");

        DeliveryDao bean = context.getBean(DeliveryDao.class);
        System.out.println(bean);
    }

    public static void test9(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        System.out.println("-------------------------");

        File file = ResourceUtils.getFile("classpath:1.jpg");
        int available = new FileInputStream(file).available();
        System.out.println(available);
    }


    public static void test8(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        System.out.println("-------------------------");

        Cat bean = context.getBean(Cat.class);
        System.out.println(bean);
    }

    public static void test7(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        System.out.println("-------------------------");

        XXXService service = context.getBean(XXXService.class);
        System.out.println(service);
        System.out.println(service.getOsType());
        System.out.println(service.getName());
    }

    public static void test6(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        System.out.println("------------------------");

        UserDao bean = context.getBean(UserDao.class);          // 构造器注入
        System.out.println(bean);
    }


    public static void test5(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Spring01Application.class, args);
        Map<String, Person> beansOfType = ioc.getBeansOfType(Person.class);
        System.out.println(beansOfType);
        UserController userController = ioc.getBean(UserController.class);
        System.out.println(userController);

    }

    public static void test4(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
        System.out.println(beansOfType);

        Map<String, Dog> beansOfType1 = context.getBeansOfType(Dog.class);
        System.out.println(beansOfType1);

    }

    public static void test3(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Spring01Application.class, args);

        System.out.println("==========================");

        Car car = ioc.getBean(Car.class);
        System.out.println(car);

        Map<String, Car> beansOfType = ioc.getBeansOfType(Car.class);
        System.out.println(beansOfType);
    }


    public static void test2(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Spring01Application.class, args);

        System.out.println("==========================");
        //@Scope("singleton")  单实例，在容器创建的时候就把所有单实例对象创建完成
        Person person1 = ioc.getBean("Alex", Person.class);
        System.out.println("person1 = " + person1);
        Person person2 = ioc.getBean("Alex", Person.class);
        System.out.println("person2 = " + person2);
        System.out.println(person1 == person2);

        //@Scope("prototype")  多实例，在获取的时候才会创建ddd
        System.out.println("==========================");
        Person person3 = ioc.getBean("Bob", Person.class);
        System.out.println("person3 = " + person3);
        Person person4 = ioc.getBean("Bob", Person.class);
        System.out.println("person4 = " + person4);
        System.out.println(person3 == person4);

    }


    public static void test1(String[] args) {

        //spring应用上下文对象，ioc容器
        ConfigurableApplicationContext ioc = SpringApplication.run(Spring01Application.class, args);
        System.out.println("ioc = " + ioc);

        //返回容器中组件名字
        System.out.println("==============================================");
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
//		for (String beanDefinitionName : beanDefinitionNames) {
//			System.out.println(beanDefinitionName);
//		}

        //1.按照组件的名字获取对象
        Person Alex = (Person) ioc.getBean("Alex");
        System.out.println("name = " + Alex);

        //2.按照组件类型获取对象
//		Person bean = ioc.getBean(Person.class);
//		System.out.println("bean = " + bean);

        //3.按照组件类型获取所有对象
        Map<String, Person> beansOfType = ioc.getBeansOfType(Person.class);
        System.out.println("type = " + beansOfType);

        //4.按照组件的名字和类型获取对象
        Person person = ioc.getBean("Alex", Person.class);
        System.out.println("person = " + person);
    }

}
