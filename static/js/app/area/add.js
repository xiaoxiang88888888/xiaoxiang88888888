//地区主页面
var areaAdd = function ($) {
    //全局
    var time = 5000;//5秒钟后消失 
    return {
        //初始化相关数据
        init:function () {
            areaAdd.bind();
        },
        //绑定相关事件
        bind:function () {
            //新增地区
            $('#save').click(function () {
                areaAdd.addOrUpdateArea();
            });
        },
        addOrUpdateArea:function () {
            var addFlag = false;//是新增,还是修改
            var areaId = $.trim($('#areaId').val());
            if (areaId == '') {
                addFlag = true;
                areaId = new UUID().createUUID();
            }
            var areaname = $.trim($('#areaname').val());
            var parentAreaId = $.trim($('#parentAreaId').val());
            var areacode = $.trim($('#areacode').val());
            var orderno = $.trim($('#orderno').val());
            var remark = $.trim($('#remark').val());

            var data = {
                'areaId':areaId,
                'areaname':encodeURIComponent(areaname),
                'parentAreaId':encodeURIComponent(parentAreaId),
                'areacode':encodeURIComponent(areacode),
                'orderno':$.trim(orderno),
                'remark':stringUtil.encode(remark)
            };
            $.ajax({
                //dataType:'json',
                type:'post',
                data:data,
                url:'/area/' + areaId + '.json',
                cache:false,
                success:function (message) {
                    if (message.result) {
                        if (addFlag) {
                            alert("添加成功");
                        } else {
                            alert("修改成功");
                        }

                    } else {
                        if (addFlag) {
                            alert("添加失败");
                        } else {
                            alert("修改失败");
                        }
                    }
                    window.location.href = '/area/index.htm';
                }

            });
        }
    };
}(jQuery);

//加载
$('document').ready(function () {
    //调用初始
    areaAdd.init();
});





