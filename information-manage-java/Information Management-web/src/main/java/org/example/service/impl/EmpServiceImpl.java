package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.DeptMapper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.popj.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl  implements EmpService {


    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpLogService empLogService;

    /**
     * 获取分页员工信息
     * @return
     */
    //原始
//    @Override
//    public PageResult findSplit(int page, int pageSize) {
//        Long total = empMapper.count();
//        int start = (page-1)*pageSize;
//        List<Emp> rows = empMapper.findSplit(start, pageSize);
//        return new PageResult(total, rows);
//    }
    //使用pageHelper
    @Override
    public PageResult findSplit(int page, int pageSize) {
        /**
         * 1.使用pageHelper开启分页功能
         * 2.执行查询功能
         * 3.获取数据信息
         * 注意 它仅会对第一次查询有效 sql语句不能加分号结尾
         */
        PageHelper.startPage(page, pageSize);
        //执行查询操作
        List<Emp> lists = empMapper.findSplit();
        //获取数据信息
        Page<Emp> pages = (Page<Emp>) lists;
//        Page<Emp> pages2 = (Page<Emp>) lists; 无效的
        return new PageResult(pages.getTotal(), pages.getResult());
    }

//    @Override
//    public PageResult findSplitSpecial(String name, int gender, LocalDate begin, LocalDate end, int page, int pageSize) {
//        PageHelper.startPage(page, pageSize);
//        List<Emp> lists = empMapper.findSplitSpecial(name, gender, begin, end);
//        Page<Emp> pages = (Page<Emp>) lists;
//        return new PageResult(pages.getTotal(), pages.getResult());
//
//    }
    /**
      * 条件分页查询员工信息
      */

    @Override
    public PageResult findSplitSpecial(EmpQuery  query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        log.info("条件分页查询员工信息："+query.getGender());
        List<Emp> lists = empMapper.findSplitSpecial(query.getName(), query.getGender(), query.getBegin(), query.getEnd());
        Page<Emp> pages = (Page<Emp>) lists;
        return new PageResult(pages.getTotal(), pages.getResult());

    }

    /**
     * 添加员工信息
     */

    @Transactional //事务  要求方法内的操作全部成功或者全部失败 不会出现数据不一致问题
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.saveUser(emp);


            List<EmpExpr> empExprs = emp.getEmpExprs();
            log.debug("保存员工信息："+emp);
            if (!CollectionUtils.isEmpty(empExprs)){
                empExprs.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.saveUserExper(empExprs);
            }
            //不可以是因为依赖注入只能用一次 所以需要新的bean对象
            //empMapper.saveUser(emp)&&empMapper.saveUserExper(emp.getEmpExprs())

        } finally {
            EmpLog empLog = new EmpLog(emp.getId(), LocalDateTime.now(), "添加员工信息"+ emp);
            empLogService.save(empLog);
        }


    }

    /**
     * 批量删除员工数据
     * @param ids
     */
    @Transactional
    @Override
    public void remove(List<Integer> ids) {
        //进行事务管理
        log.debug("批量删除员工信息："+ids);
        empMapper.remove(ids);

        empExprMapper.remove(ids);
    }
    /**
     * 根据id查询员工信息
     */
    @Override
    public Emp findById(Integer id) {
        Emp emp = new Emp();
        emp=empMapper.findById(id);
        List<EmpExpr> empExpr =empExprMapper.findById(id);
        emp.setEmpExprs(empExpr);
        return emp;
    }

    /**
     * 修改员工信息
     */
    @Transactional
    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
        List<EmpExpr> empExprs = emp.getEmpExprs();
        if (!CollectionUtils.isEmpty(empExprs)){
            empExprs.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.update( emp.getEmpExprs());
        }
    }
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Emp> findAll() {
        //修改后的查询 来解决deptName为空的问题 对应前端resetForm（）函数
//        List<Emp> emp = empMapper.findAll();
//        emp.forEach(emp1->emp1.setDeptName(deptMapper.findById(emp1.getDeptId()).getName()));
//        log.info("查询所有员工信息"+emp);
//        return emp;

        return empMapper.findAll();
    }

}
