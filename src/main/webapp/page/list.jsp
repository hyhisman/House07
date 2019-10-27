<%@ page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript">
      $(function(){  //jquery加载事件
          //1.发送异步请求加载类型数据
          $.post("getAllType",null,function(data){
              //循环一次一行对就一个选项
              for (var i=0;i<data.length;i++){
                  //创建dom节点
                /*  var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");*/
                  var option=$("<a href='searchHouse?id='"+data[i].id+">"+data[i].name+"</a>&nbsp;&nbsp;&nbsp;");
                  //添加节点
                  $("#type_id").append(option);
              }

              //设置下拉列表的选中项
              $("#type_id").val(${conditioin.tid});
          },"json");

          //2.异步加载所有区域
          $.post("getAllDistrict",null,function(data){
              //循环一次一行对就一个选项
              for (var i=0;i<data.length;i++){
                  //创建dom节点
                  var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                  //添加节点
                  $("#district_id").append(option);
              }

              //设置区域的选中项
              $("#district_id").val(${conditioin.did});
              //加载街道
              changeStreet();

          },"json");

          //3.加载街道
          $("#district_id").change(function(){  //触发区域的改变事件
              changeStreet();
          });

      });

      function changeStreet(){
          var did=$("#district_id").val();
          //发送异步请求获取街道
          //清空选项
          $("#street_id>option:gt(0)").remove();
          //加载数据
          $.post("getStreetByDid",{"did":did},function(data){
              //循环一次一行对就一个选项
              for (var i=0;i<data.length;i++){
                  //创建dom节点
                  var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                  //添加节点
                  $("#street_id").append(option);
              }
              //设置区域的选中项
              $("#street_id").val(${conditioin.sid});
          },"json");
      }

      //分页
      function goPage(num){  //num页码
          $("#setPage").val(num);
          $("#sform").submit();  //表单提交
      }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action=searchHouse>
    <input type="hidden" value="1"  id="setPage" name="page">
    标题:<input type="text" value="${conditioin.title}" name="title">
    区域:<select name="did" id="district_id">
    <option value="">请选择</option>
  </select>

    街道:<select name="sid" id="street_id">
    <option value="">请选择</option>
  </select>
    类型:<%--<select name="tid" id="type_id">
    <option value="">请选择</option>
  </select>--%>
      <div id="type_id" name="tid"></div>
    价格:<input type="text" name="startPrice" value="${conditioin.startPrice}">-<input type="text" value="${conditioin.endPrice}" name="endPrice">
    <input type="submit" name="search" value="搜 索">
  </FORM></DL></DIV>
<DIV class="main wrap">
  <TABLE class=house-list>
    <TBODY>
    <c:forEach items="${houses.list}" var="h">
      <TR>
        <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="../images/thumb_house.gif" width="100" height="75" alt=""></a></span></TD>
        <TD>
          <DL>
            <DT><A href="details.htm" target="_blank">${h.title}</A></DT>
            <DD>${h.dname},${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
        <TD class=house-type>${h.tname}</TD>
        <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
      </TR>
    </c:forEach>
    <TR>无租房信息</TR></TBODY></TABLE>
  <DIV class=pager>
    <UL>
      <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
      <LI><A href="javascript:goPage(${houses.prePage==0?1:houses.prePage})">上一页</A></LI>
      <LI><A href="javascript:goPage(${houses.nextPage==0?houses.pages:houses.nextPage})">下一页</A></LI>
      <LI><A href="javascript:goPage(${houses.pages})">末页</A></LI></UL><SPAN
          class=total>${houses.pageNum}/${houses.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
