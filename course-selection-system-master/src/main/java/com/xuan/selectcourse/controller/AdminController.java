package com.xuan.selectcourse.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.bean.RoleWithStatus;
import com.xuan.selectcourse.pojo.Admin;
import com.xuan.selectcourse.pojo.Role;
import com.xuan.selectcourse.pojo.Teacher;
import com.xuan.selectcourse.pojo.User;
import com.xuan.selectcourse.service.AdminService;
import com.xuan.selectcourse.service.TeacherService;
import com.xuan.selectcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    //分页查询所有管理员信息
    @RequestMapping("/all")
    public ModelAndView showPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        Page<Admin> adminPage=adminService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("adminPage",adminPage);
        modelAndView.setViewName("/admin_all");
        return modelAndView;
    }

    //超级管理员新增管理员账号
    @RequestMapping("/add")
    @ResponseBody  //只返回json数据，不进行页面跳转
    public String addAdmin(Admin admin){
        User user =userService.findUserByUsername(admin.getUsername());
        if (user==null){
            User user1 = new User();
            user1.setUsername(admin.getUsername());
            user1.setStatus(2);
            userService.addUser(user1);
            adminService.addAdmin(admin);
            return "添加成功";
        }else {
            return "添加失败";
        }



    }

    //超级管理员删除管理员账号
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteAdmin(Integer adminId){
        //根据id查询出管理员
        Admin admin = adminService.findAdminById(adminId);
        //根据账号删除该用户的所有角色
        userService.deleteRoleByUsername(admin.getUsername());
        //根据管理员id删除管理员信息
        adminService.deleteAdmin(admin.getId());
        //根据管理员账号删除用户中的该账号信息
        userService.delete(admin.getUsername());

        return "删除成功";
    }

    //超级管理员跳转到修改管理员信息页面
    @RequestMapping("/updateById")
    public ModelAndView updateById(Integer id){
        Admin admin = adminService.findAdminById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin",admin);
        modelAndView.setViewName("/admin_update");
        return modelAndView;
    }

    //超级管理员修改管理员信息
    @RequestMapping("/update")
    @ResponseBody
    public String updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return "修改成功";
    }

    //查看管理员详情
    @RequestMapping("/desc")
    public ModelAndView desc(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Admin admin = adminService.findDesc(id);
        modelAndView.addObject("admin",admin);
        modelAndView.setViewName("/admin_desc");
        return modelAndView;
    }

    //查看管理员所拥有的权限
    @RequestMapping("/findRole")
    public ModelAndView findRole(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        List<RoleWithStatus> roles = adminService.findRole(id);
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("id",id);
        modelAndView.setViewName("/admin_role");
        return modelAndView;
    }

    @RequestMapping("/updateRole")
    public String updateRole(Integer id,Integer[] ids){
        adminService.updateRole(id,ids);
        return "redirect:/admin/all";
    }

}
