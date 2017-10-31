/**
 * Created by a7289 on 2016/12/16 0016.
 */
$(document).ready(function() {
    $('#bg').perfectScrollbar();
    count     = 0;  //总共多少记录
    totalPage = 0;  //总共多少页
    pageSize  = 10; //每页多少记录
    current   = 1;	//当前第几页
    init_user();
    get_role();
    $('.setting_btn > span:nth-child(1)').bind('click',function(){
        var user_id   = $('#userid').text();
        var username  = $('#username').val();
        var loginname = $('#loginname').val();
        var password  = $('#password').val();
        var roleid	  = $('#roleid').val();
        var flag      = $('#flag').val();
        if(user_id == '' || user_id == null || user_id == 'null')
        {
            if(loginname == '' || password == '' || loginname.trim()=='' || password.trim() == ''){alert('登录名或密码不能为空!');return;}
            if(password.trim().length<5){alert('密码长度至少为5位数!');return;}
            var ok = true;
            $.each(userList, function(i,item){
                if (loginname == item.login_name||loginname.trim()==item.login_name){
                    ok = false;
                    alert('登录名已存在!');return false;
                }
            });
            if (!ok){return;}
            var data = { 'userName':username, 'loginName':loginname, 'password':password, 'flag':flag, 'roleId':roleid};
            $.ajax({
                contentType : 'application/json;charset=utf-8',
                type:"post",
                url:"../lte/add_user",
                data:JSON.stringify(data),
                dataType:'json',
                success:function(response){
                    //	console.log(response);
                    if(response.status)
                    {
                        $('#userid').text('');
                        $('#username').val('');
                        $('#loginname').val('');
                        $('#password').val('');
                        $('#roleid').val('');
                        $('.setting_bg').slideUp(500,function (){
                            $('#bg').removeClass('frosted-glass');
                        });


                        init_user();
                    }
                    else
                    {

                    }
                },
                error:function(e){
                    console.log(e.responseText);
                }
            });
        }
        else
        {
            if(loginname == '' || password == '' || loginname.trim()=='' || password.trim() == ''){alert('登录名或密码不能为空!');return;}
            if(password.trim().length<5){alert('密码长度至少为5位数!');return;}
            var ok = true;
            $.each(userList, function(i,item){
                if ((user_id!=item.user_id)&&(loginname == item.login_name||loginname.trim()==item.login_name)){
                    ok = false
                    alert('登录名已存在!');return false;
                }
            });
            if (!ok){return;}
            var data = {'userId':user_id, 'userName':username, 'loginName':loginname, 'password':password, 'flag':flag, 'roleId':roleid};
            $.ajax({
                contentType : 'application/json;charset=utf-8',
                type:"post",
                url:"../lte/mod_user",
                data:JSON.stringify(data),
                dataType:'json',
                success:function(response){
                    //	console.log(response);
                    if(response.status)
                    {
                        $('#userid').text('');
                        $('#username').val('');
                        $('#loginname').val('');
                        $('#password').val('');
                        $('#roleid').val('');
                        $('.setting_bg').slideUp(500,function (){
                            $('#bg').removeClass('frosted-glass');
                        })
                  
                        var loginID = getCookie('user_id');
                        var ITEID = getCookie('ITEID');
                        var old = JSON.parse(ITEID);
                        old.user_name = username;
                        setCookie('ITEID',JSON.stringify(old),1);
                        if (loginID == user_id){
                            if (username.length>5){
                                $(window.parent.document).find("#username").css("font-size","9px");
                                $(window.parent.document).find('#username').text(username);
                            }else {
                                $(window.parent.document).find("#username").css("font-size","11px");
                                $(window.parent.document).find('#username').text(username);
                            }
                        }
                        init_user();
                    }
                    else
                    {

                    }
                },
                error:function(e){
                    console.log(e.responseText);
                }
            });
        }
    });
    $('.setting_btn > span:nth-child(2)').bind('click',function(){
        $('#userid').text('');
        $('#username').val('');
        $('#loginname').val('');
        $('#password').val('');
        $('#roleid').val('');
        $('.setting_bg').slideUp(500,function (){
            $('#bg').removeClass('frosted-glass');
        })

    });
});

