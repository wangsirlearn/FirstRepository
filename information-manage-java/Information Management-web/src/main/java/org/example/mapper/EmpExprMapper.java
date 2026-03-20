package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.popj.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 批量保存员工工作经历信息
     */

//    @Insert("insert into emp_expr(emp_id,begin,end,company,job) values(#{expr.empId},#{expr.begin},#{expr.end},#{expr.company},#{expr.job})")
//    boolean saveUserExper(@Param("expr") List<EmpExpr> expr); 使用expr的属性 这么用@ Param

    public boolean saveUserExper(List<EmpExpr> expr);
    /**
     * 批量删除员工工作经历信息
     */


    void remove(List<Integer> ids);

    /**
     * 根据员工id查询员工工作经历信息
     */
    @Select("select * from emp_expr where emp_id = #{id}")
    List<EmpExpr> findById(Integer id);

    /**
     * 修改员工工作经历信息
     */

    @Update("update emp_expr set begin = #{expr.begin},end = #{expr.end},company = #{expr.company},job = #{expr.job},emp_id = #{empId} where id = #{expr.id}")
    void update(List<EmpExpr> empExprs);
}
