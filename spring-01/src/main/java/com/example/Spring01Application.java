package com.example;

import com.example.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class Spring01Application {

    public static void main(String[] args) {
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
