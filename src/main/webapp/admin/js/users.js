//加载显示区域
$(function(){  //加载事件
    //显示数据
    $('#dg').datagrid({
        title:"用户信息",
        url:'getUserByPage',
        pagination:true,
        pageSize:5,
        pageList:[5,10,15,20],
        toolbar:"#tb",
        columns:[[
            {field:'opt',checkbox:"true",title:'编号',width:100},   //复选框列
            {field:'id',title:'编号',width:100},
            {field:'name',title:'用户名称',width:100},
            {field:'telephone',title:'电话',width:100},
            {field:'age',title:'年龄',width:100},
            {field:'dd',title:'操作',width:100,
                formatter: function(value,row,index){
                    return "<a href='javascript:delDistrict("+row.id+")'>删除</a>";
                }
            }
        ]]
    });
});

function search() {
    //$("#dg").datagrid("load",查询条件格式:{"名称":值,"名称":值..});
    var name=$("#sname").val();
    var telephone=$("#stelephone").val();
    $("#dg").datagrid("reload",{"name":name,"telephone":telephone});
}

