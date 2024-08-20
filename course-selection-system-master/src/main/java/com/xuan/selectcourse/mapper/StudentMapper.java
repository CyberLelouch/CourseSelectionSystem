package com.xuan.selectcourse.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xuan.selectcourse.bean.StudentVO;
import com.xuan.selectcourse.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    List<StudentVO> showStudentVO();
    IPage<StudentVO> showStudentVOPage(IPage<StudentVO> page, @Param(Constants.WRAPPER) QueryWrapper<StudentVO> wrapper );
}
