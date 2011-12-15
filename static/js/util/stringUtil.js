/*
 * stringUtil.js
 *
 * by : xiang.xiaox
 * time: 2011-09-16
 */
var stringUtil = function() {
    return {
        //����Ƿ�����
        checkInt:function (obj, maxLength) {
            var value = obj.val();
            var pattern = new RegExp("^\\-?([1-9]\\d*|0)$", "g");
            if (value.match(pattern)) {
                //obj.attr('value',value);
            } else {
                obj.attr('value', stringUtil.replaceInt(value));
            }
        },
        //����Ƿ����� ,�滻��طǷ��ַ�
        replaceInt:function(value) {
            //�Ȱѷ����ֵĶ��滻�����������ֺ�.
            value = value.replace(/[^\d]/g, "");
            return value;
        },
        //����Ƿ�����,��λС�� param=2
        checkNum:function (obj, param, maxLength) {
            var value = obj.val();
            var pattern = new RegExp("^\\-?([1-9]\\d*|0)(\\.\\d{0," + param + "})?$", "g");
            if (value.match(pattern)) {
                //�������һλ��.
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
        //����Ƿ����� ,�滻��طǷ��ַ�
        replaceNum:function(value) {
            //�Ȱѷ����ֵĶ��滻�����������ֺ�.
            value = value.replace(/[^\d.]/g, "");
            //���뱣֤��һ��Ϊ���ֶ�����.
            value = value.replace(/^\./g, "");
            //���뱣֤��һ��Ϊ���ֶ�����.
            value = value.replace(/\.{2,}/g, "");
            //��֤.ֻ����һ�Σ������ܳ�����������
            value = value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
            //
            var valueArray = value.split('.');
            if (valueArray.length > 1 && valueArray[1].length > 2) {
                value = value.substring(0, valueArray[0].length + 3);
            }

            return value;
        },
        //����¼���
        checkObjectNum:function(obj, param, maxLength) {
            obj.css("ime-mode", "Disabled");//���뷨����
            obj.keyup(function() {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.keydown(function() {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.mouseover(function() {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.blur(function() {
                stringUtil.checkNum(obj, param, maxLength);
            });
            obj.bind('paste', function() {
                var value = clipboardData.getData('text');
                return stringUtil.replaceNum(value);
            });
            obj.bind("dragenter", function() {
                return false;
            });
        },
        //����¼���
        checkObjectInt:function(obj, maxLength) {
            obj.css("ime-mode", "Disabled");//���뷨����
            obj.keyup(function() {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.keydown(function() {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.mouseover(function() {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.blur(function() {
                stringUtil.checkInt(obj, maxLength);
            });
            obj.bind('paste', function() {
                var value = clipboardData.getData('text');
                return stringUtil.replaceInt(value);
            });
            obj.bind("dragenter", function() {
                return false;
            });
        },
        //���㶨����������  ����IE�������chrome�������onfocusʱ����궨λ�����
        focusLast:function(obj) {
            var esrc = obj;
            if (esrc == null) {
                esrc = event.srcElement;
            }
            if (obj.createTextRange) {//IE�����
                var range = obj.createTextRange();
                range.moveStart("character", esrc.value.length);
                range.collapse(true);
                range.select();
            } else {//��IE�����
                obj.setSelectionRange(esrc.value.length, esrc.value.length);
                obj.focus();
            }
        },
        checkData:function(obj) { //obj.id  name  handler
            var minValue = $("#min" + obj.id).val();
            var maxValue = $("#max" + obj.id).val();
            if (minValue.length != 0 && maxValue.length != 0) {
                if (Number(minValue) > Number(maxValue)) {
                    alert('����д��ȷ��' + obj.name);
                    $("#max" + obj.id).attr('value', '');
                    stringUtil.focusLast($("#max" + obj.id)[0]);
                    return false;
                }
            }
            if (maxValue.length != 0) {
                if (Number(maxValue) == 0) {
                    alert('���' + obj.name + '����Ϊ0');
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
        htmlEncode:function(str) {
            if ("string" !== typeof str) {
                return "";
            }
            return str.replace(/&/g, "&#38;")
                    .replace(/>/g, "&gt;")
                    .replace(/</g, "&lt;")
                    .replace(/\'/g, "&#8216;")
                    .replace(/\"/g, "&#8220;");
        },
        htmlDecode:function(str) {
            if ("string" !== typeof str) {
                return "";
            }
            return str.replace(/&#38;/g, "&")
                    .replace(/&gt;/g, ">")
                    .replace(/&lt;/g, "<")
                    .replace(/&#8216;/g, "'")
                    .replace(/&#8220;/g, "\"");
        },
        valiateDate:function(val, obj) {
            var r = val.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
            if (r == null) {
                alert('������Ϸ������ڼ����ڸ�ʽ���磺2008-08-08');
                $("#" + obj).val('');
                stringUtil.focusLast($("#" + obj)[0]);
                return false;
            }
            return true;
        },
        //�س��¼�ȡ��s
        cancleEvent:function(obj) {
            obj.keydown(function(e) {
                var curkey = e.which;
                if (curkey == 13) {
                    return false;
                }
            });
        },
        //json��̨ת���ַ���ԭ��ȡ�ַ�
        substr:function(val, cutLength) {
            var str = stringUtil.htmlDecode(val);
            str = (cutLength >= str.length ? str : (str.substring(0, cutLength) + '...'));
            return stringUtil.htmlEncode(str);
        }
    }
}();
