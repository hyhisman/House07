package com.team.house.mapper;

import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    //批量删除
    @Delete("delete from type where id in (${ids}) ")
    int delSomeType(@Param("ids") String ids);
}