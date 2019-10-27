package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.DistrictService;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }

    public PageInfo<Type> getTypeByPage(PageUtil pageInfo) {
        //1.开启分页
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getRows());
        //2.查询所有
        TypeExample typeExample=new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        PageInfo<Type> pageInfo1=new PageInfo<Type>(list);
        return pageInfo1;
    }

    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int delType(Integer id) {
        //2.删除区域
        typeMapper.deleteByPrimaryKey(id);
        return 1;
    }

    public int deleteSomeType(String ids) {
        return typeMapper.delSomeType(ids);
    }
}
