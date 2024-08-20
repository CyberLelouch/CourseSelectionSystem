package com.xuan.selectcourse.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xuan.selectcourse.bean.SyllabusVO;
import com.xuan.selectcourse.pojo.Student;
import com.xuan.selectcourse.pojo.Syllabus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SyllabusMapper extends BaseMapper<Syllabus> {
    //教师分页查询课程计划
    IPage<SyllabusVO> showSyllabusVOPage(IPage<SyllabusVO> page, @Param(Constants.WRAPPER)QueryWrapper<SyllabusVO> wrapper);

    //学生分页查询可选课程计划
    IPage<SyllabusVO> showSelectSyllabusVOPage(IPage<SyllabusVO> page, @Param(Constants.WRAPPER)QueryWrapper<Student> wrapper);

    //学生分页查询已选课程计划
    IPage<SyllabusVO> showSelectedSyllabusVOPage(IPage<SyllabusVO> page, @Param(Constants.WRAPPER)QueryWrapper<Student> wrapper);

    //退选
    void deselect(@Param("studentId") Integer studentId,@Param("syllabusId") Integer syllabusId);

    //选课
    void selectCourse(@Param("studentId") Integer studentId,@Param("syllabusId") Integer syllabusId);

}

