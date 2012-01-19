//地区主页面
var areaIndex = function($) {
    var jsonData;
    //全局
    var time = 5000;//5秒钟后消失 
    return {
        //初始化相关数据
        init:function() {
           areaIndex.loadData();
           areaIndex.bind();
        },
        //载入数据
        loadData:function(callbacks){
            $.ajax({
                'type':'get',
                'cache':false,
                'url':(webServer + '/area/list.json'),
                success:function(data){
                    if(data && data.result){
                        areaIndex.renderData(data.data);
                    }else{
                        alert("出现错误:"+data.errorDesc);
                        return;
                    }
                    //回调
                    if(callbacks) {
                        for(var cb in callbacks) {
                            callbacks[cb](data);
                        }
                    }
                }
            });
        },
        //页面渲染
        renderData:function(data,areaname){
            var html = '<ui>';
            var list = data;
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                html += '<li>' + item.areaname + '<button name="updateArea" data="'+item.areaId+'">修改</button><button name="deleteArea" data="'+item.areaId+'">删除</button></li>';
            }
            html += '</ui>';
           $('#data').html(html);
        },
        //绑定相关事件
        bind:function(){
            //新增地区
            $('#add').click(function() {
                window.location.href='/area/add.htm';
            });
            //修改
            $('#data').delegate('button[name="updateArea"]', 'click', function() {
                areaIndex.updateArea($(this).attr('data'));
                return false;
            });
             //删除
            $('#data').delegate('button[name="deleteArea"]', 'click', function() {
                areaIndex.deleteArea($(this).attr('data'));
                return false;
            });
        },
        updateArea:function (id) {
            //刷新数据
            window.location.href='/area/update.htm?areaId='+id;
        },
        deleteArea:function (id) {
            var data = {
                'areaId':id
            };
            if (confirm('您确定要删除该地区吗？')) {
                $.ajax({
                    type:'delete',
                    data:data,
                    url:'/area/'+id+".json",
                    cache:false,
                    success:function (message) {
                        if (message&&message.result) {
                            alert("删除成功");
                        } else {
                            alert("删除失败");
                        }
                        //刷新数据
                        window.location.href='/area/index.htm';
                    }
                });
            }
        }
    };
}(jQuery);

//加载
$('document').ready(function() {
    //调用初始
    areaIndex.init();
});





