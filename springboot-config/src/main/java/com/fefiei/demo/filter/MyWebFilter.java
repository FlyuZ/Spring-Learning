package com.fefiei.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MyWebFilter {
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        //过滤掉 /getUser 和/hello 的请求
        registration.addUrlPatterns("/getUser", "/hello");
        //过滤掉所有请求
//      registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }


    class MyFilter implements Filter {
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            //执行过滤操作...
            System.out.println("请求的url :" + request.getRequestURI());
            // 获取系统时间
            Calendar ca = Calendar.getInstance();
            int hour = ca.get(Calendar.HOUR_OF_DAY);
            // 设置限制运行时间
            if (0 < hour && hour < 2) {
                HttpServletResponse response = (HttpServletResponse) sresponse;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                // 消息
                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("status", "1");
                messageMap.put("message", "此接口可以请求时间为:2-24点");
                ObjectMapper objectMapper = new ObjectMapper();
                String writeValueAsString = objectMapper.writeValueAsString(messageMap);
                response.getWriter().write(writeValueAsString);
                return;
            }

            filterChain.doFilter(srequest, sresponse);
        }

        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("参数初始化:" + filterConfig);
        }

        public void destroy() {
            System.out.println("开始销毁...");
        }
    }
}

