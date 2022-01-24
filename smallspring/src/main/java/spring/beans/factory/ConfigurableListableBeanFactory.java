package spring.beans.factory;

import spring.beans.BeansException;
import spring.beans.factory.config.AutowireCapableBeanFactory;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanPostProcessor;
import spring.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
