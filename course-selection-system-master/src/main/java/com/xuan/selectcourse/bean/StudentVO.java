package com.xuan.selectcourse.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//学生表关联班级表，再关联教师表，多表联查页面展示类
@Data
public class StudentVO {
    private Integer id;
    private String username;
    private String name;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String phoneNumber;
    private Integer classId;
    private String className;
    private Integer teacherId;
    private String teacherName;

}
