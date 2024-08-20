package com.xuan.selectcourse.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Class {
    @TableId
    private Integer id;
    private String className;
    private Integer teacher_id;
}
