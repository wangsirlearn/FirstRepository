package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.mapper.DeptMapper;
import org.example.mapper.EmpMapper;
import org.example.popj.*;
import org.example.service.ClazzService;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {


    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private EmpMapper empMapper;
    /**
     * 分页查询
     * @param clazzQuery
     * @return
     */
    @Override
    public ClazzPageResult findPage(ClazzQuery clazzQuery) {
        PageHelper.startPage(clazzQuery.getPage(),clazzQuery.getPageSize());

        List<Clazz> rows = clazzMapper.findPage(clazzQuery);
        rows.forEach(clazz -> {
            Emp dept = empMapper.findById(clazz.getMasterId());
            clazz.setMasterName(dept.getName());
            clazz.setStatus(clazz.getEndDate().isAfter(LocalDate.now())?"正常":"已结束");

        });
        Page<Clazz> pages = (Page<Clazz>) rows;

        return new ClazzPageResult(pages.getTotal(),pages.getResult());

    }

    /**
     * 删除班级
     */
    @Override

    public void deleteClazz(int id) {
        clazzMapper.deleteClazz(id);
    }

    /**
     * 添加班级
     * @param clazz
     */

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);

    }


    /**
     * 根据ID查询
     */
    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    /**
     * 修改班级
     */
    @Override
    public void updateClazz(Clazz clazz) {
        clazzMapper.updateClazz(clazz);
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> findAll() {
        List<Clazz> clazzes = clazzMapper.findAll();
        return clazzes;
    }
}
