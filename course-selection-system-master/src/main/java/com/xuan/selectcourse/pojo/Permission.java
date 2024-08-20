package com.xuan.selectcourse.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Permission {
    @TableId
    private Integer id;
    private String permission;
    private String url;

}
