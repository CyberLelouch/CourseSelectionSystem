package com.xuan.selectcourse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuan.selectcourse.bean.StudentVO;
import com.xuan.selectcourse.mapper.StudentMapper;
import com.xuan.selectcourse.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //多表分页查询学生信息
    public IPage<StudentVO> showPage(int page,int size){
        QueryWrapper<StudentVO> wrapper = new QueryWrapper<>();
        IPage<StudentVO> studentVOIPage = studentMapper.showStudentVOPage(new Page<>(page, size), wrapper);
        return studentVOIPage;
    }

    //模糊查询学生信息
    public IPage<StudentVO> findPageByLike(int page, int size, String condition){
        QueryWrapper<StudentVO> wrapper = new QueryWrapper();
        wrapper.eq("student.sex",condition)
                .or().eq("className",condition)
                .or().eq("teacher.name",condition)
                .or().eq("student.name",condition)
                .or().like("student.username",condition);
        Page<StudentVO> page1 = new Page(page, size);
        page1.setOptimizeCountSql(false);
        IPage iPage = studentMapper.showStudentVOPage(page1, wrapper);
        return iPage;
    }

    //新增学生账号
    public Integer addStudent(Student student){
        return studentMapper.insert(student);
    }

    //删除学生账号
    public void deleteStudent(Integer id){
        studentMapper.deleteById(id);
    }

    //根据id查找学生信息
    public  Student findStudentById(Integer id){
        return studentMapper.selectById(id);
    }

    //修改学生信息
    public void updateStudent(Student student){
        studentMapper.updateById(student);
    }

}
