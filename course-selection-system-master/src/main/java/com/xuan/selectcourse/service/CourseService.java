package com.xuan.selectcourse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.mapper.CourseMapper;
import com.xuan.selectcourse.mapper.TeacherMapper;
import com.xuan.selectcourse.pojo.Course;
import com.xuan.selectcourse.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    //分页查询所有课程库信息
    public Page<Course> findPage(int page, int size){
        Page selectPage=courseMapper.selectPage(new Page(page,size),null);
        return selectPage;
    }


    //管理员新增课程
    public Integer addCourse(Course course){
        return courseMapper.insert(course);
    }

    //删除课程
    public void deleteCourse(Integer id){
        courseMapper.deleteById(id);
    }

    //根据id查找课程信息
    public  Course findCourseById(Integer id){
        return courseMapper.selectById(id);
    }

    //修改课程信息
    public void updateCourse(Course course){
        courseMapper.updateById(course);
    }
    //根据课程名查询课程
    public List<Course> findByCourseName(String courseName){
        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        wrapper.eq("courseName",courseName);
        return courseMapper.selectList(wrapper);
    }

    //查询所有课程
    public List<Course> findCourse(){
        return courseMapper.selectList(null);
    }

    //根据课程名查找课程id
    public Integer findIdByCourseName(String courseName){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("courseName",courseName);
        Course course = courseMapper.selectOne(wrapper);
        return course.getId();
    }

}
