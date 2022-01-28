package spring.test.bean.factory;

import spring.test.bean.BeansException;
import spring.test.bean.factory.config.AutowireCapableBeanFactory;
import spring.test.bean.factory.config.BeanDefinition;
import spring.test.bean.factory.config.BeanPostProcessor;
import spring.test.bean.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
