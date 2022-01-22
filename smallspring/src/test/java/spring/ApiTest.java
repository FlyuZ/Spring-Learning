package spring;

import org.junit.Test;
import spring.beans.UserDao;
import spring.beans.UserService;
import spring.beans.factory.PropertyValue;
import spring.beans.factory.PropertyValues;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanReference;
import spring.beans.factory.support.DefaultListableBeanFactory;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        String s = userDao.queryUserName("10003");
        System.out.println(s);
    }
}
