/**
 * Created by a7289 on 2016/12/30 0030.
 */
var tac = '';
$(document).ready(function() {
    count     = 0;  //总共多少记录
    totalPage = 0;  //总共多少页
    pageSize  = 10; //每页多少记录
    current   = 1;	//当前第几页
    init_user_important();
    $('.select_all_tac').bind('click', function(){
        tac = '';
        $(".tac").each(function(){
            if(!$(this).is(':disabled')){
                if($(this).prop('checked')==false){
                $(this).prop('checked',true);
                    var one_tac = $(this).data('tac');
                    if (tac != ''){
                        tac += (','+one_tac);
                    }else {
                        tac = one_tac;
                    }
                }
            }
        })
        get_node_set_by_tac(tac);
    });
    $('.un_select_all_tac').bind('click', function(){
        $('.tac').prop('checked', false);
        $(".tac").each(function(){
            if(!$(this).is(':disabled')){
                var one_tac = $(this).data('tac');
                if (tac != ''){
                    tac += (','+one_tac);
                }else {
                    tac = one_tac;
                }
            }
        })
        remove_node_set_by_tac(tac);
    });
    $('.select_all_node').bind('click', function(){
        $(".node").each(function(){
            if(!$(this).is(':disabled')){
                $(this).prop('checked',true);
            }
        })
    });
    $('.un_select_all_node').bind('click', function(){
        $('.node').prop('checked', false);
    });
    $('.setting_btn > span:nth-child(1)').bind('click', function(){
        var user_id   = $('#user_id').text();
        var important_tac  = '';
        $('.tac').each(function(){
            if($(this).is(':checked'))
            {
                if(important_tac == '')
                {
                    important_tac = $(this).data('tac');
                }
                else
                {
                    important_tac = important_tac + ',' + $(this).data('tac');
                }
            }
        });
        var important_node  = '';
        $('.node').each(function(){
            if($(this).is(':checked'))
            {
                if(important_node == '')
                {
                    important_node = $(this).data('node');
                }
                else
                {
                    important_node = important_node + ',' + $(this).data('node');
                }
            }
        });
        var data = {'userId':user_id,'importantTac':important_tac,'importantNode':important_node};
        $.ajax({
            contentType : 'application/json;charset=utf-8',
            type:"post",
            url:"../lte/check_user_important_exist",
            data:JSON.stringify(data),
            dataType:'json',
            async:false,
            success:function(response){
                //		console.log(response);
                if(response.status)
                {
                    $.ajax({
                        contentType : 'application/json;charset=utf-8',
                        type:"post",
                        url:"../lte/mod_user_important",
                        data:JSON.stringify(data),
                        dataType:'json',
                        async:false,
                        success:function (response) {
                            if (response.status){
                                init_user_important();
                                $('#user_id').text('');
                                $('#role_name').val('');
                                $('.setting_bg').slideUp(500,function (){
                                    $('#bg').removeClass('frosted-glass');
                                    $('.tac_content span').remove();
                                    $('.tac_content br').not(":first").remove();
                                    $('.node_content span').remove();
                                    $('.node_content br').not(":first").remove();
                                })
                            }
                        },
                        error:function(e){
                            console.log(e.responseText);
                        }
                    })
                }else {
                    $.ajax({
                        contentType : 'application/json;charset=utf-8',
                        type:"post",
                        url:"../lte/add_user_important",
                        data:JSON.stringify(data),
                        dataType:'json',
                        async:false,
                        success:function (response) {
                            if (response.status){
                                init_user_important();
                                $('#user_id').text('');
                                $('#role_name').val('');
                                $('.setting_bg').slideUp(500,function (){
                                    $('#bg').removeClass('frosted-glass');
                                    $('.tac_content span').remove();
                                    $('.tac_content br').not(":first").remove();
                                    $('.node_content span').remove();
                                    $('.node_content br').not(":first").remove();
                                })
                            }
                        },
                        error:function(e){
                            console.log(e.responseText);
                        }
                    })
                }
            },
            error:function(e){
                console.log(e.responseText);
            }
        });
    });
    $('.setting_btn > span:nth-child(2)').bind('click', function(){
        $('#user_id').text('');
        $('#role_name').text('');
        $('.setting_bg').slideUp(500,function (){
            $('#bg').removeClass('frosted-glass');
            $('.tac_content span').remove();
            $('.tac_content br').not(":first").remove();
            $('.node_content span').remove();
            $('.node_content br').not(":first").remove();
        })
    });
});

