package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.popj.Emp;
import org.example.popj.EmpExpr;
import org.example.popj.EmpQuery;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {


    /**
    分页查询员工信息
     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id ")
//    public Long count();
//    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id limit #{start}, #{pageSize}")
//    List<Emp> findSplit(int start, int pageSize);

    //可以使用pageHelper插件 简化步骤
    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id ")
    List<Emp> findSplit();

    /**
     * 条件分页查询员工信息
     */
//    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id " +
//            "where name like '%${name}%' and gender = ${gender} and entry_date >= ${begin} and entry_date <= ${end}")

    // sql代码太长可使用xml 文件
    List<Emp> findSplitSpecial(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 添加员工信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获得返回的主键并赋值给emp的id属性  主键返回 可以判断正在添加的是哪个emp
    @Insert("insert into emp(username,name,gender,image,dept_id,entry_date,job,salary,phone,create_time,update_time) values (#{username},#{name},#{gender},#{image},#{deptId},#{entryDate},#{job},#{salary},#{phone},#{createTime},#{updateTime})")
    void saveUser(Emp emp);

    /**
     * 批量删除员工信息
     */


    void remove(List<Integer> ids);
    /**
     * 根据id查询员工信息
     */

    @Select("select * from emp where id = #{id}")
    Emp findById(Integer id);
    /**
     * 修改员工信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Update("update emp set username = #{username},name = #{name},gender = #{gender},image = #{image},dept_id = #{deptId},entry_date = #{entryDate},job = #{job},salary = #{salary},phone = #{phone},create_time = #{createTime},update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);
    /**
     * 查询所有员工信息
     */
    @Select("select * from emp ")
    List<Emp> findAll();
}
