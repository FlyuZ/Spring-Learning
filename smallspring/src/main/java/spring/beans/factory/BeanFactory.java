package spring.beans.factory;

import spring.beans.BeansException;


//使用BeanDefinition生成Bean
//Bean的生命周期--
// 1）BeanDefinition Bean的定义
// 2）构造方法推断，选择一个合适的构造方法
// 3）构造方法反射得到对象
// 4）给属性进行自动填充
// 5）对其他属性赋值、校验
// 6）AOP生成代理对象
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}