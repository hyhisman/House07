package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("getHouseByPass")
    @ResponseBody
    public Map<String,Object> getHouseByPass(PageUtil page,Integer state){
        PageInfo<House> house = houseService.getHouseByPassState(state, page);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",house.getList());
        map.put("total",house.getTotal());
        return map;
    }

    @RequestMapping("upHouseByPass")
    @ResponseBody
    public String  upHouseByPass(String id,Integer state){
        int temp = houseService.updateHousePassState(id, state);
        return "{\"result\":"+temp+"}";
    }
}
