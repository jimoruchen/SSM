## SPRING

### 返回容器中组件名字 
`String[] beanDefinitionNames = ioc.getBeanDefinitionNames();`

### 在容器中注册一个自己的组件，方法名就是组件的名字或者是@Bean后面的别名
```java
    @Bean("Alex")
    public Person Person() {
        Person person = new Person();
        person.setName("Alex");
        person.setAge(20);
        person.setGender("male");
        return person;
    }
```

### 获取容器中的组件对象

组件的四大特性：名字、类型、对象、作用域

1.按照组件的名字获取对象
`Person Alex = (Person) ioc.getBean("Alex");`

2.按照组件类型获取对象
`Person bean = ioc.getBean(Person.class);`

3.按照组件类型获取所有对象
`Map<String, Person> beansOfType = ioc.getBeansOfType(Person.class);`

4.按照组件的名字和类型获取对象
`Person person = ioc.getBean("Alex", Person.class);`

5.组件创建时机：在容器创建的时候就把所有单实例对象创建完成

6.单实例特性：所有组件默认是单实例的

7.组件批量扫描
`@ComponentScan(basePackages = "com.example")`

### 导入第三方组件

1.使用@Bean
```java
    @Bean
    public CoreConstants getConstants() {
        return new CoreConstants();
    }
```

2.使用@Import
`@Import(CoreConstants.class)`
