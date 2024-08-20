package com.xuan.selectcourse.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.bean.RoleWithStatus;
import com.xuan.selectcourse.mapper.AdminMapper;
import com.xuan.selectcourse.mapper.RoleMapper;
import com.xuan.selectcourse.pojo.Admin;
import com.xuan.selectcourse.pojo.Role;
import com.xuan.selectcourse.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;

    //分页查询所有管理员信息
    public Page<Admin> findPage(int page, int size){
        Page selectPage=adminMapper.selectPage(new Page(page,size),null);
        return selectPage;
    }


    //超级管理员新增管理员账号
    public Integer addAdmin(Admin admin){
        return adminMapper.insert(admin);
    }

    //删除管理员账号
    public void deleteAdmin(Integer id){
        adminMapper.deleteById(id);
    }

    //根据id查找管理员信息
    public  Admin findAdminById(Integer id){
        return adminMapper.selectById(id);
    }

    //修改管理员信息
    public void updateAdmin(Admin admin){
        adminMapper.updateById(admin);
    }

    //查看管理员详情
    public Admin findDesc(Integer id){
        return adminMapper.findDesc(id);
    }

    //查询管理员的角色情况
    public List<RoleWithStatus> findRole(Integer aid){
        //1.查询所有角色
        List<Role> roles = roleMapper.selectList(null);

        //2.查询用户拥有的角色
        List<Integer> rids=roleMapper.findRoleIdByAdmin(aid);
        for (Integer r:rids
             ) {
            System.out.println("角色ID："+r);
        }
        //3.构建带有状态的角色集合，拥有的将拥有状态设为true，否则为false
        List<RoleWithStatus> roleList = new ArrayList();
        for (Role role :roles) {
            //创建带有状态的角色
            RoleWithStatus roleWithStatus=new RoleWithStatus();
            //复制对象的属性，只有相同属性名部分才能复制
            BeanUtils.copyProperties(role,roleWithStatus);
            roleWithStatus.setRid(role.getId());
            if(rids.contains(role.getId())){  //用户拥有该角色
                roleWithStatus.setAdminHas(true);
            }else{
                roleWithStatus.setAdminHas(false);
            }
            roleList.add(roleWithStatus);
        }
        return roleList;
    }

    //修改管理员角色
    public void updateRole(Integer aid,Integer[] ids){
        //删除管理员的所有角色
        adminMapper.deleteAdminAllRoles(aid);
        //重新给管理员添加角色
        for (Integer rid:ids){
            adminMapper.addAdminRole(aid,rid);
        }
    }


}
