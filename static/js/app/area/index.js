//地区主页面
var areaIndex = function($) {
    //全局
    var time = 5000;//5秒钟后消失 
    return {
        //初始化相关数据
        init:function() {

        },
        loadData:function(callback){
            $.ajax({
                'type':'get',
                'cache':false,
                'url':(webServer + '/jcrmgroup!groups.jspa'),
                success:function(data){
                    if(data && data.groupList){
                        window.__customer_group = data.groupList;
                    }else{
                        window.location.href="/exception/exception.vm";
                    }
                    //回调
                    if(callbacks) {
                        for(var cb in callbacks) {
                            callbacks[cb](data);
                        }
                    }
                }
            });
        }
	};
}(jQuery);
//调用初始
areaIndex.init();



