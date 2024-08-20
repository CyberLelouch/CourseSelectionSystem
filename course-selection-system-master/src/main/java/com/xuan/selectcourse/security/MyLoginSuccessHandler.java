package com.xuan.selectcourse.security;

// 导入相关类和注解
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuan.selectcourse.mapper.AdminMapper;
import com.xuan.selectcourse.mapper.StudentMapper;
import com.xuan.selectcourse.mapper.TeacherMapper;
import com.xuan.selectcourse.mapper.UserMapper;
import com.xuan.selectcourse.pojo.Admin;
import com.xuan.selectcourse.pojo.Student;
import com.xuan.selectcourse.pojo.Teacher;
import com.xuan.selectcourse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 登录成功后处理器类，标记为组件.
// 注解声明这个类为Spring组件，表示这是一个Spring管理的Bean。
@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

    // 自动注入各种Mapper，用于数据库查询
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    // 静态实例，用于在静态上下文中访问bean.
    public static MyLoginSuccessHandler myLoginSuccessHandler;

    // 初始化方法，在bean创建后调用。用于初始化静态实例。
    @PostConstruct
    public void init() {
        // 将当前实例赋值给静态变量
        myLoginSuccessHandler = this;
    }

    // 处理认证成功的方法
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取用户的认证信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 获取用户名
        String username = userDetails.getUsername();

        // 查询用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = myLoginSuccessHandler.userMapper.selectOne(wrapper);

        // 根据用户状态设置不同的会话属性
        int status = user.getStatus();
        if (status == 2) {  // 管理员身份
            QueryWrapper<Admin> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("username", username);
            Admin admin = myLoginSuccessHandler.adminMapper.selectOne(wrapper1);
            request.getSession().setAttribute("name", admin.getName());
        } else if (status == 0) {  // 教师身份
            QueryWrapper<Teacher> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("username", username);
            Teacher teacher = myLoginSuccessHandler.teacherMapper.selectOne(wrapper1);
            request.getSession().setAttribute("name", teacher.getName());
        } else {  // 学生身份
            QueryWrapper<Student> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("username", username);
            Student student = myLoginSuccessHandler.studentMapper.selectOne(wrapper1);
            request.getSession().setAttribute("name", student.getName());
        }

        // 重定向到首页
        response.sendRedirect("/index");
    }
}
