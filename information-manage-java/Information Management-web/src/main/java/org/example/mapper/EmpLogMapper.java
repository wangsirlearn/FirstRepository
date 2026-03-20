package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.popj.EmpLog;

@Mapper
public interface EmpLogMapper {

    @Insert("insert into emp_log(id,operate_time,info) values(#{empLog.id},#{empLog.operateTime},#{empLog.info})")
    void save(@Param("empLog") EmpLog empLog);
}
