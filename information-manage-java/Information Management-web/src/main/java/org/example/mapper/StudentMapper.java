package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.popj.Student;
import org.example.popj.StudentQuery;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 分页条件查询
     * @return
     */
    @Select("select * from student where name like '%${studentQuery.name}%' and degree=#{studentQuery.degree} and clazz_id=#{studentQuery.classId}")
    List<Student> findAll(@Param("studentQuery") StudentQuery studentQuery);

    /**
     * 批量删除学生
     */
    void deleteStudent(List<Integer> ids);

    /**
     * 添加学生
     */
    @Insert("insert into student(name,no,gender,phone,degree,class_id,is_card,is_college,address,graduation_date) values(#{name},#{no},#{gender},#{degree},#{classId},#{isCard},#{isCollege},#{address},#{graduationDate})")
    void addStudent(Student student);

    /**
     * 根据ID查询
     */
    @Select("select * from student where id= #{id}")
    Student findById(Integer id);

    /**
     * 修改学生
     */
    @Update("update student set id=#{id} name=#{name},no=#{no},gender=#{gender},phone=#{phone},degree=#{degree},class_id=#{classId},is_card=#{isCard},is_college=#{isCollege},address=#{address},graduation_date=#{graduationDate},violation_count=#{violationCount},violation_score=#{violationScore} where id=#{id}")
    void updateStudent(Student student);
    /**
     * 处理违纪
     */
    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score} where id=#{id}")
    void handleViolation(Integer id,Short score);
}

