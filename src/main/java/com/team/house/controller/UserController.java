package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.UserService;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUserByPage")
    @ResponseBody
    public Map<String,Object> getUserByPage(UserCondition condition){
        PageInfo<Users> pageInfo = userService.getUserByCondition(condition);
        //使用map封装返回的数据
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

}
