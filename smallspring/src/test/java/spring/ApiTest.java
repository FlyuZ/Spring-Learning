package spring;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import spring.beans.UserDao;
import spring.beans.UserService;
import spring.beans.PropertyValue;
import spring.beans.PropertyValues;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanReference;
import spring.beans.factory.support.DefaultListableBeanFactory;
import spring.beans.factory.xml.XmlBeanDefinitionReader;
import spring.context.support.ClassPathXmlApplicationContext;
import spring.core.io.DefaultResourceLoader;
import spring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }

}
