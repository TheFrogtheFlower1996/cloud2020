# 版本说明

![img_0.png](image/版本.png)

springcloud服务

![img_0.png](image/服务.png)

# 父工程

# cloud-provider-payment8001 提供者 支付模块


微服务模块module创建过程

~~~java
1.创建module
2.写pom文件
3.写yml文件
4.写主启动类
5.写业务类
~~~

## 热部署 devtools

1. 父POM中 插入devtools jar包，maven插件
~~~xml
<!--    热部署    -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
        
<!--   maven插件   -->
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <fork>true</fork>
        <addResources>true</addResources>
    </configuration>
</plugin>

~~~

2.yml配置文件注明
~~~yaml
spring:
  devtools:
    remote:
      restart:
        enabled: true #开启热部署
  freemarker:
    cache: true #页面不加载缓存，修改即时生效
~~~

2. 开启自动构建项目

![img_0.png](image/热部署1.png)


3. ctrl+shift+alt+/ 快捷键 打开注册表，勾选两个配置

![img_0.png](image/热部署2.png)

# cloud-consumer-order80 消费者

## RestTemplate

提供了多种便捷访问远程Http服务的方法，是一种简单便捷访问restful服务模板类，是Spring提供的用于访问Rest服务的 客户端 模板工具集

使用restTemplate访问restful接口
~~~text
（url，requestMap，ResponseBean.class）
REST请求地址，请求参数，HTTP响应转换被转换成的对象类型
~~~

@Bean 将组件注册到spring的IOC容器中
~~~java

@Bean
public RestTemplate getRestTemplate(){

    return new RestTemplate();
}

~~~

# Eureka 服务注册

1. 服务治理

    SpringCloud封装了Netflix(奈飞)公司开发的Eureka模块来实现服务治理

    在传统的rpc远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比较复杂，所以需要使用服务治理，管理服务于服务之间的依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册


2. 服务注册
    
    Eureka采用了CS的设计架构，Eureka Server 作为服务注册功能的服务器，他是服务注册中心。而系统中的其他服务，使用Eureka的客户端连接到 EurekaServer并维持心跳连接，这样系统的维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行

    在服务注册与发现中，有一个注册中心。当服务器启动时，会把当前自己服务器的信息比如服务地址通讯地址等以别名方式注册到注册中心。另一方（消费者|服务提供者），以该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地RPC调用RPC远程调用框架核心设计思想：在于注册中心，因为使用注册中心管理每个服务与服务间的依赖关系（服务治理概念）。在任何RPC远程框架下，都会有一个注册中心（存放服务地址、接口地址）


![img_0.png](image/Eureka注册中心.png)


3. Eureka 包含的两个组件 Eureka Server 和 Eureka Client

* Eureka Server 注册中心
   各个微服务节点通过配置启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到
  
* Eureka Client 注册服务
   是一个java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询（round-robin）负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳（默认周期为30秒）。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除（默认90秒）


# cloud-eureka-server7001 注册中心

配置流程

1. 引入eureka server jar包
~~~xml
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
~~~

2. 配置yml文件
~~~yaml
server:
  port: 7001

eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    register-with-eureka: false #false表示自己不向注册中心注册自己
    fetch-registry: false #false表示自己就是注册中心，职责是维护服务实例，不需要检索服务
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/ #设置与Eureka Server交互的地址查询服务和注册服务
~~~

3. 启动类上添加 @EnableEurekaServer 注解
~~~java
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
~~~

其他服务在 注册中心 注册服务

1. 引入eureka client jar包
~~~xml
<!-- eureka-client 向服务注册中心发送消息 -->
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
~~~

2. 配置yml文件
~~~yaml
eureka:
  client:
    register-with-eureka: true #默认为true，表示把自己注册进Eureka Server
    fetchRegistry: true # 是否抓取已有的注册信息，默认为true。单节点无所谓，集群必须位置为true才能配合ribbon使用负载均衡
    service-url:
      defaultZone: http://localhost:7001/eureka
~~~

3. 主启动类加 @EnableEurekaClient 注解
~~~java
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
~~~

# Eureka集群原理

![img_0.png](image/Eureka集群.png)

## 注册中心 集群 

Eureka集群步骤：修改yml配置，让注册中心 相互注册，相互守望
~~~yaml
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com #eureka服务端的实例名称
  client:
    register-with-eureka: false #false表示自己不向注册中心注册自己
    fetch-registry: false #false表示自己就是注册中心，职责是维护服务实例，不需要检索服务
    service-url:
      defaultZone: http://eureka7002.com:7002/eureka/ #设置与Eureka Server交互的地址查询服务和注册服务

~~~

## 服务提供者 集群

cloud-provider-payment8001，cloud-provider-payment8002作为服务提供者，对外有相同的服务名称：cloud-payment-service；所以消费者调用时，需要用到负载均衡

1. 在Controller层定义的 提供者URL 统一改成 服务提供者名称
~~~java
public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
~~~

2. 在 RestTemplate 工具类中添加 负载均衡 注解 @LoadBalanced
~~~java  
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //让RestTemplate具有负载均衡的能力
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
~~~

Eureka集群简略图示

![img_0.png](image/Eureka集群管理.png)


# Discovery 服务发现 

功能：对于注册进Eureka的微服务，可以通过服务发现来获得该服务的信息

1. controller层注入 EurekaDiscoveryClient 对象
~~~java
    @Resource
    private EurekaDiscoveryClient eurekaDiscoveryClient;
~~~

2. 启动类添加 @EnableDiscoveryClient 启动发现客户端注解
~~~java
    @EnableDiscoveryClient
~~~

服务发现调用实例
~~~java
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){

        List<String> services = eurekaDiscoveryClient.getServices();
        for (String service : services) {
            log.info("**** service:"+service);//service:cloud-consumer-order; service:cloud-consumer-order
        }
        List<ServiceInstance> instances = eurekaDiscoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getUri()+"\t"+instance.getPort());
            //payment8002	172.16.66.166	http://172.16.66.166:8002	8002
            //payment8001	172.16.66.166	http://172.16.66.166:8001	8001
        }
        return  this.eurekaDiscoveryClient;
    }

~~~

# Eureka 自我保护机制

概述

* 某时刻某一个微服务不可用了，Eureka不会立刻清理，依旧会对该服务信息进行保存














