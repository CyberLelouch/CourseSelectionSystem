package com.xuan.selectcourse.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;


@Data
public class Admin {
    @TableId
    private Integer id;
    private String username;
    private String name;
    private String phoneNumber;
    private String email;
    @TableField(exist = false)  //不是数据库的字段
    private List<Role> roleList;
    @TableField(exist = false) //不是数据库的字段
    private User user;

}
