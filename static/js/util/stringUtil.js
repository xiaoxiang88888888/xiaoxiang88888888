/*
 * stringUtil.js
 *
 * by : xiang.xiaox
 * time: 2011-09-16
 */
var stringUtil = function () {
    return {
        //检查是否整数
        checkInt:function (obj, maxLength) {
            var value = obj.val();
            var pattern = new RegExp("^\\-?([1-9]\\d*|0)$", "g");
            if (value.match(pattern)) {
                //obj.attr('value',value);
            } else {
                obj.attr('value', stringUtil.replaceInt(value));
            }
        },
        //检查是否整数 ,替换相关非法字符
        replaceInt:function (value) {
            //先把非数字的都替换掉，除了数字和.
            value = value.replace(/[^\d]/g, "");
            return value;
        },
        //检查是否数字,两位小数 param=2
        checkNum:function (obj, param, maxLength) {
            var value = obj.val();
            var pattern = new RegExp("^\\-?([1-9]\\d*|0)(\\.\\d{0," + param + "})?$", "g");
            if (value.match(pattern)) {
                //处理最后一位是.
                if (value != null
                    && value.length == maxLength
                    && value.substr(value.length - 1, value.length) == '.') {
                    obj.attr('value', value.substr(0, value.length - 1));
                }
                //obj.attr('value',value);
            } else {
                obj.attr('value', stringUtil.replaceNum(value));
            }
        },
        //检查是否数字 ,替换相关非法字符
        replaceNum:function (value) {
            //先把非数字的都替换掉，除了数字和.
            value = value.replace(/[^\d.]/g, "");
            //必须保证第一个为数字而不是.
            value = value.replace(/^\./g, "");
            //必须保证第一个为数字而不是.
            value = value.replace(/\.{2,}/g, "");
            //保证.只出现一次，而不能出现两次以上
            value = value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
            //
            var valueArray = value.split('.');
            if (valueArray.length > 1 && valueArray[1].length > 2) {
                value = value.substring(0, valueArray[0].length + 3);
            }

            return value;
        },
        //多个事件绑定
        checkObjectNum:function (obj, param, maxLength) {
            obj.css("ime-mode", "Disabled");//输入法屏蔽
            obj.keyup(function () {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.keydown(function () {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.mouseover(function () {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.blur(function () {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.bind('paste', function () {
                var value = clipboardData.getData('text');
                return stringUtil.replaceNum(value);
            });
            obj.bind("dragenter", function () {
                return false;
            });
        },
        //多个事件绑定
        checkObjectInt:function (obj, maxLength) {
            obj.css("ime-mode", "Disabled");//输入法屏蔽
            obj.keyup(function () {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.keydown(function () {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.mouseover(function () {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.blur(function () {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.bind('paste', function () {
                var value = clipboardData.getData('text');
                return stringUtil.replaceInt(value);
            });
            obj.bind("dragenter", function () {
                return false;
            });
        },
        //焦点定到最后的文字  兼容IE、火狐、chrome浏览器的onfocus时将光标定位在最后
        focusLast:function (obj) {
            var esrc = obj;
            if (esrc == null) {
                esrc = event.srcElement;
            }
            if (obj.createTextRange) {//IE浏览器
                var range = obj.createTextRange();
                range.moveStart("character", esrc.value.length);
                range.collapse(true);
                range.select();
            } else {//非IE浏览器
                obj.setSelectionRange(esrc.value.length, esrc.value.length);
                obj.focus();
            }
        },
        checkData:function (obj) { //obj.id  name  handler
            var minValue = $("#min" + obj.id).val();
            var maxValue = $("#max" + obj.id).val();
            if (minValue.length != 0 && maxValue.length != 0) {
                if (Number(minValue) > Number(maxValue)) {
                    alert('请填写正确的' + obj.name);
                    $("#max" + obj.id).attr('value', '');
                    stringUtil.focusLast($("#max" + obj.id)[0]);
                    return false;
                }
            }
            if (maxValue.length != 0) {
                if (Number(maxValue) == 0) {
                    alert('最大' + obj.name + '不能为0');
                    $("#max" + obj.id).attr('value', '');
                    stringUtil.focusLast($("#max" + obj.id)[0]);
                    return false;
                }
            }
            if (obj.handler) {
                return obj.handler();
            }
            return true;
        },
        htmlEncode:function (str) {
            if ("string" !== typeof str) {
                return "";
            }
            return str.replace(/&/g, "&#38;")
                .replace(/>/g, "&gt;")
                .replace(/</g, "&lt;")
                .replace(/\'/g, "&#8216;")
                .replace(/\"/g, "&#8220;");
        },
        htmlDecode:function (str) {
            if ("string" !== typeof str) {
                return "";
            }
            return str.replace(/&#38;/g, "&")
                .replace(/&gt;/g, ">")
                .replace(/&lt;/g, "<")
                .replace(/&#8216;/g, "'")
                .replace(/&#8220;/g, "\"");
        },
        valiateDate:function (val, obj) {
            var r = val.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
            if (r == null) {
                alert('请输入合法的日期及日期格式。如：2008-08-08');
                $("#" + obj).val('');
                stringUtil.focusLast($("#" + obj)[0]);
                return false;
            }
            return true;
        },
        //回车事件取消s
        cancleEvent:function (obj) {
            obj.keydown(function (e) {
                var curkey = e.which;
                if (curkey == 13) {
                    return false;
                }
            });
        },
        //json后台转义字符还原截取字符
        substr:function (val, cutLength) {
            var str = stringUtil.htmlDecode(val);
            str = (cutLength >= str.length ? str : (str.substring(0, cutLength) + '...'));
            return stringUtil.htmlEncode(str);
        },
        //转码中文成特殊的编码
        encode:function (strIn) {
            var intLen = strIn.length;
            var strOut = "";
            var strTemp;
            for (var i = 0; i < intLen; i++) {
                strTemp = strIn.charCodeAt(i);
                if (strTemp > 255) {
                    tmp = strTemp.toString(16);
                    for (var j = tmp.length; j < 4; j++) tmp = "0" + tmp;
                    strOut = strOut + "^" + tmp;
                } else {
                    if (strTemp < 48 || (strTemp > 57 && strTemp < 65) || (strTemp > 90 && strTemp < 97) || strTemp > 122) {
                        tmp = strTemp.toString(16);
                        for (var j = tmp.length; j < 2; j++) tmp = "0" + tmp;
                        strOut = strOut + "~" + tmp;
                    } else {
                        strOut = strOut + strIn.charAt(i);
                    }
                }
            }
            return (strOut);
        },
        //解码成正常的中文
        decode:function (strIn) {
            var intLen = strIn.length;
            var strOut = "";
            var strTemp;
            for (var i = 0; i < intLen; i++) {
                strTemp = strIn.charAt(i);
                switch (strTemp) {
                    case "~":
                    {
                        strTemp = strIn.substring(i + 1, i + 3);
                        strTemp = parseInt(strTemp, 16);
                        strTemp = String.fromCharCode(strTemp);
                        strOut = strOut + strTemp;
                        i += 2;
                        break;
                    }
                    case "^":
                    {
                        strTemp = strIn.substring(i + 1, i + 5);
                        strTemp = parseInt(strTemp, 16);
                        strTemp = String.fromCharCode(strTemp);
                        strOut = strOut + strTemp;
                        i += 4;
                        break;
                    }
                    default:
                    {
                        strOut = strOut + strTemp;
                        break;
                    }
                }
            }
            return (strOut);
        }
    }
}();
