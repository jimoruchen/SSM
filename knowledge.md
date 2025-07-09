# SPRING

## IOC

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

### @Scope调整组件的作用域，默认是单实例

@Scope("prototype")  非单实例。
@Scope("singleton")  单实例，在容器创建的时候就把所有单实例对象创建完成。
@Lazy  懒加载使单实例在使用时再创建。

### @FactoryBean

工厂Bean允许你自定义对象的创建逻辑。通过实现 FactoryBean 接口，你可以更灵活地控制 Bean 的实例化过程。

```java
@Component
public class BYDFactoryBean implements FactoryBean<Car> {

    @Override
    public Car getObject() throws Exception {       //返回由工厂创建的对象实例
        return new Car();
    }

    @Override
    public Class<?> getObjectType() {               //返回类型
        return Car.class;
    }

    @Override
    public boolean isSingleton() {                  //声明由这个工厂创建的对象是否是单例模式,默认为True。
        return true;
    }
}
```

### @Conditional()

满足条件才执行代码
```java
    @Conditional(WindowsCondition.class)
    @Bean("Bill")
    public Person Bill() {
        Person person = new Person();
        person.setName("Bill");
        person.setAge(18);
        person.setGender("male");
        return person;
    }
```
```java
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        return property != null && property.contains("Windows");
    }
}

```
只有本机系统是Windows才会注册这个Bill的bean。

### @ConditionalOnBean()
@ConditionalOnBean(name = "Alex"), @ConditionalOnBean(value = {Person.class})
根据是否有某个Bean决定是否执行代码。    
@ConditionalOnMissingBean(name = "Alex"), @ConditionalOnMissingBean(value = {Person.class})
根据是否没有某个Bean决定是否执行代码。
```java
@Configuration
public class DogConfiguration {
    @ConditionalOnMissingBean(name = "Alex")        //没有Alex这个Bean才创建Dog的Bean。
    @Bean
    public Dog dog() {
        return new Dog();
    }
}
```

### 组件注入

### @Autowired
    /**
     * 自动装配流程（先按照类型，再按照名称）
     * 1、按照类型找到这个组件：
     *      1.0、只有且只找到一个，直接注入，名字无所谓
     *      1.1、找到多个再按照名字去找；变量名就是名字
     *          1.1.0、找到直接注入
     *          1.1.1、找不到报错
     */
    `@Autowired      //自动装配，原理：Spring调用容器 .getBean()
     Person Bill;`

### @Qualifier()
    `@Qualifier("Bill")
     @Autowired
     Person person;`
当名字不确定时，使用@Qualifier精确指定
```java
@Configuration
public class PersonConfiguration {

    @Primary
    @Conditional(WindowsCondition.class)
    @Bean("Bill")
    public Person Bill() {
        Person person = new Person();
        person.setName("Bill");
        person.setAge(18);
        person.setGender("male");
        return person;
    }

    @Conditional(MacCondition.class)
    @Bean("Jobs")
    public Person Jobs() {
        Person person = new Person();
        person.setName("Jobs");
        person.setAge(18);
        person.setGender("male");
        return person;
    }

    // 在容器中注册一个自己的组件，方法名就是组件的名字
    @Lazy
    @Scope("singleton")
    @Bean("Alex")
    public Person Alex() {
        Person person = new Person();
        person.setName("Alex");
        person.setAge(20);
        person.setGender("male");
        return person;
    }
    @Scope("prototype")
    @Bean("Bob")
    public Person Bob() {
        Person person = new Person();
        person.setName("Bob");
        person.setAge(20);
        person.setGender("male");
        return person;
    }
}
```

### @Primary
    `@Primary
     @Conditional(WindowsCondition.class)
     @Bean("Bill")
     public Person Bill() {
         Person person = new Person();
         person.setName("Bill");
         person.setAge(18);
         person.setGender("male");
         return person;
     }`
    `@Autowired
     Person person;`
或者使用@Primary精确指定哪个Person的Bean是首要的

### 构造器注入
```java
@Repository
public class UserDao {

    Dog dog1;
    
    //spring 自动去容器中找到 构造器需要的所有参数的组件值
    public UserDao(Dog dog) {
        this.dog1 = dog;
    }
}
```

### set方法注入
```java
@ToString
@Repository
public class UserDao {

    Dog dog1;

//    public UserDao(Dog dog) {         //构造器注入
//        this.dog1 = dog;
//    }

    @Autowired
    public void setDog(@Qualifier("dog1") Dog dog) {        //set方法注入
        System.out.println(dog);
        this.dog1 = dog;
    }
}
```
```java
@Configuration
public class DogConfiguration {

    @ConditionalOnMissingBean(name = "Alex")
    @Bean
    public Dog dog() {
        Dog dog = new Dog();
        dog.setName("dog");
        return dog;
    }

    @Bean
    public Dog dog1() {
        Dog dog = new Dog();
        dog.setName("dog1");
        return dog;
    }

    @Bean
    public Dog dog2() {
        Dog dog = new Dog();
        dog.setName("dog2");
        return dog;
    }
}
```
```java
@Data
public class Dog {
    String name;
    public Dog() {
        System.out.println("Dog constructor");
    }
}
```

### EnvironmentAware, BeanNameAware
设置环境变量，Bean的名字
```java
@Data
@ToString
@Service
public class XXXService implements EnvironmentAware, BeanNameAware {

    private Environment env;
    private String name;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    public String getOsType() {
        return env.getProperty("os.name");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
```

