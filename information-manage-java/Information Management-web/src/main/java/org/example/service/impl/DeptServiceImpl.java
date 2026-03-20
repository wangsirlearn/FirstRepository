package org.example.service.impl;

import org.example.mapper.DeptMapper;
import org.example.popj.Dept;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public  class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        List<Dept> depts= deptMapper.findAll();
        return depts;
    }

    @Override
    public void deleteDept(int id) {
        deptMapper.deleteDept(id);
    }

    @Override
    public void addDept(Dept dept) {
        //补全属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public Dept findById(int id) {
        return deptMapper.findById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
