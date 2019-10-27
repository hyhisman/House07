package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.util.HouseCondition;
import com.team.house.util.PageUtil;

import java.util.List;

public interface HouseService {

    int addHouse(House house);

    public List<House> getHouseByUserid(Integer uid);

    House getHouseByid(String id);

    int updateHouse(House house);

    /**
     * 查询出租房的审核状态
     * @param state  0未审核  1已审核
     * @return 影响行数
     */
    PageInfo<House> getHouseByPassState(Integer state, PageUtil pageUtil);

    /**
     * 修改出租房的审核状态
     * @param id 编号
     * @param state  0未审核  1已审核
     * @return 影响行数
     */
    int updateHousePassState(String  id,Integer state);

    /**
     *查询所有浏览出租房信息
     * @param condition 查询条件  分页page,rows
     * @return
     */
    PageInfo<House> getHouseByBroswer(HouseCondition condition);
}
