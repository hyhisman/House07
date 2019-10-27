package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.util.HouseCondition;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    public List<House> getHouseByUserid(Integer uid) {
        return houseMapper.getHouseByUser(uid);
    }

    public House getHouseByid(String id) {
        return houseMapper.getHouseById(id);
    }

    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    public PageInfo<House> getHouseByPassState(Integer state,PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> list = houseMapper.getHouseByPassState(state);
        return new  PageInfo<House>(list);
    }

    public int updateHousePassState(String id, Integer state) {
        House house=new House();
        house.setId(id);
        house.setIspass(state);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    public PageInfo<House> getHouseByBroswer(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> list = houseMapper.getHouseByBroswer(condition);
        return new PageInfo<House>(list);
    }
}
