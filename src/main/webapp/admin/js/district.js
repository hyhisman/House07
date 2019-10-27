//加载显示区域
$(function(){  //加载事件
    //显示数据
    $('#dg').datagrid({
        title:"区域信息",
        url:'getDistrictByPage',
        pagination:true,
        pageSize:5,
        pageList:[5,10,15,20],
        toolbar:"#tb",
        columns:[[
            {field:'opt',checkbox:"true",title:'编号',width:100},   //复选框列
            {field:'id',title:'编号',width:100},
            {field:'name',title:'区域名称',width:100},
            {field:'dd',title:'操作',width:200,
                formatter: function(value,row,index){
                    return "<a href='javascript:delDistrict("+row.id+")'>删除</a>|<a href=''>修改</a>"+
                        "|<a href='javascript:getStreetByid("+row.id+")'>街道详情</a> ";
                }
            }
        ]]
    });
});
function add() {
    $("#AddDialog").dialog("setTitle","添加区域");
    $("#AddDialog").dialog("open");//打开
}
function CloseDialog(id) {
    $("#"+id).dialog("close");//关闭
}
//保存区域
//使用easyui表单插件提交
function SaveDialog() {
    //1.使用jquery的post方法发送异步请求实现添加
    /*$.post("addDistrit",{"name":$("#txtName").val()},function(data){
        if(data.result==1){
            $("#dg").datagrid('reload'); //刷新
            $("#AddDialog").dialog("close");//关闭窗口
        }else{
            alert("添加失败!!!");
        }
    },"json");*/

    //2.使用easyui的表单提示插件
    $('#addForm').form('submit',{//提交按钮
        url:"addDistrict",
        success:function (data) {//获得Json字符
            data=$.parseJSON(data);
            if(data.result==1){
                $("#dg").datagrid('reload'); //刷新
                $("#AddDialog").dialog("close");//关闭窗口
            }else{
                alert("添加失败!!!");
            }
        }
    })
}
function goupdate() {
    //判断用户是否选择行
    //获取dagagrid选中的行
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length==1){
        //获取当前行的编号--》查询当前记录-->还原表单
        //1.获取当前的编号
        var bh=SelectRows[0].id;
        //2.发送异步请求获取服务器数据
        $.post("getDistrict",{"id":bh},function(data){
            //3.还原加载表单数据  //data的格式:{"id":1002,"name":"西城东区"}
            $("#updateForm").form('load',data);
        },"json");
        //获得行对象的数据加载到表单中显示
        //$("#upForm").form('load',{"名秒":值,"名称":值});

        $("#UpdateDialog").dialog("setTitle","修改区域");
        $("#UpdateDialog").dialog("open"); //打开  close 关闭
    }else{
        //消息框 alert
        $.messager.alert('提示信息','你没有选中行或者选中多行','warning');
    }
}
function updateDistrict() {
    $('#updateForm').form('submit',{//提交按钮
        url:"upDistrict",
        success:function (data) {//获得Json字符
            data=$.parseJSON(data);
            if(data.result==1){
                $("#dg").datagrid('reload'); //刷新
                $("#UpdateDialog").dialog("close");//关闭窗口
            }else{
                $.messager.alert('提示信息','修改失败','error');
            }
        }
    })
}
function delDistrict(id) {
    //确认提示框
    $.messager.confirm('删除区域', '真的想删除吗?', function(r){
        if (r){
            //删除
            $.post("delDistrict",{"id":id},function (data) {
                if(data.result==1){
                    $("#dg").datagrid('reload'); //刷新
                }else{
                    $.messager.alert('提示信息','删除失败','error');
                }
            },"json");
        }
    });
}
function deleteByPrimaryId() {
    //获取选择的行对象
    var selectRows = $("#dg").datagrid("getSelections");
    if (0 == selectRows.length) {
        $.messager.alert('系统提示','请选择一行数据进行删除！');
        return;
    }
    //定义数组，存储id进行删除，格式为[1,2,3]
    var selectIndex= [];
    for (var i = 0; i <selectRows.length ; i++) {
        selectIndex.push(selectRows[i].id);
    }
    //按照,进行拼接，返回字符串
    var selectIndexToString = selectIndex.join(",");
    $.messager.confirm('系统提示','你确认删除'+selectRows.length+'条数据吗',function (flag) {
        if (flag){
            $.post(
                "delById",
                {"ids":selectIndexToString},
                function (data) {
                    if (data.result == 0){
                        $.messager.alert("系统提示","删除失败！","error");
                    } else {
                        $.messager.alert("系统提示","您已成功删除"+data.result+"条数据！");
                        //刷新数据
                        $("#dg").datagrid('reload');
                    }
                },"json"
            )
        }
    });
}
function getStreetByid(id) {
    $("#streetDialog").dialog("setTitle","街道区域"),
    $("#streetDialog").dialog("open"),
    $('#dg2').datagrid({
        title:"街道信息",
        url:'getStreet',
        queryParams:{
            id:id
        },
        pagination:true,
        pageSize:3,
        pageList:[3,5,10,15],
        columns:[[
            {field:'opt',checkbox:"true",title:'编号',width:100},   //复选框列
            {field:'id',title:'编号',width:100},
            {field:'name',title:'街道名称',width:100},
        ]]
    });
}