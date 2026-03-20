package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.popj.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
//    @Results({
//            // 1，可解决字段名和属性名不一致 的问题 column 字段名
//            @Result(column = "create_Time", property = "createTime"),
//            @Result(column = "update_Time", property = "updateTime")
//    })

    // 2，可解决字段名和属性名不一致 的问题 起别名
//    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc ")


    @Select("select * from dept order by update_time desc ")
    public List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    void deleteDept(int id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void addDept(Dept dept);

    @Select("select id, name, create_time, update_time  from dept where id=#{id}")
    Dept findById(int id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updateDept(Dept dept);
}
