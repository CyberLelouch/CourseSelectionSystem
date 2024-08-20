package com.xuan.selectcourse.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.bean.PermissionWithStatus;
import com.xuan.selectcourse.pojo.Role;
import com.xuan.selectcourse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/all")

    public ModelAndView all(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "5") int size){
        Page<Role> rolePage = roleService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rolePage",rolePage);
        modelAndView.setViewName("/role_all");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Role role){
        roleService.add(role);
        return "redirect:/role/all";
    }

    //查询要修改的角色
    @RequestMapping("/edit")
    public ModelAndView edit(Integer rid){
        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.findById(rid);
        modelAndView.addObject("role",role);
        modelAndView.setViewName("/role_edit");
        return modelAndView;
    }
    //修改角色
    @RequestMapping("/update")
    public String update(Role role){
        roleService.update(role);
        return "redirect:/role/all";
    }
    //删除角色
    @RequestMapping("/delete")
    public String delete(Integer rid){
        roleService.delete(rid);
        return "redirect:/role/all";
    }
    //查询角色的权限情况
    @RequestMapping("/findPermission")
    public ModelAndView findPermission(Integer rid){
        ModelAndView modelAndView = new ModelAndView();
        List<PermissionWithStatus> permissions = roleService.findPermission(rid);
        modelAndView.addObject("permissions",permissions);
        modelAndView.addObject("rid",rid);
        modelAndView.setViewName("/role_permission");
        return modelAndView;
    }
    //给角色重新分配权限
    @RequestMapping("/updatePermission")
    public String updatePermission(Integer rid,Integer[] ids){
        roleService.updatePermissions(rid, ids);
        return "redirect:/role/all";
    }
}
