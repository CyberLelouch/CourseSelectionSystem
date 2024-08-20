package com.xuan.selectcourse.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.pojo.Class;
import com.xuan.selectcourse.pojo.Course;
import com.xuan.selectcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //分页查询所有课程信息
    @RequestMapping("/all")
    public ModelAndView showPage(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int size){
        Page<Course> coursePage=courseService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("coursePage",coursePage);
        modelAndView.setViewName("/course_all");
        return modelAndView;
    }

    //管理员新增课程
    @RequestMapping("/add")
    @ResponseBody  //只返回json数据，不进行页面跳转
    public String addCourse(Course course){
        List<Course> courseList = courseService.findByCourseName(course.getCourseName());
        if (courseList.size()==0){
            courseService.addCourse(course);
            return "添加成功";
        }else {
            return "添加失败";
        }

    }

    //管理员删除课程
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteCourse(Integer courseId){
        //根据id查询出课程
        Course course = courseService.findCourseById(courseId);
        //根据课程id删除课程信息
        courseService.deleteCourse(course.getId());
        //根据课程id在课程安排表中删除有该课程的所有信息

        return "删除成功";
    }

    //管理员跳转到修改课程信息页面
    @RequestMapping("/updateById")
    public ModelAndView updateById(Integer id){
        Course course = courseService.findCourseById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("course",course);
        modelAndView.setViewName("/course_update");
        return modelAndView;
    }

    //管理员修改课程信息
    @RequestMapping("/update")
    @ResponseBody
    public String updateCourse(Course course){
        courseService.updateCourse(course);
        return "修改成功";
    }

    //教师开课查询课程库所有课程
    @RequestMapping("/findAll")
    public ModelAndView findClass(){
        List<Course> courseList =courseService.findCourse();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("courseList",courseList);
        modelAndView.setViewName("/syllabus_add");
        return modelAndView;
    }

}
