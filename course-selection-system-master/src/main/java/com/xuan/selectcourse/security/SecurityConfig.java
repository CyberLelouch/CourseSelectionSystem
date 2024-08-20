package com.xuan.selectcourse.security;

// 导入Spring框架相关的注解和类
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 标注为配置类
@Configuration
// 启用方法级别的安全注解（如 @PreAuthorize, @PostAuthorize 等）
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 重写配置HTTP安全性的方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置自定义表单登录配置
        http.formLogin()
                .loginPage("/admin_login") // 设置自定义登录页面
                .usernameParameter("username")  // 设置登录表单中的用户名字段
                .passwordParameter("password")  // 设置登录表单中的密码字段
                .loginProcessingUrl("/user/login")  // 设置登录表单提交的URL
                .successHandler(new MyLoginSuccessHandler()) // 设置登录成功处理器
                .failureForwardUrl("/login_fail"); // 设置登录失败后跳转的页面

        // 配置权限拦截规则
        http.authorizeRequests()
                .antMatchers("/user/login").permitAll() // 允许所有用户访问登录接口
                .antMatchers("/login_fail").permitAll()  // 允许所有用户访问登录失败页面
                .antMatchers("/admin_login").permitAll() // 允许所有用户访问登录页面
                .antMatchers("/checkCode*").permitAll()  // 允许所有用户访问验证码接口
                .antMatchers("/backstage/**").permitAll()   // 允许所有用户访问静态资源
                .antMatchers("/**").authenticated();     // 其他请求都需要认证

        // 配置退出登录
        http.logout()
                .logoutUrl("/user/logout") // 设置退出登录的URL
                .logoutSuccessUrl("/admin_login")  // 设置退出登录成功后跳转的页面
                .clearAuthentication(true)   // 退出登录成功后清除认证信息
                .invalidateHttpSession(true); // 退出登录成功后清除session

        // 添加自定义过滤器，在UsernamePasswordAuthenticationFilter之前执行
        http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);

        // 禁用CSRF保护
        http.csrf().disable();
        // 启用跨域访问
        http.cors();
    }

    // 配置密码加密器
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        // 返回一个BCrypt密码加密器实例
        return new BCryptPasswordEncoder();
    }
}
