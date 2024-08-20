package com.xuan.selectcourse.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

//带有状态的角色，状态表示用户是否拥有该角色
@Data
public class RoleWithStatus {
    @TableId
    private Integer rid;
    private String roleName;  //角色名
    private String roleDesc;  //角色介绍
    private Boolean adminHas;  //用户是否拥有该角色
}
