package com.xuan.selectcourse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuan.selectcourse.mapper.UserMapper;
import com.xuan.selectcourse.pojo.Teacher;
import com.xuan.selectcourse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
//创建一个服务类UserService，用来封装用户的业务逻辑：
@Service
public class UserService {
    //自动装配bean对象
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //根据账户查询用户
    public User findUserByUsername(String username){
        /*QueryWrapper<User>：QueryWrapper 是 MyBatis-Plus 提供的一个工具类，用于构建查询条件。
        这里使用了泛型 User，表明这个查询包装器是针对 User实体类型的。
         wrapper：声明并初始化一个 QueryWrapper 对象，命名为 wrapper。
        new QueryWrapper<>()：创建一个新的 QueryWrapper 对象。*/
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",username);//字段名和字段值
       /* User user：声明一个 User 类型的变量 user，用于接收查询结果。
        userMapper：假设这是一个 MyBatis 的 mapper 接口实例，负责与数据库进行交互。
        .selectOne(wrapper)：selectOne 方法执行查询操作，根据提供的查询条件返回一条记录。wrapper 参数包含查询条件。*/
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    //添加用户
    public Integer addUser(User user){
        user.setPassword(encoder.encode("123456"));
        userMapper.insert(user);
        return user.getId();
    }

    //删除用户
    public Integer delete(String username){
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        userMapper.delete(wrapper);
        return user.getId();
    }

    //根据用户账号查询用户权限
    public List<User> findPermissionByUsername(String username){
        return userMapper.findPermissionsByUsername(username);
    }

    //根据用户账号删除用户所有角色
    public void deleteRoleByUsername(String username){
        userMapper.deleteRoleByUsername(username);
    }

    //添加用户时添加角色
    public void addUserRole(Integer uid,Integer rid){
        userMapper.addUserRole(uid,rid);
    }
    //删除用户时删除角色
    public void deleteUserRole(Integer id){
        userMapper.deleteUserRole(id);
    }

}
