package com.xuan.selectcourse.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Syllabus {
    @TableId
    private Integer id;
    private Integer class_hour;
    private Integer credit;
    private String address;
    private Integer number;
    private Integer teacher_id;
    private Integer course_id;
}
