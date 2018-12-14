package cn.flyuz.pan.entity;

public class User {
    private String user;
    private String pw;
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
