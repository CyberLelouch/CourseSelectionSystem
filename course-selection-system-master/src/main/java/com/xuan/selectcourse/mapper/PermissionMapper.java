package com.xuan.selectcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuan.selectcourse.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Integer> findPermissionIdByRole(Integer rid);
}
