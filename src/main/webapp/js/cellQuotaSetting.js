/**
 * Created by a7289 on 2016/11/17 0017.
 */

$(document).ready(function() {
    count     = 0;  //总共多少记录
    totalPage = 0;  //总共多少页
    pageSize  = 10; //每页多少记录
    curr   	  = 1;	//当前第几页
    init();
    $('.setting_btn > span:nth-child(1)').bind('click', function(){
        var quota_name  = $('#quota_name').val();
        var threshold_1 = $('#threshold_1').val();
        var threshold_2 = $('#threshold_2').val();
        var threshold_3 = $('#threshold_3').val();
        var quota_type  = $('#quota_type').val();
        var quota_unit  = $('#quota_unit').val();
        var data = {'quotaName':quota_name,
            'threshold1':threshold_1,
            'threshold2':threshold_2,
            'threshold3':threshold_3,
            'quotaType':quota_type,
            'quotaUnit':quota_unit}
        $.ajax({
            contentType : 'application/json;charset=utf-8',
            type:"post",
            url:"../lte/mod_cell_quota_threshold",
            data:JSON.stringify(data),
            dataType:'json',
            success:function(response){
                //	console.log(response);
                if(response.status)
                {
                    init();
                    $('#quota_name').val('');
                    $('#threshold_1').val('');
                    $('#threshold_2').val('');
                    $('#threshold_3').val('');
                    $('.setting_bg').slideUp(500,function (){
                        $('#bg').removeClass('frosted-glass');
                    })
                }
                else
                {
                    alert('修改失败,请稍后再试!');
                }
            },
            error:function(e){
                console.log(e.responseText);
            }
        });
    });
    $('.setting_btn > span:nth-child(2)').bind('click', function(){
        $('#quota_name').val('');
        $('#threshold_1').val('');
        $('#threshold_2').val('');
        $('#threshold_3').val('');
        $('.setting_bg').slideUp(500,function (){
            $('#bg').removeClass('frosted-glass');
        })
    });
});
function init()
{
    $("#cell_quota_list li").not(":first").remove();
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_cell_quota_threshold",
        dataType:'json',
        success:function(response){
            //	console.log(response);
            if(response.status)
            {
                var str = '';
                $.each(response.data, function(i,item){
                    count = i + 1;
                    var start = pageSize * (curr - 1) + 1;
                    var end   = pageSize * curr;
                    if(i+1 >= start && i+1 <= end)
                    {
                        var quota_type = '小于';
                        if(item.quota_type == 1){quota_type = '大于';}
                        var quota_unit = '百分比';
                        if(item.quota_unit == 1){quota_unit = '整数';}
                        var str2 = '<li class="link_setting" >'+
                            '<div style="width: 16% !important;"  align="center">'+item.quota_name+'</div> ' +
                            '<div  align="center">'+quota_type+'</div> ' +
                            '<div  align="center">'+item.threshold_1+'</div> ' +
                            '<div  align="center">'+item.threshold_2+'</div> ' +
                            '<div  align="center">'+item.threshold_3+'</div> ' +
                            '<div  align="center">'+quota_unit+'</div> ' +
                            '<div  align="center" style="display: none!important;">'+item.quota_type+'</div> ' +
                            '<div  align="center" style="display: none!important;">'+item.quota_unit+'</div> ' +
                            '<div  align="center">' +
                            '<span onclick="tac_mod(this)" class="setting_mod" style="float: none!important;">修改</span>' +
                            '</div>' +
                            '</li>'
                        $('#cell_quota_list').append(str2);
                    }
                });
                sessionStorage.setItem("cell_quota_list", JSON.stringify(response.data));
                totalPage = Math.ceil(count/pageSize);//向上取整,有小数就整数部分加1
                page(totalPage, curr);
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
            curr = p;
            $("#cell_quota_list li").not(":first").remove();
            var str = '';
            $.each(JSON.parse(sessionStorage.getItem("cell_quota_list")),function(i,item){
                var start = pageSize * (p - 1) + 1;
                var end   = pageSize * p;
                var flag = (i+1 >= start && i+1 <= end);
                if(flag)
                {
                    var quota_type = '小于';
                    if(item.quota_type == 1){quota_type = '大于';}
                    var quota_unit = '百分比';
                    if(item.quota_unit == 1){quota_unit = '整数';}
                    var str2 = '<li class="link_setting" >'+
                        '<div style="width: 16% !important;"  align="center">'+item.quota_name+'</div> ' +
                        '<div  align="center">'+quota_type+'</div> ' +
                        '<div  align="center">'+item.threshold_1+'</div> ' +
                        '<div  align="center">'+item.threshold_2+'</div> ' +
                        '<div  align="center">'+item.threshold_3+'</div> ' +
                        '<div  align="center">'+quota_unit+'</div> ' +
                        '<div  align="center" style="display: none!important;">'+item.quota_type+'</div> ' +
                        '<div  align="center" style="display: none!important;">'+item.quota_unit+'</div> ' +
                        '<div  align="center">' +
                        '<span onclick="tac_mod(this)" class="setting_mod" style="float: none!important;">修改</span>' +
                        '</div>' +
                        '</li>'
                    $('#cell_quota_list').append(str2);
                }
            });
        }
    });
}
function tac_mod(obj)
{
    var quota_name = $(obj).parent().parent().children(':nth-child(1)').text();

    var threshold_1 = $(obj).parent().parent().children(':nth-child(3)').text();
    var threshold_2 = $(obj).parent().parent().children(':nth-child(4)').text();
    var threshold_3 = $(obj).parent().parent().children(':nth-child(5)').text();

    var quota_type = $(obj).parent().parent().children(':nth-child(7)').text();
    var quota_unit = $(obj).parent().parent().children(':nth-child(8)').text();

    $('#quota_name').val(quota_name);

    $('#threshold_1').val(threshold_1);
    $('#threshold_2').val(threshold_2);
    $('#threshold_3').val(threshold_3);

    $('#quota_type').val(quota_type);
    $('#quota_unit').val(quota_unit);

    $('#bg').addClass('frosted-glass');
    $('.setting_bg').slideDown(500);}
