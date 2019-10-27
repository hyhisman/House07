package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Type;
import com.team.house.util.PageUtil;

import java.util.List;

public interface TypeService {

    //一功能一方法
    List<Type> getAllType();

    //查询所有区域支持分页
    PageInfo<Type> getTypeByPage(PageUtil pageInfo);

    //实现添加
    int addType(Type type);

    //修改显示
    Type getType(Integer id);

    //实现修改
    int updateType(Type type);

    //删除区域
    int delType(Integer id);

    //批量删除
    int deleteSomeType(String ids);
}
