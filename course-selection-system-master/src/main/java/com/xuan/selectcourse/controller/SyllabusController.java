package com.xuan.selectcourse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xuan.selectcourse.bean.SyllabusVO;
import com.xuan.selectcourse.pojo.*;
import com.xuan.selectcourse.pojo.Class;
import com.xuan.selectcourse.service.CourseService;
import com.xuan.selectcourse.service.SyllabusService;
import com.xuan.selectcourse.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/syllabus")
public class SyllabusController {
    @Autowired
    private SyllabusService syllabusService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    //教师分页查询开课信息
    @RequestMapping("/all")
    public ModelAndView showTeacherSyllabusPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            String teacherName){
        ModelAndView modelAndView = new ModelAndView();
        IPage<SyllabusVO> syllabusVOIPage = syllabusService.showOpenCoursePage(page, size, teacherName);
        modelAndView.addObject("syllabusVOIPage",syllabusVOIPage);
        modelAndView.setViewName("/syllabus_all");
        return modelAndView;
    }

    //新增课程计划
    @RequestMapping("/add")
    @ResponseBody  //只返回json数据，不进行页面跳转
    public String addSyllabus(SyllabusVO syllabusVO,String username){
        syllabusService.addSyllabus(syllabusVO,username);
        return "添加成功";
    }

    //删除课程计划
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteSyllabus(Integer syllabusId){
        //根据id删除出课程计划
        syllabusService.deleteSyllabus(syllabusId);
        return "删除成功";
    }

    //查询课程并跳转到修改课程计划信息页面
    @RequestMapping("/updateById")
    public ModelAndView findCourseAndUpdateSyllabus(Integer id){
        List<Course> courseList =courseService.findCourse();
        Syllabus syllabus=syllabusService.findSyllabusById(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("courseList",courseList);
        modelAndView.addObject("syllabus",syllabus);
        modelAndView.setViewName("/syllabus_update");
        return modelAndView;

    }

    //教师修改课程计划信息
    @RequestMapping("/update")
    @ResponseBody
    public String updateSyllabus (Syllabus syllabus,String courseName,String username){

        Integer courseId = courseService.findIdByCourseName(courseName);
        syllabus.setCourse_id(courseId);
        Integer teacherId = teacherService.findIdByUsername(username);
        syllabus.setTeacher_id(teacherId);
        System.out.println(syllabus);
        syllabusService.updateSyllabus(syllabus);
        return "修改成功";
    }

    //学生查看可选课程计划
    @RequestMapping("/selectCourse")
    public ModelAndView showStudentSelectSyllabusPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            String username){
        ModelAndView modelAndView = new ModelAndView();
        IPage<SyllabusVO> syllabusVOIPage = syllabusService.showSelectCoursePage(page, size, username);
        modelAndView.addObject("syllabusVOIPage",syllabusVOIPage);
        modelAndView.setViewName("/syllabus_all_select");
        return modelAndView;
    }

    //学生查看已选课程计划
    @RequestMapping("/selectedCourse")
    public ModelAndView showStudentSelectedSyllabusPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            String username){
        ModelAndView modelAndView = new ModelAndView();
        IPage<SyllabusVO> syllabusVOIPage = syllabusService.showSelectedCoursePage(page, size, username);
        modelAndView.addObject("syllabusVOIPage",syllabusVOIPage);
        modelAndView.setViewName("/syllabus_all_selected");
        return modelAndView;
    }

    //学生退选
    @RequestMapping("/deselect")
    @ResponseBody
    public String studentDeselect(String username,Integer syllabusId){
        syllabusService.deselect(username,syllabusId);
        return "退选成功";
    }

    //学生选课
    @RequestMapping("/selectSyllabus")
    @ResponseBody
    public String studentSelectCourse(String username,Integer syllabusId){
        syllabusService.selectCourse(username,syllabusId);
        return "选课成功";
    }
}
