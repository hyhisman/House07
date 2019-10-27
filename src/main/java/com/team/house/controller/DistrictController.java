package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Street;
import com.team.house.service.DistrictService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("getAllDistrict")
    @ResponseBody
    public List<District> getAllDistrict(){
           return districtService.getAllDistrict();

    }
    //由于前端使用的datagrid 所以他自动传参 page 页码,rows页大小
    @RequestMapping("getDistrictByPage")
    @ResponseBody
    public Map<String,Object> getDistrictByPage(PageUtil pageUtil){
        //调用业务
        PageInfo<District> pageInfo = districtService.getDisrictByPage(pageUtil);
        //使用map封装返回的数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    @RequestMapping("addDistrict")
    @ResponseBody
    public Map<String,Object> addDistrit(District district){
        int flag = districtService.addDistrict(district);
        Map<String,Object> map=new HashMap<String, Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
    @RequestMapping("getDistrict")
    @ResponseBody
    public District getDistrit(Integer id){
        District district = districtService.getDistrict(id);
        return district;
    }
    @RequestMapping("upDistrict")
    @ResponseBody
    public Map<String,Object> upDistrit(District district){
        int flag = districtService.updateDistrict(district);
        Map<String,Object> map=new HashMap<String, Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
    @RequestMapping("delDistrict")
    @ResponseBody
    public Map<String,Object> delDistrit(Integer id){
        int flag = districtService.delDistrict(id);
        Map<String,Object> map=new HashMap<String, Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
    @RequestMapping("delById")
    @ResponseBody
    public Map<String,Object> delById(String ids){
        int flag = districtService.deleteSomeDistrict(ids);
        Map<String,Object> map=new HashMap<String,Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
    @RequestMapping("getStreet")
    @ResponseBody
    public Map<String,Object> getStreet(PageUtil pageUtil,Integer id){
        PageInfo<Street> pageInfo = districtService.getStreet(pageUtil, id);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
}
