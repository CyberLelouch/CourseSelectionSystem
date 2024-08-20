package com.xuan.selectcourse.mapper;

// 导入相关类和注解
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuan.selectcourse.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// 标注为MyBatis的Mapper接口
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 自定义方法：根据用户名查询用户的权限。泛型 User，表明这个查询包装器是针对 User 类型的。
    List<User> findPermissionsByUsername(String username);

    // 自定义方法：根据用户名删除用户的所有角色
    void deleteRoleByUsername(String username);

    // 自定义方法：新增用户角色（教师或学生）
    void addUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);

    // 自定义方法：删除用户角色
    void deleteUserRole(Integer id);
}
