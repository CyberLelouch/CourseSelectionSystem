package com.xuan.selectcourse.pojo;

// 导入相关类和注解
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

// 使用Lombok注解@Data，自动生成Getter, Setter, toString等方法
@Data
public class User {

    // 标识id为主键
    @TableId
    private Integer id;

    // 用户名
    private String username;

    // 用户密码
    private String password;

    // 用户状态 (如 0: 教师, 1: 学生, 2: 管理员等)
    private int status;

    // 用户权限列表，使用@TableField注解标识该字段不属于数据库字段
    @TableField(exist = false)
    private List<Permission> permissions;
}
