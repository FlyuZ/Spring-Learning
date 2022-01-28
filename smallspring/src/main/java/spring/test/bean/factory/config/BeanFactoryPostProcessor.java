package spring.test.bean.factory.config;

import spring.test.bean.BeansException;
import spring.test.bean.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
