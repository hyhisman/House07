package com.team.house.pcontroller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import com.team.house.util.HouseCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller(value = "houseController2")
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("addHouse")
    public String addHouse(House house, HttpSession session,@RequestParam(value ="pfile",required = false)MultipartFile pfile,
    Integer type_id,Integer street_id){
        try {
        //上传文件
        String sourceFile = pfile.getOriginalFilename();//文件名
        String exName = sourceFile.substring(sourceFile.lastIndexOf("."));//扩展名
        String bh=System.currentTimeMillis()+"";
        String filename=bh+exName;
        String path="d:\\images\\"+filename;
        File saveFile=new File(path);

        pfile.transferTo(saveFile);//上传
        //2.调用业务将数据保存到数据库
        //设置编号
        house.setId(bh);
        //设置图片
        house.setPath(filename);
        //设置用户编号
        Users user=(Users)session.getAttribute("userinfo");
        house.setUserId(user.getId());

        //设置其他属性
        house.setIsdel(0);
        house.setIspass(0);
        house.setTypeId(type_id);
        house.setStreetId(street_id);
        houseService.addHouse(house);//保存
            return "fabu";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    @RequestMapping("getUserHouse")
    public String getUserHouse(HttpSession session, Model model){
        //调用业务查询所有用户发布的出租房信息
        //设置用户编号
        Users user=(Users) session.getAttribute("userinfo");
        List<House> list=houseService.getHouseByUserid(user.getId());  //固定某值查询
        model.addAttribute("list",list);
        return "guanli";
    }
    @RequestMapping("showHouse")
    public String showHouse(String id,Model model){
        House house = houseService.getHouseByid(id);
        model.addAttribute("house",house);
        return "upfabu";
    }
    @RequestMapping("updateHouse")
    public String updateHouse(House house,String oldPic,@RequestParam(value ="pfile",required = false)MultipartFile pfile,
    Integer type_id,Integer street_id){

        try {
            if (!pfile.isEmpty()){
            //上传文件
            String sourceFile = pfile.getOriginalFilename();//文件名
            String exName = sourceFile.substring(sourceFile.lastIndexOf("."));//扩展名
            String bh=System.currentTimeMillis()+"";
            String filename=bh+exName;
            String path="d:\\images\\"+filename;
            File saveFile=new File(path);

            pfile.transferTo(saveFile);//上传
            house.setPath(filename);
         }
          house.setTypeId(type_id);
          house.setStreetId(street_id);
          houseService.updateHouse(house);//保存

            //删除旧图
            if (!pfile.isEmpty()){
                //删除旧的图片
                File file=new File("d:\\images\\"+oldPic);
                file.delete();
            }
            return "redirect:getUserHouse";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    @RequestMapping("delHouse")
    @ResponseBody
    public String delHouse(House house,String id){
        house.setIsdel(1);
        house.setId(id);
        int temp = houseService.updateHouse(house);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("searchHouse")
    public String searchHouse(HouseCondition condition, Model model){
        condition.setRows(3);
        PageInfo<House> houses = houseService.getHouseByBroswer(condition);
        model.addAttribute("houses",houses);
        model.addAttribute("conditioin",condition);
        return "list";
    }
}
