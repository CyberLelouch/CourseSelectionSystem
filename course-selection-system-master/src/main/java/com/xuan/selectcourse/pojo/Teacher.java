package com.xuan.selectcourse.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
//教师表
@Data
public class Teacher {
    @TableId
    private Integer id;
    private String username;
    private String name;
    private String sex;
    private String phoneNumber;
    private String email;
    private String position;
}
