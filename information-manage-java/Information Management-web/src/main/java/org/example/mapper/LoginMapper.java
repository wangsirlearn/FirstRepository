package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.popj.Emp;

@Mapper
public interface LoginMapper {

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp login(String username, String password);
}
