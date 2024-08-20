package com.xuan.selectcourse.security;

// 导入相关类和注解
import com.xuan.selectcourse.pojo.Permission;
import com.xuan.selectcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 自定义认证授权逻辑的服务类，标记为Spring服务组件
@Service
public class MyUserDetailService implements UserDetailsService {

    // 自动注入UserService，用于获取用户信息和权限
    @Autowired
    private UserService userService;

    // 根据用户名加载用户信息并进行认证和授权
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 认证：通过用户名查找用户
        com.xuan.selectcourse.pojo.User user = userService.findUserByUsername(username);
        if (user == null) {
            // 如果用户不存在，抛出异常
            throw new UsernameNotFoundException("用户不存在");
        }

        // 2. 授权：获取用户的权限信息
        List<com.xuan.selectcourse.pojo.User> userPermissions = userService.findPermissionByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 遍历用户权限，添加到grantedAuthorities列表中
        for (com.xuan.selectcourse.pojo.User user1 : userPermissions) {
            List<Permission> permissions = user1.getPermissions();
            for (Permission permission : permissions) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getUrl()));
            }
        }

        // 封装为UserDetails对象，包含用户名、密码和权限信息
        UserDetails userDetails = User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(grantedAuthorities) // 设置用户的权限
                .build();

        // 返回UserDetails对象
        return userDetails;
    }
}
