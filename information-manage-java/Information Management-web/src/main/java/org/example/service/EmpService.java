package org.example.service;


import org.example.popj.Emp;
import org.example.popj.EmpQuery;
import org.example.popj.PageResult;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
    PageResult findSplit(int page, int pageSize);

//    PageResult findSplitSpecial(String name, int gender, LocalDate begin, LocalDate end, int page, int pageSize);
PageResult findSplitSpecial(EmpQuery  query);

     void save(Emp emp);

    void remove(List<Integer> ids);

    Emp findById(Integer id);

    void update(Emp emp);

    List<Emp> findAll();

}

