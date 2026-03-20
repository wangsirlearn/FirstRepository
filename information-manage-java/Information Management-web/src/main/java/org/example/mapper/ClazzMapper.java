package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.popj.Clazz;
import org.example.popj.ClazzQuery;

import java.util.List;
@Mapper
public interface ClazzMapper {
    /**
     * 分页查询
     * @param clazzQuery
     * @return
     */
    //在clazz表中根据name，begin,end 进行模糊查询
    @Select("select * from clazz where name like '%${clazzQuery.name}%' and begin_date>=#{clazzQuery.begin} and end_date<=#{clazzQuery.end}")
    List<Clazz> findPage(@Param("clazzQuery") ClazzQuery clazzQuery);

    /**
     * 删除班级
     * @param id
     */
    @Delete("delete from clazz where id=#{id}")
    void deleteClazz(int id);

    /**
     * 添加班级
     * @param clazz
     */
    //添加班级

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
            "values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void addClazz(Clazz clazz);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Select("select * from clazz where id=#{id}")
    Clazz findById(Integer id);

    /**
     * 修改班级
     * @param clazz
     */
    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void updateClazz(Clazz clazz);
    /**
     * 获取所有班级信息
     * @return
     */
    @Select("select * from clazz")
    List<Clazz> findAll();



}
