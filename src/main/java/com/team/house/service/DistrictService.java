package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Street;
import com.team.house.util.PageUtil;

import java.util.List;

public interface DistrictService {

    //一功能一方法
    List<District> getAllDistrict();

    //查询所有区域支持分页
    PageInfo<District> getDisrictByPage(PageUtil pageInfo);

    //实现添加
    int addDistrict(District district);

    //修改显示
    District getDistrict(Integer id);

    //实现修改
    int updateDistrict(District district);

    //删除区域
    int delDistrict(Integer id);

    //批量删除
    int deleteSomeDistrict(String ids);

    PageInfo<Street> getStreet(PageUtil pageInfo, Integer id);


    /**
     * 通过区域查询对应的街道
     * @param disstrictId
     * @return
     */
    List<Street> getStreetByDistrictId(Integer disstrictId);
}