function init_user()
{
$("#user_list li").not(":first").remove();
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_user",
        dataType:'json',
        success:function(response){
            //	console.log(response);
            if(response.status)
            {
                var str = '';
                userList = response.data;
                $.each(response.data, function(i,item){
                    count = i + 1;
                    var start = pageSize * (current - 1) + 1;
                    var end   = pageSize * current;
                    if(i+1 >= start && i+1 <= end)
                    {
                        var flag = '可用';
                        if(item.flag == 2 || item.flag == '2'){flag = '不可用';}
                        if (item.role_name == ''){
                            item.role_name = "未选择（默认为全部权限）";
                        }
                        var str2 = '<li class="link_setting" data-user_id="'+item.user_id+'" data-user_name="'+item.user_name+'" data-login_name="'+item.login_name+'" data-flag="'+item.flag+'" data-role_id="'+item.role_id+'" data-password="'+item.password+'">'+
                            '<div  align="center" style="display: none!important;">'+item.user_id+'</div> ' +
                            '<div  align="center">'+item.user_name+'</div> ' +
                            '<div  align="center">'+item.login_name+'</div> ' +
                            '<div  align="center" style="width: 28% !important;">'+item.role_name+'</div> ' +
                            '<div  align="center">'+flag+'</div> ' +
                            '<div  align="center" style="display: none!important;">'+item.password+'</div> ' +
                            '<div  align="center">' +
                            '<span onclick="user_mod(this)" class="setting_mod">修改</span><span onclick="user_del(this)" class="setting_del">删除</span>' +
                            '</div>' +
                            '</li>'
                        $('#user_list').append(str2);
                    }
                });
                totalPage = Math.ceil(count/pageSize);//向上取整,有小数就整数部分加1
                page(totalPage, current);
                setCookie('user_list',JSON.stringify(response.data),1);
            }
            else
            {

            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
function page(pageCount, current)
{
    $('.page').remove();
    $('.list-content').append('<div class="page"></div>');
    $(".page").createPage({
        pageCount:pageCount,  //总页数
        current:current,      //当前页
        backFn:function(p){
            $("#user_list li").not(":first").remove();
            var str = '';
            $.each(JSON.parse(getCookie('user_list')),function(i,item){
                var start = pageSize * (p - 1) + 1;
                var end   = pageSize * p;
                var flag = (i+1 >= start && i+1 <= end);
                if(flag)
                {
                    var flag = '可用';
                    if(item.flag == 2 || item.flag == '2'){flag = '不可用';}
                    if (item.role_name == ''){
                        item.role_name = "未选择（默认为全部权限）";
                    }
                    var str2 = '<li class="link_setting" data-user_id="'+item.user_id+'" data-user_name="'+item.user_name+'" data-login_name="'+item.login_name+'" data-flag="'+item.flag+'" data-role_id="'+item.role_id+'" data-password="'+item.password+'">'+
                    '<div  align="center" style="display: none!important;">'+item.user_id+'</div> ' +
                    '<div  align="center">'+item.user_name+'</div> ' +
                    '<div  align="center">'+item.login_name+'</div> ' +
                    '<div  align="center" style="width: 28% !important;">'+item.role_name+'</div> ' +
                    '<div  align="center">'+flag+'</div> ' +
                    '<div  align="center" style="display: none!important;">'+item.password+'</div> ' +
                    '<div  align="center">' +
                    '<span onclick="user_mod(this)" class="setting_mod">修改</span><span onclick="user_del(this)" class="setting_del">删除</span>' +
                    '</div>' +
                    '</li>'
                    $('#user_list').append(str2);
                }
            });
        }
    });
}
function get_role()
{
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_role",
        dataType:'json',
        success:function(response){
            //	console.log(response);
            if(response.status)
            {
                var str = '';
                $.each(response.data, function(i,item){
                    str = str + '<option value="'+item.role_id+'">'+item.role_name+'</option>';
                });
                $('#roleid').append(str);
            }
            else
            {

            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
function user_add(obj)
{
    $('#userid').text('');
    $('#username').val('');
    $('#loginname').val('');
    $('#password').val('');
    $('#roleid').val('');
    $('#bg').addClass('frosted-glass');
    $('.setting_bg').slideDown(500);
}
function user_del(obj)
{
	var login_name = $(obj).parent().parent().data('login_name');
    var del = confirm("确定要删除"+login_name+"么？");
    if(del!=true){
      return false;
     
    }
    
    var user_id = $(obj).parent().parent().data('user_id');
    var  data = {'userId':user_id};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/del_user",
        data:JSON.stringify(data),
        dataType:'json',
        success:function(response){
            //	console.log(response);
            if(response.status)
            {
                init_user();
                var loginID = getCookie('user_id');
                if (loginID == user_id){
                    delCookie('ITEID');
                    delCookie('station');
                    delCookie('user_name');
                    delCookie('user_id');
                    window.parent.location.href = "../lte/login";
                }
//				        	$(obj).parent().parent().remove();
            }
            else
            {
            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
function user_mod(obj)
{
    var user_id    = $(obj).parent().parent().data('user_id');
    var user_name  = $(obj).parent().parent().data('user_name');
    var login_name = $(obj).parent().parent().data('login_name');
    var password   = $(obj).parent().parent().data('password');
    var role_id    = $(obj).parent().parent().data('role_id');
    var flag 	   = $(obj).parent().parent().data('flag');
    $('#userid').text(user_id);
    $('#username').val(user_name);
    $('#loginname').val(login_name);
    $('#password').val(password);
    $('#roleid').val(role_id);
    $('#flag').val(flag);
    $('#bg').addClass('frosted-glass');
    $('.setting_bg').slideDown(500);
}
function check_pass_len(obj) {
    var pass = $(obj).val();
    var warning = $('#warning').val();
    if (pass.length < 5){
        if (warning.length == '' ){
            $('#warning').text('密码长度至少为5位数');
        }
    }else {
        $('#warning').text('');
    }
}