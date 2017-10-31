$(function() {
    $('#sel').selectpicker('selectAll');
    $('#right').perfectScrollbar();
    $('#left_tac').perfectScrollbar();
    $('#left_node').perfectScrollbar();



    // $(".link_left").click(function(){
    //     var id = $(this).attr("id");
    //     setCookie('tac',id,1);
    //     window.location.href = "../lte/level2";
    // });

    $("#setting").click(function(){
        var id = $(this).attr("id");
        window.location.href="../lte/setting";
    });

    $("#go").on('click',(function(){
        var $optgroups = $('select').find('optgroup');
        $optgroups.each(function() {
            selected = $optgroups.find('option:selected').length;
            if (selected < $optgroups.data('minOptions')) {
                alert('你至少需要选择' + $optgroups.data('minOptions') + '个范围.');
            }else {
                var x = $("#sel").val();
                alert(x);
            }
        });

    }));

    $("#back").on('click',(function(){
        back();
    }));

    $("#logout").on('click',(function(){
       logout();
    }));

    // var lastSel = new Array();
    // $('select').on('changed.bs.select', function (e) {
    //     var $optgroups = $(this).find('optgroup');
    //     var newSel=$("#sel").val();
    //     $optgroups.each(function() {
    //         $this = $(this);
    //         selected = $this.find('option:selected').length;
    //         if (selected < $this.data('minOptions')) {
    //             var x = lastSel[0];
    //             $('.selectpicker').selectpicker('val', x);
    //             alert('你至少需要选择' + $this.data('minOptions') + '个范围.');
    //         }else {
    //             lastSel = newSel;
    //         }
    //     });
    // });
});

