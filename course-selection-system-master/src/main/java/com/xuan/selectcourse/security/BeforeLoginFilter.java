package com.xuan.selectcourse.security;

// 导入相关类和注解
import org.springframework.ui.Model;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 定义一个过滤器类，并将其应用到指定的URL模式
//如果用户访问的是/admin_login或/checkCode，或者Session中已经有用户名，
//则允许通过过滤器。否则，会检查Session中的验证码与请求参数中的验证码是否匹配，
//匹配则通过并记录用户名，不匹配则重定向到登录页面。
@WebFilter(urlPatterns = {"/user/login","/admin_login"})
public class BeforeLoginFilter extends GenericFilterBean {
    // 重写doFilter方法，定义过滤逻辑
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将ServletRequest转换为HttpServletRequest，以便访问HTTP特有的方法
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 获取请求的URI
        String uri = request.getRequestURI();
        // 获取当前请求的Session
        HttpSession session = request.getSession();
        // 获取请求参数中的验证码
        String code = request.getParameter("code");

        // 如果请求URI包含/admin_login或/checkCode，或者Session中有用户名，继续过滤链的执行。
        if (uri.indexOf("/admin_login") != -1 || uri.indexOf("/checkCode") != -1 || session.getAttribute("username") != null) {
            // 如果满足上述条件之一，则继续过滤链的执行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 获取Session中的验证码
            String checkCode = (String) session.getAttribute("checkCode");
            // 如果Session中没有验证码
            if (checkCode == null) {
                // 继续过滤链的执行
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // 如果Session中的验证码和请求参数中的验证码一致
                if (checkCode.equalsIgnoreCase(code)) {
                    // 将请求参数中的用户名存入Session
                    session.setAttribute("username", request.getParameter("username"));
                    // 继续过滤链的执行
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    // 如果验证码不一致，重定向到/admin_login页面
                    request.getRequestDispatcher("redirect:/admin_login").forward(servletRequest, servletResponse);
                }
            }
        }
    }
}
