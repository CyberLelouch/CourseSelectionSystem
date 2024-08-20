package com.xuan.selectcourse.controller;

import com.xuan.selectcourse.pojo.Class;
import com.xuan.selectcourse.service.ClassService;
import com.xuan.selectcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    //查询班级
    @RequestMapping("/all")
    public ModelAndView findClass(){
        List<Class> classList =classService.findClass();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("classList",classList);
        modelAndView.setViewName("/student_add");
        return modelAndView;
    }

}
