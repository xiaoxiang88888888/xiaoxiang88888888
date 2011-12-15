
//打点
var TracelogClick = function() {
    var _attrName = 'tracelogClickkey';
    /**
     * 打点的事件，默认为鼠标点击事件
     */
    var _defaultEvent = 'mousedown';

    return {
        initTracelogclick : function(root, event) {
            var e = event ? event : _defaultEvent;
            var r = root ? root : document;
			if($('[' + _attrName + ']')!=null)
            $('[' + _attrName + ']').each(function() {
                TracelogClick._bind(this, e);
            });
        },

        /**
         * 针对单个参数的打点（绑定事件）
         */
        _bind : function(el, e, key) {
            var tracelogclickKey = key ? key : el.getAttribute(_attrName);
            /*if (tracelogclickKey != '') {
             tracelogclickKey = tracelogclickKey.toLowerCase();
             }*/
            $(el).bind(e, function() {
                if (typeof window.dmtrack != 'undefined') {
                    dmtrack.tracelog(tracelogclickKey);
                }
            });
        },
        clickSingleTrace : function(key) {
            if (typeof (key) != 'string') {
                return;
            }
            if (typeof window.dmtrack != 'undefined') {
                dmtrack.tracelog(key);
            }
        },
        clickObjectTrace : function(params) {
            if (typeof (params) != 'object') {
                return;
            }
            if (typeof window.dmtrack != 'undefined') {
                dmtrack.clickstat(dmtrack.tracelog_url, params);
            }
        }
    };
}();
TracelogClick.initTracelogclick();