### @Value()
```java
@ToString
@Data
@Component
public class Cat {

    /**
     * 1、@value("字面值")：直接赋值
     * 2、@value("${key}")：从配置文件动态取出某一项的值
     * 3、@value("#{SpEL}")：Spring表达式
     */
    @Value("大黄")
    private String name;
    @Value("${dog.age}")        // @Value("${dog.age:20}")  冒号后面是取不到的默认值
    private int age;
    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String id;
    @Value("#{'Hello World!'.substring(0, 5)}")
    private String message;

    public Cat() {
        System.out.println("Cat构造器...");
    }
}
```

### 映射配置文件
### properties映射
使用@PropertySource("classpath:cat.properties")

```java
@PropertySource("classpath:cat.properties")
@ToString
@Data
@Component
public class Cat {

    /**
     * 1、@value("字面值")：直接赋值
     * 2、@value("${key}")：从配置文件动态取出某一项的值
     * 3、@value("#{SpEL}")：Spring表达式
     */
    @Value("大黄")
    private String name;
    @Value("${cat.age}")
    private int age;
    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String id;
    @Value("#{'Hello World!'.substring(0, 5)}")
    private String message;

    public Cat() {
        System.out.println("Cat构造器...");
    }
}
```

### yml映射
使用@ConfigurationProperties(prefix = "cat")

```java
//@PropertySource("classpath:cat.properties")
@ConfigurationProperties(prefix = "cat")
@ToString
@Data
@Component
public class Cat {

    /**
     * 1、@value("字面值")：直接赋值
     * 2、@value("${key}")：从配置文件动态取出某一项的值
     * 3、@value("#{SpEL}")：Spring表达式
     */
    @Value("大黄")
    private String name;
    //@Value("${cat.age}")              //yml映射不能再在每个字段上使用@Value
    private int age;
    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String id;
    @Value("#{'Hello World!'.substring(0, 5)}")
    private String message;

    public Cat() {
        System.out.println("Cat构造器...");
    }
}
```

### ResourceUtils--获取资源
```java
@SpringBootApplication
public class Spring01Application {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Spring01Application.class, args);
        System.out.println("-------------------------");

        File file = ResourceUtils.getFile("classpath:1.jpg");
        int available = new FileInputStream(file).available();
        System.out.println(available);
    }
}
```

### @Profile--多环境
```java
@ToString
@Data
public class MyDataSource {

    private String url;
    private String username;
    private String password;
}
```
```java
@Configuration
public class DataSourceConfiguration {

    //利用条件注解，只在某种环境下激活一个组件

    @Profile("dev")     //环境标识
    @Bean
    public MyDataSource dev() {
        MyDataSource dataSource = new MyDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/dev");
        dataSource.setUsername("dev_user");
        dataSource.setPassword("dev_pass");
        return dataSource;
    }

    @Profile("test")
    @Bean
    public MyDataSource test() {
        MyDataSource dataSource = new MyDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSource.setUsername("test_user");
        dataSource.setPassword("test_pass");
        return dataSource;
    }

    @Profile("prod")
    @Bean
    public MyDataSource prod() {
        MyDataSource dataSource = new MyDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/prod");
        dataSource.setUsername("prod_user");
        dataSource.setPassword("prod_pass");
        return dataSource;
    }
}
```
```java
@ToString
@Repository
public class DeliveryDao {

    @Resource
    MyDataSource dataSource;

    void save() {
        System.out.println("数据源，保存数据" + dataSource);
    }

}
```
```yml
spring:
  application:
    name: spring-01
  profiles:
    active: dev
```


### 组件生命周期 @Bean

```java
@Data
public class User implements InitializingBean, DisposableBean {

    private String username;
    private String password;
    private Cat cat;

    @Autowired
    public void setCat(Cat cat) {
        System.out.println("自动注入：" + cat);
        this.cat = cat;
    }

    public User() {
        System.out.println("User 构造器...");
    }

    public void initUser() {
        System.out.println("@Bean 初始化");
    }

    public void destroyUser() {
        System.out.println("@Bean 销毁");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean...destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean...afterPropertiesSet");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy...");
    }
}

```
```java
@Configuration
public class UserConfiguration {

    @Bean(initMethod = "initUser", destroyMethod = "destroyUser")
    public User user() {
        return new User();
    }
}
```
```java
@Component
public class TestBeanTestProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("PostProcessBeforeInitialization..." + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("PostProcessAfterInitialization..." + beanName);
        return bean;
    }
}
```
User 构造器...
自动注入：Cat(name=大黄, age=0, id=e1e414ab-beb1-4779-9825-38fc72fefdfc, message=Hello)
PostProcessBeforeInitialization...user
PostConstruct...
InitializingBean...afterPropertiesSet
@Bean 初始化
PostProcessAfterInitialization...user
-----------ioc容器创建完成--------------
User(username=null, password=null, cat=Cat(name=大黄, age=0, id=e1e414ab-beb1-4779-9825-38fc72fefdfc, message=Hello))
PreDestroy...
DisposableBean...destroy
@Bean 销毁

<a href="https://sm.ms/image/oSIw3hbECXO2BTR" target="_blank"><img src="https://s2.loli.net/2025/07/06/oSIw3hbECXO2BTR.png" ></a>

@Autowired是如何实现的?
1、专门有一个处理@Autowired注解的AutowiredAnnotationBeanPostProcessor  
2、每个Bean创建以后，会调用BeanPostProcessor的postProcessBeforeInitialization方法  
3、postProcessBeforeInitialization里面就会利用反射，得到当前Bean的所有属性，利用反射，
得到Bean属性上标注的所有注解，看有没有@Autowired注解  
4、如果有，去容器中找到这个属性对应的组件（按类型，按名字）找到。


## AOP

### 