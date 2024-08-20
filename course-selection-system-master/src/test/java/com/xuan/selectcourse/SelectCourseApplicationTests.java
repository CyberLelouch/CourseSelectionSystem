package com.xuan.selectcourse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.bean.StudentVO;
import com.xuan.selectcourse.bean.SyllabusVO;
import com.xuan.selectcourse.mapper.*;
import com.xuan.selectcourse.pojo.Admin;
import com.xuan.selectcourse.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SelectCourseApplicationTests {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SyllabusMapper syllabusMapper;

    //测试多表联查
    @Test
    void moreConnectionFind() {
        List<StudentVO> studentVO = studentMapper.showStudentVO();
        for (StudentVO s:studentVO
             ) {
            System.out.println(s);
        }
    }
    //测试多表条件查询
    @Test
    void showConnectionPage(){
        Page<StudentVO> page=new Page<>(1,2);
        // 手动关闭 SQL 优化，不然查询总数的时候只会查询主表
        page.setOptimizeCountSql(false);
        QueryWrapper<StudentVO> wrapper = new QueryWrapper<>();
        wrapper.like("student.sex","男");
        IPage<StudentVO> iPage = studentMapper.showStudentVOPage(page, wrapper);
        System.out.println("总记录条数"+iPage.getTotal());
        System.out.println("总页数"+iPage.getPages());
        System.out.println("当前页码"+iPage.getCurrent());
        System.out.println("查询数据"+iPage.getRecords());



    }

    //密码加密测试
    @Test
    public void testBCryptPasswordEncoder(){
        //创建解析器
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        //密码加密
        String password = encoder.encode("123456");
        System.out.println("加密后:"+password);

        //密码校验
        /**
         * 参数1:明文密码
         * 参数2:加密密码
         * 返回值：是否校验成功
         */
        boolean result = encoder.matches("zhangsan","$2a$10$0FCa0/3kQeYf14aciBdBie0uRA44pnKjM2OT1y.HAQDF1l6Sd9O9O");
        System.out.println(result);
    }

    //测试用户权限查询
    @Test
    public void testFindPermission(){
        List<User> admin = userMapper.findPermissionsByUsername("admin");
        for (User u:admin
             ) {
            System.out.println(u);
        }
    }

    //测试管理员详情查询
    @Test
    public void testFindDesc(){
        Admin desc = adminMapper.findDesc(1);
        System.out.println(desc.getRoleList());
    }

    //测试删除管理员角色和新增管理员角色
    @Test
    public void testAdminRole(){
        adminMapper.deleteAdminAllRoles(1);
    }

    //测试根据账号删除用户所有角色
    @Test
    public void testDeleteRole(){
        userMapper.deleteRoleByUsername("admin");
    }

    //测试删除角色所有权限
    @Test
    public void testDeleteRolePermission(){
        roleMapper.deleteRoleAllPermission(1);
    }

    //测试添加角色权限
    @Test
    public void testUpdateRolePermission(){
        roleMapper.addRolePermission(1,2);
    }

    //测试多表分页查询开课信息
    @Test
    public void testShowOpenCoursePage(){
        QueryWrapper<SyllabusVO> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher.name","李老师");
        IPage<SyllabusVO> syllabusVOIPage = syllabusMapper.showSyllabusVOPage(new Page<>(1, 2), wrapper);
        System.out.println(syllabusVOIPage);
    }

    //测试新增删除教师学生角色
    @Test
    public void testAddOrDeleteRole(){
        userMapper.addUserRole(27,3);

        //userMapper.deleteUserRole(27);
    }

}
