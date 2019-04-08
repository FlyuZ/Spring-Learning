感谢 https://github.com/xuwujing/springBoot-study 的教程
SpringBoot自定义配置获取，使用过滤器以及拦截器  
结果通过json返回给前端

1.过滤器：所谓过滤器顾名思义是用来过滤的，在java web中，你传入的request,response提前过滤掉一些信息，或者提前设置一些参数，然后再传入servlet或者struts的action进行业务逻辑，比如过滤掉非法url（不是login.do的地址请求，如果用户没有登陆都过滤掉）,或者在传入servlet或者struts的action前统一设置字符集，或者去除掉一些非法字符（聊天室经常用到的，一些骂人的话）。filter 流程是线性的， url传来之后，检查之后，可保持原来的流程继续向下执行，被下一个filter, servlet接收等.

2.java的拦截器 主要是用在插件上，扩展件上比如 hibernate spring struts2等 有点类似面向切片的技术，在用之前先要在配置文件即xml文件里声明一段的那个东西。
