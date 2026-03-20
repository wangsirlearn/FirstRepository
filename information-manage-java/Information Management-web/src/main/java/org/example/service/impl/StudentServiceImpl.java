package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ClazzMapper;
import org.example.mapper.StudentMapper;
import org.example.popj.Clazz;
import org.example.popj.Student;
import org.example.popj.StudentPageResult;
import org.example.popj.StudentQuery;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public StudentPageResult findSplit(StudentQuery studentQuery) {
        PageHelper.startPage(studentQuery.getPage(),studentQuery.getPageSize());
        List<Student> list=studentMapper.findAll(studentQuery);
        list.forEach(stu->{
            Clazz clazz = clazzMapper.findById(stu.getClazzId());
            String clazzName = clazz.getName();
            stu.setClazzName(clazzName);
        });
        Page<Student> pages= (Page<Student>) list;
        return new StudentPageResult(pages.getTotal(),pages.getResult());
    }

    @Override
    public void deleteStudent(List<Integer> ids) {
        studentMapper.deleteStudent(ids);
    }

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void handleViolation(Integer id, Short score) {
        studentMapper.handleViolation(id,score);

    }
}