function setCookie(name,value,expiresDays)
{
    var exp = new Date();
    exp.setTime(exp.getTime() + expiresDays * 24 * 60 * 60 * 1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
}

function getCookie(name)
{
    var arr,reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if(arr = document.cookie.match(reg)) return unescape(arr[2]);
    else return null;
}

function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if(cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString()+";path=/";
}

function back(obj){
    if (window.location.href == 'http://'+window.location.host+'/SRAN/lte/setting'){
        window.location.href = 'http://'+window.location.host+'/SRAN/lte/level1';
    }else if(window.location.href == 'http://'+window.location.host+'/SRAN/lte/level3'){
        window.location.href = 'http://'+window.location.host+'/SRAN/lte/level2'
    }else if(window.location.href == 'http://'+window.location.host+'/SRAN/lte/level2'){
        window.location.href = 'http://'+window.location.host+'/SRAN/lte/level1'
    }else if(window.location.href == 'http://'+window.location.host+'/SRAN/lte/level1'){
        alert("你已到达一级页面！");
    }else{
        history.go(-1);
    }
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = decodeURI(window.location.search).substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

function getStrParam(str, name)
{
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r   = str.match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

function setting()
{
    window.location.href = "../lte/setting";
}

function logout() {
    delCookie('ITEID');
    delCookie('station');
    delCookie('user_name');
    delCookie('user_id');
    window.location.href = "../lte/login";
}

function check_login()
{
    var ITEID = getCookie('ITEID');
    // var user_name = getCookie('user_name');
    // var user_id = getCookie('user_id');
    // if((user_name == 'null' || user_name == '' || user_name == null || user_name == undefined) || (user_id == 'null' || user_id == '' || user_id == null || user_id == undefined))
    // {
    // 	window.location.href = "/LTETEST/login";
    // }
    if(ITEID == 'null' || ITEID == '' || ITEID == null || ITEID == undefined)
    {
        window.location.href = "../lte/login";
    }
    else
    {
        try{
            // var s = Base64.decode(ITEID);
            var o = JSON.parse(ITEID);
            if(o.user_id == undefined || o.user_name == undefined)
            {
                delCookie('ITEID');
                delCookie('station');
                delCookie('user_name');
                delCookie('user_id');
                window.location.href = "../lte/login";
            }
            else
            {
                setCookie('user_name',o.user_name,1);
                setCookie('user_id',o.user_id,1);
            }
        }catch(e){
            delCookie('ITEID');
            delCookie('station');
            delCookie('user_name');
            delCookie('user_id');
            window.location.href = "../lte/login";
        }
    }
}

String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};

// function trim(str){ //删除左右两端的空格
// 	return str.replace(/(^\s*)|(\s*$)/g, "");
// }

function fileScanDestroy()
{
    $.ajax({
        // type:"POST",
        url:"../lte/FileScanDestroy",
        async:true,
        error:function(e){
            console.log(e.responseText);
        },
        success:function(response){

        }
    })
}

function fileScanSuspend()
{
    $.ajax({
        // type:"POST",
        url:"../lte/FileScanSuspend",
        async:true,
        error:function(e){
            console.log(e.responseText);
        },
        success:function(response){

        }
    })
}

function fileScanInit()
{
    $.ajax({
        // type:"POST",
        url:"../SRAN/lte/FileScanInit",
        async:true,
        error:function(e){
            console.log(e.responseText);
        },
        success:function(response){

        }
    })
}

/*显示用户名称*/
function show_username()
{
    var user_name = getCookie('user_name');
    if (user_name.length>5){
        $("#username").css("font-size","9px");
        $('#username').text(user_name);
    }else {
        $("#username").css("font-size","11px");
        $('#username').text(user_name);
    }
}

/*获得更新时间*/
function get_update_time()
{
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_update_time",
        dataType:'json',
        async:false,
        success:function(response){
            //	console.log(response.data);
            update_time = response.data.time;
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}

function get_max(val_1, val_2)
{
    if(parseInt(val_1) > parseInt(val_2))
    {
        return val_1;
    }
    else
    {
        return val_2;
    }
}
function xtile(lon,zoom)
{
    return Math.floor((lon/180+1)*Math.pow(2, zoom-1));
}
function ytile(lat,zoom)
{
    return (Math.floor((1-Math.log(Math.tan(lat*Math.PI/180) + 1/Math.cos(lat*Math.PI/180))/Math.PI)/2 *Math.pow(2,zoom)));
}

/**
 *
 * 描述：js实现的map方法
 * @returns {Map}
 */
function Map(){
    var struct = function(key, value) {
        this.key = key;
        this.value = value;
    };
// 添加map键值对
    var put = function(key, value){
        for (var i = 0; i < this.arr.length; i++) {
            if ( this.arr[i].key === key ) {
                this.arr[i].value = value;
                return;
            }
        };
        this.arr[this.arr.length] = new struct(key, value);
    };
// 根据key获取value
    var get = function(key) {
        for (var i = 0; i < this.arr.length; i++) {
            if ( this.arr[i].key === key ) {
                return this.arr[i].value;
            }
        }
        return null;
    };
// 根据key删除
    var remove = function(key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if ( v.key === key ) {
                continue;
            }
            this.arr.unshift(v);
        }
    };
// 获取map键值对个数
    var size = function() {
        return this.arr.length;
    };
// 判断map是否为空
    var isEmpty = function() {
        return this.arr.length <= 0;
    };
    this.arr = new Array();
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}

/*加载js文件*/
function loadJs() {
    var head = $("head").remove("script[role='reload']");
    $("<scri" + "pt>" + "</scr" + "ipt>").attr({ role: 'reload', src: '../plugin/accordion/js/accordion.js', type: 'text/javascript' }).appendTo(head);
}

/*加载动画*/
function loading(type)
{
    if (type == 'start') {
        //	console.log("loading start");
        if (!$('.loading').is('div')) {
            var tempStr = '<div class="loading"> <div class="load ball-triangle-path"> <div></div> <div></div> <div></div> </div> </div>';
            $('body').append(tempStr);
        } else {
            $('.loading').show();
        }

    } else {
        //	console.log("loading stop");
        $('.loading').remove();
    }
}