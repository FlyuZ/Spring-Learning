package cn.flyuz.pan;

import cn.flyuz.pan.util.ConnectionUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBCloseListener implements ServletContextListener{
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        ConnectionUtil.conClose();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    }
}
