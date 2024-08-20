package com.xuan.selectcourse.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.pojo.Permission;
import com.xuan.selectcourse.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/all")

    public ModelAndView all(@RequestParam(defaultValue = "1")int page,
                            @RequestParam(defaultValue = "5") int size){
        Page<Permission> permissionPage = permissionService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionPage",permissionPage);
        modelAndView.setViewName("/permission_all");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Permission permission){
        permissionService.add(permission);
        return "redirect:/permission/all";
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer pid){
        Permission permission = permissionService.findById(pid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permission",permission);
        modelAndView.setViewName("/permission_edit");
        return modelAndView;

    }

    @RequestMapping("/update")
    public String update(Permission permission){
        permissionService.update(permission);
        return "redirect:/permission/all";
    }

    @RequestMapping("/delete")
    public String delete(Integer pid){
        permissionService.delete(pid);
        return "redirect:/permission/all";
    }
}
