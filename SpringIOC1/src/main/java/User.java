import org.springframework.stereotype.Service;

//1、@Component
//@Component
//是所有受Spring 管理组件的通用形式，@Component注解可以放在类的头上，@Component不推荐使用。
//2、@Controller
//@Controller对应表现层的Bean，也就是Action
// 3、@ Service
//@Service对应的是业务层Bean
// 4、@ Repository
//@Repository对应数据访问层Bean
@Service
public class User {
    private String name;
    private String passwd;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}