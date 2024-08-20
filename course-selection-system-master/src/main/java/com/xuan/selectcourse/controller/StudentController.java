package com.xuan.selectcourse.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xuan.selectcourse.bean.StudentVO;
import com.xuan.selectcourse.pojo.Class;
import com.xuan.selectcourse.pojo.Student;
import com.xuan.selectcourse.pojo.User;
import com.xuan.selectcourse.service.ClassService;
import com.xuan.selectcourse.service.StudentService;
import com.xuan.selectcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassService classService;
    //多表分页查询学生所有信息
    @RequestMapping("/all")
    public ModelAndView showPage(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int size){
        IPage<StudentVO> studentVOIPage = studentService.showPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentVOIPage",studentVOIPage);
        modelAndView.addObject("condition","");
        modelAndView.setViewName("/student_all");
        return modelAndView;
    }

    //模糊查询学生信息
    @RequestMapping("/findByLike")
    public ModelAndView showPageByLike(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int size, String condition) {

        IPage<StudentVO> studentVOIPage = studentService.findPageByLike(page, size, condition);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentVOIPage", studentVOIPage);
        modelAndView.addObject("condition", condition);
        modelAndView.setViewName("/student_all");
        return modelAndView;
    }

    //管理员新增学生账号
    @RequestMapping("/add")
    @ResponseBody  //只返回json数据，不进行页面跳转
    public String addStudent(Student student,String className){
        User userByUsername = userService.findUserByUsername(student.getUsername());
        if (userByUsername==null){
            Class aClass =classService.findByClassName(className);
            student.setClass_id(aClass.getId());
            User user = new User();
            user.setUsername(student.getUsername());
            user.setStatus(1);
            Integer uid = userService.addUser(user);
            userService.addUserRole(uid,3);
            studentService.addStudent(student);
            return "添加成功";
        }else {
            return "添加失败";
        }

    }

    //管理员删除学生账号
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteStudent(Integer studentId){
        //根据id查询出学生
        Student student = studentService.findStudentById(studentId);
        //根据学生id删除学生信息
        studentService.deleteStudent(student.getId());
        //根据学生学号删除用户中的该账号信息
        Integer uid = userService.delete(student.getUsername());
        userService.deleteUserRole(uid);
        return "删除成功";
    }

    //查询班级并跳转到修改学生信息页面
    @RequestMapping("/updateById")
    public ModelAndView findClassAndUpdateStudent(Integer id){
        List<Class> classList =classService.findClass();
        Student student=studentService.findStudentById(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("classList",classList);
        modelAndView.addObject("student",student);
        modelAndView.setViewName("/student_update");
        return modelAndView;

    }

    //管理员修改学生信息
    @RequestMapping("/update")
    @ResponseBody
    public String updateStudent (Student student,String className){
        Class aClass=classService.findByClassName(className);
        student.setClass_id(aClass.getId());
        studentService.updateStudent(student);
        return "修改成功";
    }

}
