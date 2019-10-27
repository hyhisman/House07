package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Type;
import com.team.house.service.DistrictService;
import com.team.house.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("getAllType")
    @ResponseBody
    public List<Type> getAllDistrict(){
           return this.typeService.getAllType();

    }
    //由于前端使用的datagrid 所以他自动传参 page 页码,rows页大小
    @RequestMapping("getTypeByPage")
    @ResponseBody
    public Map<String,Object> getTypeByPage(PageUtil pageUtil){
        //调用业务
        PageInfo<Type> pageInfo = typeService.getTypeByPage(pageUtil);
        //使用map封装返回的数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    @RequestMapping("addType")
    @ResponseBody
    public Map<String,Object> addType(Type type){
        int flag = typeService.addType(type);
        Map<String,Object> map=new HashMap<String, Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
    @RequestMapping("getType")
    @ResponseBody
    public Type getType(Integer id){
        Type type = typeService.getType(id);
        return type;
    }
    @RequestMapping("upType")
    @ResponseBody
    public Map<String,Object> upDistrit(Type type){
        int flag = typeService.updateType(type);
        Map<String,Object> map=new HashMap<String, Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
    @RequestMapping("delType")
    @ResponseBody
    public Map<String,Object> delType(Integer id){
        int flag = typeService.delType(id);
        Map<String,Object> map=new HashMap<String, Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
    @RequestMapping("delById2")
    @ResponseBody
    public Map<String,Object> delById(String ids){
        int flag = typeService.deleteSomeType(ids);
        Map<String,Object> map=new HashMap<String,Object>();
        // return "{\"result\":"+flag+"}";  //手工拼的json
        map.put("result",flag); //自动转化成Json
        return map;
    }
}
