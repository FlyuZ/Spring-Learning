import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Factory{
    private Factory() {}
    public static User getInstance(String className) {
        if("user".equalsIgnoreCase(className)) {
            return new User();
        }
        return null;
    }
}
public class Test {
    public void run1(){
        User user = new User();
        System.out.println("自己生成" + user.toString());
    }
    public void run2(){
        User user = Factory.getInstance("user");
        System.out.println("工厂方法生成" + user.toString());
    }
    public void run3(){
        ApplicationContext concext = new ClassPathXmlApplicationContext("application.xml");
        User user = (User) concext.getBean("user1");
        System.out.println("SpringIOC生成" + user.toString());
    }
}