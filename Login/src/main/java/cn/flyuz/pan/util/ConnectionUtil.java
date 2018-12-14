package cn.flyuz.pan.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionUtil {
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    private String dbUrl="jdbc:mysql://localhost:3306/login?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC ";
    private String dbUser="root";
    private String dbPw="123456";
    private static Connection conn = null;

    public Connection getConn() {
        try{
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl,dbUser,dbPw);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void conClose(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
