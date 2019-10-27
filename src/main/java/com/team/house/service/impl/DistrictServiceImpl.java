package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

    public PageInfo<District> getDisrictByPage(PageUtil pageInfo) {
        //1.开启分页
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getRows());
        //2.查询所有
        DistrictExample districtExample=new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        PageInfo<District> pageInfo1=new PageInfo<District>(list);
        return pageInfo1;
    }

    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int delDistrict(Integer id) {
        //1.通过区域道编号删除街   //编写dao层
        districtMapper.delStreetByDistrict(id);
        //2.删除区域
        districtMapper.deleteByPrimaryKey(id);
        return 1;
    }

    public int deleteSomeDistrict(String ids) {
        return districtMapper.delSomeDistrict(ids);

    }

    public PageInfo<Street> getStreet(PageUtil pageInfo,Integer id) {
        //1.开启分页
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getRows());
        List<Street> list = districtMapper.getStreetByDistrict(id);
        return new PageInfo<Street>(list);
    }


    public List<Street> getStreetByDistrictId(Integer disstrictId) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(disstrictId);
        List<Street> list = streetMapper.selectByExample(streetExample);
        return list;
    }

}
