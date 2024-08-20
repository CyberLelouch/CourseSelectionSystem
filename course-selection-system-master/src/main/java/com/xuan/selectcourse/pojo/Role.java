package com.xuan.selectcourse.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Role {
    @TableId
    private Integer id;
    private String roleName;
    private String roleDesc;
    @TableField(exist = false) //不是数据库的字段
    private List<Permission> permissionList;
}