function init_user_important()
{
    $("#important_list li").not(":first").remove();
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/init_user_important",
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
                        var important_tac = '';
                        var important_node = '';
                        if(item.important_tac == null || item.important_tac == ''){
                            important_tac = '未设置';
                        }else {
                            if(item.important_tac.length < 50)
                            {
                                important_tac = item.important_tac;
                            }
                            else
                            {
                                important_tac = item.important_tac.substring(0, 50) + '...';
                            }
                        }

                        if(item.important_node == null || item.important_node == ''){
                            important_node = '未设置';
                        }else {
                            if(item.important_node.length < 50)
                            {
                                important_node = item.important_node;
                            }
                            else
                            {
                                important_node = item.important_node.substring(0, 50) + '...';
                            }
                        }
                        var str2 = '<li class="link_setting" data-user_id="'+item.user_id+'" data-user_name="'+item.user_name+'" data-important_tac="'+item.important_tac+'" data-important_node="'+item.important_node+'">'+
                            '<div  align="center" style="display: none!important;">'+item.user_id+'</div> ' +
                            '<div  align="center">'+item.user_name+'</div> ' +
                            '<div  align="center" style="width: 36% !important;">'+important_tac+'</div> ' +
                            '<div  align="center" style="width: 36% !important;">'+important_node+'</div> ' +
                            '<div  align="center">' +
                            '<span style="margin-left: 30% !important;" onclick="user_important_setting_mod(this)" class="setting_mod">修改</span>' +
                            '</div>' +
                            '</li>'
                        $('#important_list').append(str2);
                    }
                });
                totalPage = Math.ceil(count/pageSize);//向上取整,有小数就整数部分加1
                page(totalPage, current);
                setCookie('important_list',JSON.stringify(response.data),1);
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

