package com.xuan.selectcourse.bean;

import lombok.Data;

//课程安排表关联教师表关联课程表
@Data
public class SyllabusVO {
    private Integer id;
    private Integer class_hour;
    private Integer credit;
    private String address;
    private Integer number;
    private Integer teacher_id;
    private Integer course_id;
    private String teacherName;
    private String courseName;
}
