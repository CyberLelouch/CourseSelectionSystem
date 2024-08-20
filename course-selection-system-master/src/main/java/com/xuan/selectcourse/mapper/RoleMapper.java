package com.xuan.selectcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuan.selectcourse.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Integer> findRoleIdByAdmin(Integer id);

    void deleteRoleAllPermission(Integer rid);

    void addRolePermission(@Param("rid") Integer rid,@Param("pid") Integer pid);
}
