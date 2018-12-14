package cn.flyuz.pan.servlet;


import cn.flyuz.pan.dao.UserDao;
import cn.flyuz.pan.util.Base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UserServlet", urlPatterns = "/loginServlet")
public class UserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");

        switch (type) {
            case "login":
                login(request, response);
                break;
            case "reg":
                reg(request, response);
                break;
//            case "del":
//                del(request, response);
//                break;
//            case "getAll":
//                getAll(request, response);
//                break;
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


    private void login(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        String pw = request.getParameter("pw");

        boolean user_login = userDao.login(user, pw);
        try {
            if (user_login) {
                request.getRequestDispatcher("index/index.jsp").forward(request, response);
            } else {
                String msg = "登陆失败";
                request.getSession().setAttribute("msg", msg);
                request.getRequestDispatcher("index/login.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void reg(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        String pw = request.getParameter("pw");

        String rs = userDao.reg(user, pw);
        if (rs.equals(Base.registerSuccess)) {
            try {
                request.getRequestDispatcher("index/login.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else if (rs.equals(Base.registerFalse) || rs.equals(Base.registerRepeated)) {
            try {
                String msg = "注册失败";
                request.getSession().setAttribute("msg", msg);
                request.getRequestDispatcher("index/register.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
//    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String user = request.getParameter("user");
//        try {
//            userDao.del(user);
//            request.getSession().setAttribute("msg", "删除成功！");
//            request.getRequestDispatcher("index/index.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.getSession().setAttribute("msg", "删除失败！");
//            request.getRequestDispatcher("index/index.jsp").forward(request, response);
//        }
//    }
//    private void getAll(HttpServletRequest request, HttpServletResponse response) {
//        request.getSession().setAttribute("userList", userDao.getAll());
//        try {
//            request.getRequestDispatcher("index/index.jsp").forward(request, response);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
//    }
}