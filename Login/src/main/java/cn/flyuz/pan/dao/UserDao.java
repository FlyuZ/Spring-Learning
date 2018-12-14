package cn.flyuz.pan.dao;

import cn.flyuz.pan.entity.User;
import cn.flyuz.pan.util.Base;
import cn.flyuz.pan.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection getConn() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        return connectionUtil.getConn();
    }
    public boolean login(String user, String pw) {
        Connection conn = getConn();
        String sql = "select * from login where user=? and pw=?";

        try {
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,pw);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                ps.close();
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String reg(String user, String pw) {
        Connection conn = getConn();
        String sql1= "select * from login where user = ?";
        String sql = "insert into login(user,pw) values (?,?)";

        try {
            PreparedStatement pss = conn.prepareStatement(sql1);
            pss.setString(1,user);
            ResultSet rs = pss.executeQuery();
            if (!rs.next()){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,user);
                ps.setString(2,pw);
                ps.executeUpdate();
                return Base.registerSuccess;
            }else{
                return Base.registerFalse;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Base.registerFalse;
    }

    public void del(String user) {
        Connection conn = getConn(); //获取连接
        String sql = "delete from login where user = ?";//sql语句
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public List<User> getAll() {
//        Connection conn = getConn();
//        String sql = "select * from login";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            List<User> userList = new ArrayList<>();
//            while(rs.next()) {
//                User user1 = new User();
//                user1.setUser(rs.getString("user"));
//                user1.setPw(rs.getString("pw"));
//                userList.add(user1);
//            }
//            return userList;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public void update(String user,String pw){
//        Connection conn = getConn(); //获取连接
//        String sql = "update login set user = ?,pw = ? where id = ?"; //sql语句
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            //设置参数
//            ps.setString(1,user);
//            ps.setString(2,pw);
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
