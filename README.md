# Spring-Learning
Java/Spring/SpringBoot
，感谢各位fork，2020年继续更新

## SpringBean的生命周期

### 在普通Java环境下创建对象简要的步骤可以分为：
1):java源码被编译为被编译为class文件
2):等到类需要被初始化时（比如说new、反射等）
3):class文件被虚拟机通过类加载器加载到JVM
4):初始化对象供我们使用
简单来说，可以理解为它是用Class对象作为「模板」进而创建出具体的实例
 
### SpringBean的生命周期
而在Spring管理的Bean中，BeanDefinition非常重要，它用来描述描述对象的信息，比如  属性信息，作用域 ， 初始化方法， 销毁方法 等等元数据。
DefaultListableBeanFactory 存储beanDefinitionMap，十分重要
在Spring中Bean的创建过程主要在ApplicationContext的refresh()方法中
（1）Spring在启动的时候需要「扫描」在XML/注解/JavaConfig 中需要被Spring管理的Bean信息，将这些信息封装成BeanDefinition。这一步可以叫注册--虽然填充了属性，但还没有实例化
（2）继续注册BeanFactoryPostProcessor（ApplicationContext扩展了BeanFactory），用于解析更多更复杂的信息，并修改BeanDefinition。
（3）Bean实例化，注入相关属性。
（4）判断Bean是否实现了Aware接口，是一种感知标记性接口，通过这个桥梁，可以从ApplicationContext获取其他bean。
（5）BeanPostProcessor的前置处理，Bean初始化方法，后置处理。BeanPostProcessor也是AOP实现的关键。
（6）整个过程可以总结为：加载--注册--修改--实例化--扩展--销毁

### 如何解决循环依赖（A依赖B，而B又依赖A）的问题：（构造依赖是不行的）
先实例化A，注入属性时发现依赖于B，把A放到三级缓存中
实例化B，用缓存中的A来注入B的属性，B初始化成功
继续初始化三级缓存中已实例化，但未初始化的A
AB都初始化成功。

### 单例 bean 的线程安全问题了解吗？
单例 bean 存在线程问题，主要是因为当多个线程操作同一个对象的时候是存在资源竞争的。
常见的有两种解决办法：
在 bean 中尽量避免定义可变的成员变量。
在类中定义一个 ThreadLocal 成员变量，将需要的可变成员变量保存在 ThreadLocal 中（推荐的一种方式）。
不过，大部分 bean 实际都是无状态（没有实例变量）的（比如 Dao、Service），这种情况下， bean 是线程安全的。

### Spring MVC 工作流程：
客户端（浏览器）发送请求，直接请求到 DispatcherServlet。
DispatcherServlet 根据请求信息调用 HandlerMapping，解析请求对应的 Handler。
解析到对应的 Handler（也就是我们平常说的 Controller 控制器）后，开始由 HandlerAdapter 适配器处理。
HandlerAdapter 会根据 Handler来调用真正的处理器开处理请求，并处理相应的业务逻辑。
处理器处理完业务后，会返回一个 ModelAndView 对象，Model 是返回的数据对象，View 是个逻辑上的 View。
ViewResolver 会根据逻辑 View 查找实际的 View。
DispaterServlet 把返回的 Model 传给 View（视图渲染）。
把 View 返回给请求者（浏览器）


### Spring 框架中用到了哪些设计模式？
工厂设计模式 : Spring 使用工厂模式通过 BeanFactory、ApplicationContext 创建 bean 对象。
代理设计模式 : Spring AOP 功能的实现。
单例设计模式 : Spring 中的 Bean 默认都是单例的。
模板方法模式 : Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式。
包装器设计模式 : 我们的项目需要连接多个数据库，而且不同的客户在每次访问中根据需要会去访问不同的数据库。这种模式让我们可以根据客户的需求能够动态切换不同的数据源。
观察者模式: Spring 事件驱动模型就是观察者模式很经典的一个应用。
适配器模式 : Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配Controller。

