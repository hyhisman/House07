package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.util.UserCondition;

public interface UserService {

    //模糊查询,分页
    PageInfo<Users> getUserByCondition(UserCondition condition);

    /**
     * 注册用户  房东
     * @param users  用户信息
     * @return 影响行数
     */
    int addUser(Users users);

    //检查用户是否存在
    int checkUserName(String name);

    //登录验证
    Users  login(String name,String password);

    //管理员登录验证
    Users  isadmlogin(String name,String password);
}
