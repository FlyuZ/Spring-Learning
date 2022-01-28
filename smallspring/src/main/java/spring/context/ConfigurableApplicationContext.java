package spring.context;

import spring.test.bean.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器(核心)
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
