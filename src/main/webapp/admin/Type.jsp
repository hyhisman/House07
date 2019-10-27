<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/Type.js">
    </script>
</head>
<body>
<table id="dg"></table>
<!--工具栏-->
<div id="tb">
    <a href="javascript:add()" class="easyui-linkbutton"
       iconCls="icon-add" plain="true">添加</a>
    <a href="javascript:goupdate()" class="easyui-linkbutton"
       iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:deleteByPrimaryId()" class="easyui-linkbutton"
       iconCls="icon-remove" plain="true">删除</a>
</div>
<!--制作添加区域的对话框-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form action="" id="addForm" name="addForm" method="post">
        类型名称:<input type="text" id="txtName" name="name"><br/>
    </form>
</div>
<!--对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">添加</a> <a href="javascript:CloseDialog('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--制作修改框-->
<div id="UpdateDialog" class="easyui-dialog" buttons="#updateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form action="" id="updateForm" name="updateForm" method="post">
        类型名称:<input type="text" id="txtName2" name="name"><br/>
        <input type="hidden" name="id" id="name2">
    </form>
</div>
<!--修改框的按钮-->
<div id="updateDialogButtons">
    <a href="javascript:updateDistrict()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a> <a href="javascript:CloseDialog('UpdateDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
