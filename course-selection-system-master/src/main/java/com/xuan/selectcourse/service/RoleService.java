package com.xuan.selectcourse.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.bean.PermissionWithStatus;
import com.xuan.selectcourse.mapper.PermissionMapper;
import com.xuan.selectcourse.mapper.RoleMapper;
import com.xuan.selectcourse.pojo.Permission;
import com.xuan.selectcourse.pojo.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    //分页
    public Page<Role> findPage(int page, int size){
        return roleMapper.selectPage(new Page(page,size),null);
    }

    //新增角色
    public void add(Role role){
        roleMapper.insert(role);
    }

    //根据id查询角色
    public Role findById(Integer rid){
        return roleMapper.selectById(rid);
    }

    //修改角色
    public void update(Role role){
        roleMapper.updateById(role);
    }

    //删除角色
    public void delete(Integer rid){
        roleMapper.deleteById(rid);
    }

    //查询角色的权限情况
    public List<PermissionWithStatus> findPermission(Integer rid){
        //查询所有权限
        List<Permission> permissions = permissionMapper.selectList(null);
        //查询角色拥有的权限id
        List<Integer> pids = permissionMapper.findPermissionIdByRole(rid);
        //构建带有状态的权限集合
        List<PermissionWithStatus> permissionList=new ArrayList<>();
        for (Permission permission:permissions) {
            //创建带有状态的权限
            PermissionWithStatus permissionWithStatus=new PermissionWithStatus();

            BeanUtils.copyProperties(permission,permissionWithStatus);

            //判断角色是否拥有该权限
            if (pids.contains(permission.getId())){
                permissionWithStatus.setRoleHas(true);
            }else{
                permissionWithStatus.setRoleHas(false);
            }
            permissionList.add(permissionWithStatus);
        }
        return permissionList;
    }

    //给角色重新分配权限
    public void updatePermissions(Integer rid,Integer[] ids){
        //删除角色的所有权限
        roleMapper.deleteRoleAllPermission(rid);
        //
        for (Integer pid:ids) {
            roleMapper.addRolePermission(rid,pid);
        }
    }

}