function user_important_setting_mod(obj) {
    var important_tacs = '';
    var important_nodes = '';
    var user_id   = $(obj).parent().parent().data('user_id');
    var important_tac   = $(obj).parent().parent().data('important_tac');
    var important_node   = $(obj).parent().parent().data('important_node');
    if(important_tac != null) {
        if (important_tac.toString().indexOf(",") > 0) {
             important_tacs = important_tac.split(","); //字符分割
        } else {
             important_tacs = new Array();
            important_tacs.push(important_tac)
        }
    }
    if(important_node != null) {
        if (important_node.toString().indexOf(",") > 0) {
             important_nodes = important_node.split(","); //字符分割
        } else {
             important_nodes = new Array();
            important_nodes.push(important_node)
        }
    }
    var data = { 'userId':user_id};
    var role_name = '';
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        data:JSON.stringify(data),
        url:"../lte/get_all_user_tac",
        dataType:'json',
        async:false,
        success:function(response){
            if(response.status)
            {
                var str = '';
                var j	= 0;
                var role_tac = response.data.user_role_tac;
                role_name = response.data.role_name;
                if(role_tac.toString().indexOf(",")>0){
                    var role_tacs = role_tac.split(","); //字符分割
                    for (i = 0; i < role_tacs.length; i++) {
                        var tac_class = '.tac_' + role_tacs[i];
                            str = '<span style="display:inline-block;color: #ffffff"><input class="tac tac_' + role_tacs[i] + '" type="checkbox" data-tac = "' + role_tacs[i] + '" onchange="get_node_by_tac(this)"/>' + role_tacs[i] + '</span>';
                        $('.tac_content').append(str);
                        j++;
                        if(j == 12)
                        {
                            j = 0;
                            $('.tac_content').append('<br />');
                        }
                    }
                }else {
                    var tac_class = '.tac_'+role_tac;
                    str = '<span style="display:inline-block;color: #ffffff"><input class="tac tac_' + role_tac + '" type="checkbox" data-tac = "' + role_tac + '" onchange="get_node_by_tac(this)"/>' + role_tac + '</span>';
                    $('.tac_content').append(str);
                }
            }else {
                $.ajax({
                    contentType : 'application/json;charset=utf-8',
                    type:"get",
                    url:"../lte/get_dic_tac",
                    dataType:'json',
                    async:false,
                    success:function(response){
                        if(response.status)
                        {
                            role_name = '未设置（默认为全部权限）';
                            var str = '';
                            var j	= 0;
                            $.each(response.data, function(i,item) {
                                if (item.available == 'true') {
                                        str = '<span style="display:inline-block;color: #ffffff"><input class="tac tac_' + item.tac + '" type="checkbox" data-tac = "' + item.tac + '" onchange="get_node_by_tac(this)"/>' + item.tac + '</span>';
                                }else {
                                    str = '<span style="display:inline-block;color: #ffffff"><input class="tac tac_' + item.tac + '" type="checkbox" disabled="disabled" data-tac = "' + item.tac + '" onchange="get_node_by_tac(this)"/>' + item.tac + '</span>';
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
                })
            }
            var tac = '';
            for (m = 0; m < important_tacs.length; m++) {
                var tac_class = '.tac_' + important_tacs[m];
                $(tac_class).prop('checked', 'checked');
                if (tac != ''){
                    if (important_tacs[m]!='') {
                        tac += (',' + important_tacs[m]);
                    }
                }else {
                    tac = important_tacs[m];
                }
            }
            if (tac != '') {
                get_node_set_by_tac(tac);
            }
            for (m = 0; m < important_nodes.length; m++) {
                var node_class = '.node_' + important_nodes[m];
                $(node_class).prop('checked', 'checked');
            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    })
    $('#role_name').val(role_name);
    $('#user_id').text(user_id);
    $('#bg').addClass('frosted-glass');
    $('.setting_bg').slideDown(500,function () {
        // $('.setting_bg').perfectScrollbar();
        $('.setting_bg').animate({scrollTop:0},0);
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
            $("#important_list li").not(":first").remove();
            var str = '';
            $.each(JSON.parse(getCookie('important_list')),function(i,item){
                var start = pageSize * (p - 1) + 1;
                var end   = pageSize * p;
                var flag = (i+1 >= start && i+1 <= end);
                if(flag)
                {
                    var important_tac = '';
                    var important_node = '';
                    if(item.important_tac == null || item.important_tac == ''){
                        important_tac = '未设置';
                    }else {
                        if(item.important_tac.length < 50)
                        {
                            important_tac = item.important_tac;
                        }
                        else
                        {
                            important_tac = item.important_tac.substring(0, 50) + '...';
                        }
                    }

                    if(item.important_node == null || item.important_node == ''){
                        important_node = '未设置';
                    }else {
                        if(item.important_node.length < 50)
                        {
                            important_node = item.important_node;
                        }
                        else
                        {
                            important_node = item.important_node.substring(0, 50) + '...';
                        }
                    }
                    var str2 = '<li class="link_setting" data-user_id="'+item.user_id+'" data-user_name="'+item.user_name+'" data-important_tac="'+item.important_tac+'" data-important_node="'+item.important_node+'">'+
                        '<div  align="center" style="display: none!important;">'+item.user_id+'</div> ' +
                        '<div  align="center">'+item.user_name+'</div> ' +
                        '<div  align="center" style="width: 36% !important;">'+important_tac+'</div> ' +
                        '<div  align="center" style="width: 36% !important;">'+important_node+'</div> ' +
                        '<div  align="center">' +
                        '<span style="margin-left: 30% !important;" onclick="user_important_setting_mod(this)" class="setting_mod">修改</span>' +
                        '</div>' +
                        '</li>'
                    $('#important_list').append(str2);
                }
            });
        }
    });
}

function get_node_by_tac(obj) {
    if ($(obj).prop('checked')){
        $('#note').css('display','none');
        $('.node_content').css('display','block');
        tac = $(obj).data('tac');
        var data = {tac : tac};
        $.ajax({
            contentType : 'application/json;charset=utf-8',
            type:"post",
            data:JSON.stringify(data),
            url:"../lte/get_node_by_tac",
            dataType:'json',
            async:false,
            success:function(response){
                if(response.status)
                {
                    var str = '';
                    $.each(response.data, function(i,item) {
                            str = '<span  style="display:inline-block;color: #ffffff"><input class="node node_' + item.node + '" type="checkbox" data-node = "' + item.node + '" "/>' + item.node + '</span>';
                            $('.node_content').append(str);
                    });
                }
            },
            error:function(e){
                console.log(e.responseText);
            }
        })
    }
    else {
         tac = $(obj).data('tac');
        var data = {tac : tac};
        $.ajax({
            contentType : 'application/json;charset=utf-8',
            type:"post",
            data:JSON.stringify(data),
            url:"../lte/get_node_by_tac",
            dataType:'json',
            async:false,
            success:function(response){
                if(response.status)
                {
                    $.each(response.data, function(i,item) {
                        var node = '.node_' + item.node;
                            $(node).parent().remove();
                    });
                }
                if($('.node').length <= 0 ){
                    $('#note').css('display','block');
                    $('.node_content').css('display','none');
                }
            },
            error:function(e){
                console.log(e.responseText);
            }
        })

    }

}

function get_node_set_by_tac(obj) {
    $('#note').css('display','none');
    $('.node_content').css('display','block');
        var data = {tac : obj};
        $.ajax({
            contentType : 'application/json;charset=utf-8',
            type:"post",
            data:JSON.stringify(data),
            url:"../lte/get_node_by_tac",
            dataType:'json',
            async:false,
            success:function(response){
                if(response.status)
                {
                    var str = '';
                    $.each(response.data, function(i,item) {
                        str = '<span  style="display:inline-block;color: #ffffff"><input class="node node_' + item.node + '" type="checkbox" data-node = "' + item.node + '" "/>' + item.node + '</span>';
                        $('.node_content').append(str);
                    });
                }
            },
            error:function(e){
                console.log(e.responseText);
            }
        })
}

function remove_node_set_by_tac(obj) {
        var data = {tac : obj};
        $.ajax({
            contentType : 'application/json;charset=utf-8',
            type:"post",
            data:JSON.stringify(data),
            url:"../lte/get_node_by_tac",
            dataType:'json',
            async:false,
            success:function(response){
                if(response.status)
                {
                    $.each(response.data, function(i,item) {
                        var node = '.node_' + item.node;
                        $(node).parent().remove();
                    });
                }
                if($('.node').length <= 0 ){
                    $('#note').css('display','block');
                    $('.node_content').css('display','none');
                }
            },
            error:function(e){
                console.log(e.responseText);
            }
        })
}

