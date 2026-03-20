package org.example.service;

import org.example.popj.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 获取所有部门信息
     * @return
     */
    public List<Dept> findAll();

    /**
     * 删除部门信息
     * @param id
     *
     */
    void  deleteDept(int id);

    /**
    * 添加部门信息
     */

    void addDept(Dept dept);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */

    Dept findById(int id);

    /**
     * 修改部门信息
     *
     */

    void updateDept(Dept dept);
}
