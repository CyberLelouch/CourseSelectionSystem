package com.xuan.selectcourse.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.pojo.Teacher;
import com.xuan.selectcourse.pojo.User;
import com.xuan.selectcourse.service.TeacherService;
import com.xuan.selectcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    //分页查询所有教师信息
    @RequestMapping("/all")
    public ModelAndView showPage(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int size){
        Page<Teacher> teacherPage=teacherService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacherPage",teacherPage);
        modelAndView.addObject("condition","");
        modelAndView.setViewName("/teacher_all");
        return modelAndView;
    }

    //模糊查询教师信息
    @RequestMapping("/findByLike")
    public ModelAndView showPageByLike(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int size,String condition) {
        Page<Teacher> teacherPage = teacherService.findPageByLike(page, size,condition);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacherPage", teacherPage);
        modelAndView.addObject("condition", condition);
        modelAndView.setViewName("/teacher_all");
        return modelAndView;
    }
    //管理员新增教师账号
    @RequestMapping("/add")
    @ResponseBody  //只返回json数据，不进行页面跳转
    public String addTeacher(Teacher teacher){
        User user =userService.findUserByUsername(teacher.getUsername());
        if (user==null){
            User user1 = new User();
            user1.setUsername(teacher.getUsername());
            user1.setStatus(0);
            Integer uid = userService.addUser(user1);
            userService.addUserRole(uid,2);
            teacherService.addTeacher(teacher);
            return "添加成功";
        }else {
            return "添加失败";
        }



    }

    //管理员删除教师账号
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteTeacher(Integer teacherId){
        //根据id查询出教师
        Teacher teacher = teacherService.findTeacherById(teacherId);

        //根据教师id删除教师信息
        teacherService.deleteTeacher(teacher.getId());

        //根据教师工号删除用户中的该账号信息
        Integer uid = userService.delete(teacher.getUsername());
        userService.deleteUserRole(uid);
        return "删除成功";
    }

    //管理员跳转到修改教师信息页面
    @RequestMapping("/updateById")
    public ModelAndView updateById(Integer id){
        Teacher teacher = teacherService.findTeacherById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacher",teacher);
        modelAndView.setViewName("/teacher_update");
        return modelAndView;
    }

    //管理员修改教师信息
    @RequestMapping("/update")
    @ResponseBody
    public String updateTeacher(Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "修改成功";
    }


}
