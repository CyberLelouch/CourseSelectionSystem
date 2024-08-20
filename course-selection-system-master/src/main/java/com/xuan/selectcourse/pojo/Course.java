package com.xuan.selectcourse.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Course {
    @TableId
    private Integer id;
    private String courseName;
    private String courseDesc;

}
