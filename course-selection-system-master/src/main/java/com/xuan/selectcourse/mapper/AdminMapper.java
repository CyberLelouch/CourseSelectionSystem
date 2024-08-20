package com.xuan.selectcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuan.selectcourse.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    //查询管理员的详情
    Admin findDesc(Integer id);
    //删除管理员的所有角色
    void deleteAdminAllRoles(Integer id);
    //给管理员添加角色
    void addAdminRole(@Param("aid")Integer aid, @Param("rid")Integer rid);
}
