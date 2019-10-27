package com.team.house.mapper;

import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.entity.Street;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    //删除区域下的街道
    int delStreetByDistrict(Integer id);

    //批量删除
    @Delete("delete from district where id in (${ids}) ")
    int delSomeDistrict(@Param("ids") String ids);

    //查询街道区域
    @Select("select * from street where DISTRICT_ID= #{DISTRICT_ID}")
    List<Street> getStreetByDistrict(@Param("DISTRICT_ID") Integer id);
}