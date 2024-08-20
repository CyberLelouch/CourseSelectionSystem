package com.xuan.selectcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//页面跳转
@Controller
public class PageController {
    //访问指定页面
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return "/"+page;
    }
    //忽略访问项目logo
    @RequestMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon(){}

}
