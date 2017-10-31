/**
 * Created by a7289 on 2016/12/16 0016.
 */
var roleList = '';
$(document).ready(function() {
    count     = 0;  //总共多少记录
    totalPage = 0;  //总共多少页
    pageSize  = 10; //每页多少记录
    current   = 1;	//当前第几页
    init_role();
    get_dic_tac();
    $('.select_all').bind('click', function(){
        $(".tac").each(function(){
            if(!$(this).is(':disabled')){
                $(this).prop('checked',true);
            }
        })
    });
    $('.un_select_all').bind('click', function(){
        $('.tac').prop('checked', false);
    });
    $('.setting_btn > span:nth-child(1)').bind('click', function(){
        var role_id   = $('#role_id').text();
        var role_name = $('#role_name').val();
        var role_tac  = '';
        $('.tac').each(function(){
            if($(this).is(':checked'))
            {
                if(role_tac == '')
                {
                    role_tac = $(this).data('tac');
                }
                else
                {
                    role_tac = role_tac + ',' + $(this).data('tac');
                }
            }
        });
        if(role_id == '' || role_id == 'null' || role_id == null)
        {
            if( role_name == '' || role_name.trim()==''){alert('权限名称不能为空!');return;}
            var ok = true;
            $.each(roleList, function(i,item){
                if (role_name == item.role_name || role_name.trim()==item.role_name){
                    ok = false;
                    alert('权限名称已存在!');return false;
                }
            });if (!ok){return;}
            var data = { 'roleName':role_name, 'roleTac':role_tac};
            $.ajax({
                contentType : 'application/json;charset=utf-8',
                type:"post",
                url:"../lte/add_role",
                data:JSON.stringify(data),
                dataType:'json',
                success:function(response){
                    //		console.log(response);
                    if(response.status)
                    {
                        init_role();
                        $('#role_id').text('');
                        $('#role_name').val('');
                        $('.tac').attr('checked', false);
                        $('.setting_bg').slideUp(500,function (){
                            $('#bg').removeClass('frosted-glass');
                        })

                    }
                },
                error:function(e){
                    console.log(e.responseText);
                }
            });
        }
        else
        {
            if( role_name == '' || role_name.trim()==''){alert('权限名称不能为空!');return;}
            var ok = true;
            $.each(roleList, function(i,item){
                if ((role_id!=item.role_id)&&(role_name == item.role_name || role_name.trim()==item.role_name)){
                    ok = false;
                    alert('权限名称已存在!');return false;
                }
            });
            if (!ok){return;}
            var data = {'roleId':role_id, 'roleName':role_name, 'roleTac':role_tac};
            $.ajax({
                contentType : 'application/json;charset=utf-8',
                type:"post",
                url:"../lte/mod_role",
                data:JSON.stringify(data),
                dataType:'json',
                success:function(response){
                    //		console.log(response);
                    if(response.status)
                    {
                        init_role();
                        $('#role_id').text('');
                        $('#role_name').val('');
                        $('.tac').attr('checked', false);
                        $('.setting_bg').slideUp(500,function (){
                            $('#bg').removeClass('frosted-glass');
                        })
                    }
                },
                error:function(e){
                    console.log(e.responseText);
                }
            });
        }
    });
    $('.setting_btn > span:nth-child(2)').bind('click', function(){
        $('#role_id').text('');
        $('#role_name').val('');
        $('.tac').attr('checked', false);
        $('.setting_bg').slideUp(500,function (){
            $('#bg').removeClass('frosted-glass');
        })
    });
});
function init_role()
{
    $("#role_list li").not(":first").remove();
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
                roleList = response.data;
                $.each(response.data, function(i,item){
                    count = i + 1;
                    var start = pageSize * (current - 1) + 1;
                    var end   = pageSize * current;
                    if(i+1 >= start && i+1 <= end)
                    {
                        if(item.role_tac.length < 50)
                        {
                            var role_tac = item.role_tac;
                        }
                        else
                        {
                            var role_tac = item.role_tac.substring(0, 50) + '...';
                        }
                        var str2 = '<li class="link_setting" data-role_id="'+item.role_id+'" data-role_name="'+item.role_name+'" data-role_tac="'+item.role_tac+'" >'+
                            '<div  align="center" style="display: none!important;">'+item.role_id+'</div> ' +
                            '<div  align="center">'+item.role_name+'</div> ' +
                            '<div style="width: 54% !important;"   align="center">'+role_tac+'</div> ' +
                            '<div  align="center">' +
                            '<span onclick="role_mod(this)" class="setting_mod">修改</span><span onclick="role_del(this)" class="setting_del">删除</span>' +
                            '</div>' +
                            '</li>'
                        $('#role_list').append(str2);
                    }
                });

                totalPage = Math.ceil(count/pageSize);//向上取整,有小数就整数部分加1
                page(totalPage, current);
                setCookie('role_list',JSON.stringify(response.data),1);
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
            $("#role_list li").not(":first").remove();
            var str = '';
            $.each(JSON.parse(getCookie('role_list')),function(i,item){
                var start = pageSize * (p - 1) + 1;
                var end   = pageSize * p;
                var flag = (i+1 >= start && i+1 <= end);
                if(flag)
                {
                    if(item.role_tac.length < 50)
                    {
                        var role_tac = item.role_tac;
                    }
                    else
                    {
                        var role_tac = item.role_tac.substring(0, 50) + '...';
                    }
                    var str2 = '<li class="link_setting" data-role_id="'+item.role_id+'" data-role_name="'+item.role_name+'" data-role_tac="'+item.role_tac+'" >'+
                        '<div  align="center" style="display: none!important;">'+item.role_id+'</div> ' +
                        '<div  align="center">'+item.role_name+'</div> ' +
                        '<div style="width: 54% !important;"   align="center">'+role_tac+'</div> ' +
                        '<div  align="center">' +
                        '<span onclick="role_mod(this)" class="setting_mod">修改</span><span onclick="role_del(this)" class="setting_del">删除</span>' +
                        '</div>' +
                        '</li>'
                    $('#role_list').append(str2);
                }
            });
        }
    });
}
function get_dic_tac()
{
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_dic_tac",
        dataType:'json',
        success:function(response){
            //	console.log(response);
            if(response.status)
            {
                var str = '';
                var j	= 0;
                $.each(response.data, function(i,item) {
                    if (item.available == 'true') {
                        str = '<span style="color: #ffffff"><input class="tac tac_' + item.tac + '" type="checkbox" data-tac = "' + item.tac + '" />' + item.tac + '</span>';
                    }else {
                        str = '<span style="color: #ffffff"><input class="tac tac_' + item.tac + '" type="checkbox" disabled="disabled" data-tac = "' + item.tac + '" />' + item.tac + '</span>';
                    }
                    $('.tac_content').append(str);
                    j++;
                    if(j == 12)
                    {
                        j = 0;
                        $('.tac_content').append('<br />');
                    }
                });
            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
function role_add()
{
    $('#role_id').text('');
    $('#role_name').val('');
    $(".tac").each(function(){
        if(!$(this).is(':disabled')){
            $(this).prop('checked',true);
        }
    });
    $('#bg').addClass('frosted-glass');
    $('.setting_bg').slideDown(500);
}
function role_mod(obj)
{
    var role_id   = $(obj).parent().parent().data('role_id');
    var role_name = $(obj).parent().parent().data('role_name');
    var role_tac  = $(obj).parent().parent().data('role_tac');

    if(role_tac.toString().indexOf(",")>0){
        var role_tacs = role_tac.split(","); //字符分割
        for (i = 0; i < role_tacs.length; i++) {
            var tac_class = '.tac_' + role_tacs[i];
            $(tac_class).prop("checked", 'checked');
        }
    }else {
        var tac_class = '.tac_'+role_tac;
        $(tac_class).prop("checked", 'checked');
    }



    $('#role_id').text(role_id);
    $('#role_name').val(role_name);
    $('#bg').addClass('frosted-glass');
    $('.setting_bg').slideDown(500);
}
function role_del(obj)
{
    var del = confirm("确定要删除么？");
    if(!del){
        return false;
    }
    var role_id = $(obj).parent().parent().data('role_id');
    var data = {'roleId':role_id};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/del_role",
        data:JSON.stringify(data),
        dataType:'json',
        success:function(response){
            //	console.log(response);
            if(response.status)
            {
                init_role();
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

