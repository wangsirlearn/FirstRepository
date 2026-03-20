package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.popj.Result;
import org.example.popj.Student;
import org.example.popj.StudentPageResult;
import org.example.popj.StudentQuery;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 条件分页查询
     */
    @GetMapping("/students")
    public Result findSplit(StudentQuery studentQuery){
        StudentPageResult spr= studentService.findSplit(studentQuery);
        return Result.success(spr);

    }
    /**
     * 删除学生
     */
    @DeleteMapping("/students/{ids}")
    public Result deleteStudent(@RequestParam List<Integer> ids){
        log.info("删除学生："+ids);
        studentService.deleteStudent(ids);
        return Result.success();
    }

    /**
     * 添加学生
     */
    @PostMapping("/students")
    public Result add(@RequestBody Student student){
        log.debug("添加学生："+student);
        studentService.addStudent(student);
        return Result.success();
    }

    /**
     * 根据id查询
     */
    @GetMapping("/students/{id}")
    public Result findById(@PathVariable Integer id){
        log.debug("查询学生信息："+id);
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    /**
     * 修改学生
     */
    @PutMapping("/students")
    public Result update(@RequestBody Student student){
        log.debug("修改学生信息："+student);
        studentService.updateStudent(student);
        return Result.success();
    }
    /**
     * 违纪处理
     */
    @PutMapping("/students/violation/{id}/{score}")
    public Result handleViolation(@PathVariable Integer id,@PathVariable Short score){
        log.debug("处理学生违纪："+id+"-"+score);
        studentService.handleViolation(id,score);

        return Result.success();
    }



}
