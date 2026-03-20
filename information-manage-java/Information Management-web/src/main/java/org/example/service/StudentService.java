package org.example.service;

import org.example.popj.Student;
import org.example.popj.StudentPageResult;
import org.example.popj.StudentQuery;

import java.util.List;

public interface StudentService {
    StudentPageResult findSplit(StudentQuery studentQuery);

    void deleteStudent(List<Integer> ids);

    void addStudent(Student student);

    Student findById(Integer id);

    void updateStudent(Student student);

    void handleViolation(Integer id, Short score);
}
