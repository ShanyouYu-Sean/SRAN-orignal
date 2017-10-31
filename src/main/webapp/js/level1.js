/**
 * Created by a7289 on 2016/12/6 0006.
 */
map = '';
var pop = new Map();
var markers = "";
/*检查并获取登录信息*/
check_login();
user_id  = getCookie('user_id');
/*显示用户名称*/
show_username();
role_tac = '';
update_time = '';
quota_threshold_tac = '';
quota_threshold_node = '';
/*检查选择的3g还是4g*/
check_select_net();
/*取出当前用户的role_tac*/
get_user_role_tac(user_id);
if(role_tac == undefined || role_tac == null || role_tac == 'null'){role_tac = '';}
/*获取tac门限*/
get_tac_quota_threshold();
/*获得node门限*/
get_node_quota_threshold();
tac_status = '';
/*获得更新时间*/
get_update_time();
/*获得用户关注列表*/
var info = get_user_important();
var important_tacs = info["important_tacs"];
var important_nodes = info["important_nodes"];
/*获取tac的详细信息数据(并加载tac重点关注列表)*/
init_tac_data();
/*加载地图*/
draw_map();
/*加载node重点关注列表*/
init_important_node();

$(function () {

    locate();

    $(".important").click(function(){
        $(".click").removeClass("click");
        $(this).addClass("click");
        var id = $(this).attr('id');
        if(id == 'important_tac'){
            $('#left_tac').show();
            $('#left_node').hide();
            $('#load').hide();
        }else {
            var node = $('#node').children().length;
            if (node == 0){
                if (!$('.loading').is('div')) {
                    var tempStr = '<div class="loading"> <div class="load ball-triangle-path"> <div></div> <div></div> <div></div> </div> </div>';
                    $('#left_node').append(tempStr);
                    $('#left_tac').hide();
                    $('#left_node').show();
                } else {
                    $('.loading').show();
                    $('#left_tac').hide();
                    $('#left_node').show();
                }
            }else {
                $('#left_tac').hide();
                $('#left_node').show();
            }
        }
    });
    $(".important").hover(function(){
            $(this).addClass("hover");},
        function () {
            $(this).removeClass("hover");
        });
    // var x ="";
    // $(".link_left").click(function(){
    //     var id = $(this).attr("id");
    //     var $el = $('.link').parent();
    //     $this = $(this),
    //         $next = $this.next();
    //     $next.slideToggle();
    //     $this.parent().toggleClass('open');
    //     if (x == id){
    //         $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
    //         x = "";
    //     }else {
    //         x = id;
    //         $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
    //
    //     }
    // });
})


/*取出当前用户的role_tac*/
function get_user_role_tac(user_id)
{
    var data = {'userId':user_id};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/get_user_role_tac",
        data:JSON.stringify(data),
        dataType:'json',
        async:false,
        success:function(response){
            //	console.log(response);
            role_tac = response.data.role_tac;
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
/*获取tac门限*/
function get_tac_quota_threshold()
{
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_tac_quota_threshold",
        dataType:'json',
        async:false,
        success:function(response){
            //	console.log(response.data);
            quota_threshold_tac = response.data;
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
/*获得node门限*/
function get_node_quota_threshold()
{
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_node_quota_threshold",
        dataType:'json',
        async:false,
        success:function(response){
            //	console.log(response.data);
            quota_threshold_node = response.data;
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
/*获取tac的详细信息数据*/
function init_tac_data()
{
    if((important_tacs.length == 1)&&(important_tacs[0]=='')){
        var str = "<li> " +
            "<div class='link_left'> " +
            "未设置重点关注TAC"+
            "</div> " +
            "</li>";
        $('#tac').append(str);
    }
    var data = { 'tac':role_tac};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"POST",
        url:"../lte/get_group_station_by_tac",
        data:JSON.stringify(data),
        dataType:'json',
        async:false,
        error:function(e){
            console.log(e.responseText);
        },
        success:function(response){
            var str = '';
            if(response.status)
            {
                $.each(response.data, function(i,ite) {
                    arr = new Array();

                    var t_RRC_Setup_Succ_Rate = ite.RRC_Setup_Succ_Rate;
                    if(t_RRC_Setup_Succ_Rate > 100){t_RRC_Setup_Succ_Rate = 100;}
                    if(t_RRC_Setup_Succ_Rate < 0){t_RRC_Setup_Succ_Rate = 0;}
                    var RRC_Setup_Succ_Rate   = kpi('RRC_setup_succ_Rate', t_RRC_Setup_Succ_Rate);

                    var t_S1_Setup_Succ_Rate = ite.S1_Setup_Succ_Rate;
                    if(t_S1_Setup_Succ_Rate > 100){t_S1_Setup_Succ_Rate = 100;}
                    if(t_S1_Setup_Succ_Rate < 0){t_S1_Setup_Succ_Rate = 0;}
                    var S1_Setup_Succ_Rate   = kpi('S1_Setup_Succ_Rate', t_S1_Setup_Succ_Rate);

                    var t_ERAB_Setup_Succ_Rate = ite.ERAB_Setup_Succ_Rate;
                    if(t_ERAB_Setup_Succ_Rate > 100){t_ERAB_Setup_Succ_Rate = 100;}
                    if(t_ERAB_Setup_Succ_Rate < 0){t_ERAB_Setup_Succ_Rate = 0;}
                    var ERAB_Setup_Succ_Rate   = kpi('ERAB_Setup_Succ_Rate', t_ERAB_Setup_Succ_Rate);

                    var t_paging_Succ_Rate = ite.paging_Succ_Rate;
                    if(t_paging_Succ_Rate > 100){t_paging_Succ_Rate = 100;}
                    if(t_paging_Succ_Rate < 0){t_paging_Succ_Rate = 100;}
                    var paging_Succ_Rate   = kpi('paging_Succ_Rate', t_paging_Succ_Rate);

                    var t_Cell_Utilization_Rate = ite.Cell_Utilization_Rate;
                    if(t_Cell_Utilization_Rate > 100){t_Cell_Utilization_Rate = 100;}
                    if(t_Cell_Utilization_Rate < 0){t_Cell_Utilization_Rate = 0;}
                    var Cell_Utilization_Rate 	= kpi('Cell_Utilization_Rate', t_Cell_Utilization_Rate);

                    var t_Within_System_HO_Succ_Rate = ite.Within_System_HO_Succ_Rate;
                    if(t_Within_System_HO_Succ_Rate > 100){t_Within_System_HO_Succ_Rate = 100;}
                    if(t_Within_System_HO_Succ_Rate < 0){t_Within_System_HO_Succ_Rate = 0;}
                    var Within_System_HO_Succ_Rate   = kpi('Within_System_HO_Succ_Rate', t_Within_System_HO_Succ_Rate);

                    var t_Erab_Drop_Rate = ite.Erab_Drop_Rate;
                    if(t_Erab_Drop_Rate > 100){t_Erab_Drop_Rate = 100;}
                    if(t_Erab_Drop_Rate < 0){t_Erab_Drop_Rate = 0;}
                    var Erab_Drop_Rate   = kpi('Erab_Drop_Rate', t_Erab_Drop_Rate);

                    var t_DL_PRB_Occupy_Rate = ite.DL_PRB_Occupy_Rate;
                    if(t_DL_PRB_Occupy_Rate > 100){t_DL_PRB_Occupy_Rate = 100;}
                    if(t_DL_PRB_Occupy_Rate < 0){t_DL_PRB_Occupy_Rate = 0;}
                    var DL_PRB_Occupy_Rate   = kpi('DL_PRB_Occupy_Rate', t_DL_PRB_Occupy_Rate);

                    var t_UL_PRB_Occupy_Rate = ite.UL_PRB_Occupy_Rate;
                    if(t_UL_PRB_Occupy_Rate > 100){t_UL_PRB_Occupy_Rate = 100;}
                    if(t_UL_PRB_Occupy_Rate < 0){t_UL_PRB_Occupy_Rate = 0;}
                    var UL_PRB_Occupy_Rate   = kpi('UL_PRB_Occupy_Rate', t_UL_PRB_Occupy_Rate);

                    var Dl_ThroughputRate_Mbps 	   = kpi('Dl_ThroughputRate_Mbps', ite.Dl_ThroughputRate_Mbps);
                    var Ul_ThroughputRate_Mbps 	   = kpi('Ul_ThroughputRate_Mbps', ite.Ul_ThroughputRate_Mbps);
                    var DL_User_Speed_Mbps 		   = kpi('DL_User_Speed_Mbps', ite.DL_User_Speed_Mbps);
                    var UL_User_Speed_Mbps 		   = kpi('UL_User_Speed_Mbps', ite.UL_User_Speed_Mbps);
                    var UE_SessionTime 			   = kpi('UE_SessionTime', ite.UE_SessionTime);
                    var Drb_pmSessionTime 		   = kpi('Drb_pmSessionTime', ite.Drb_pmSessionTime);
                    var DL_Active_User_Num 		   = kpi('DL_Active_User_Num', ite.DL_Active_User_Num);
                    var UL_Active_User_Num 		   = kpi('UL_Active_User_Num', ite.UL_Active_User_Num);
                    arr.sort();
                    var status = get_max(arr[0], arr[arr.length - 1]);
                    if(tac_status == '')
                    {
                        tac_status = ite.tac + '|' + status;
                    }
                    else
                    {
                        tac_status = tac_status + ',' + ite.tac + '|' + status;
                    }
                    var type   = '';
                    var tac_status_cube = '';
                    if(status == 0)
                    {
                        type   = '<font color="#06fa06">正常</font>';
                        tac_status_cube = "<div style='float: left;background:#06fa06;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    else if(status == 1)
                    {
                        type   = '<font color="#f29f00">异常</font>';
                        tac_status_cube = "<div style='float: left;background:#f29f00;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    else if(status == 2)
                    {
                        type   = '<font color="#cc00ff">异常</font>';
                        tac_status_cube = "<div style='float: left;background:#cc00ff;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    else if(status == 3)
                    {
                        type   = '<font color="#fa0606">异常</font>';
                        tac_status_cube = "<div style='float: left;background:#fa0606;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    if(
                        ite.RRC_Setup_Succ_Rate == '0.0' &&
                        ite.S1_Setup_Succ_Rate == '0.0' &&
                        ite.ERAB_Setup_Succ_Rate == '0.0' &&
                        ite.paging_Succ_Rate == '0.0' &&
                        ite.Cell_Utilization_Rate == '0.0' &&
                        (ite.Within_System_HO_Succ_Rate == '0.0' || ite.Within_System_HO_Succ_Rate == '100') &&
                        ite.Erab_Drop_Rate == '0.0' &&
                        ite.DL_PRB_Occupy_Rate == '0.0' &&
                        ite.UL_PRB_Occupy_Rate == '0.0' &&
                        ite.Dl_ThroughputRate_Mbps == '0.0' &&
                        ite.Ul_ThroughputRate_Mbps == '0.0' &&
                        ite.DL_User_Speed_Mbps == '0.0' &&
                        ite.UL_User_Speed_Mbps == '0.0' &&
                        ite.UE_SessionTime == '0.0' &&
                        ite.Drb_pmSessionTime == '0.0' &&
                        ite.DL_Active_User_Num == '0.0' &&
                        ite.UL_Active_User_Num == '0.0'
                    ){
                        RRC_Setup_Succ_Rate   	   = 'N/A';
                        S1_Setup_Succ_Rate    	   = 'N/A';
                        ERAB_Setup_Succ_Rate  	   = 'N/A';
                        paging_Succ_Rate      	   = 'N/A';
                        Cell_Utilization_Rate 	   = 'N/A';
                        Within_System_HO_Succ_Rate = 'N/A';
                        Erab_Drop_Rate             = 'N/A';
                        DL_PRB_Occupy_Rate 		   = 'N/A';
                        UL_PRB_Occupy_Rate 		   = 'N/A';
                        Dl_ThroughputRate_Mbps 	   = 'N/A';
                        Ul_ThroughputRate_Mbps 	   = 'N/A';
                        DL_User_Speed_Mbps 		   = 'N/A';
                        UL_User_Speed_Mbps 		   = 'N/A';
                        UE_SessionTime 			   = 'N/A';
                        Drb_pmSessionTime 		   = 'N/A';
                        DL_Active_User_Num 		   = 'N/A';
                        UL_Active_User_Num 		   = 'N/A';
                    }


                    for (m = 0; m < important_tacs.length; m++) {
                        if(ite.tac == important_tacs[m]){
                            var str1 = "<li> " +
                                "<div class='link_left' id='" + ite.tac +
                                "' onclick='open_submenu(this)'> " +
                                "<div style='float: left;width: 86%'>"+ite.tac+"</div>" +
                                tac_status_cube +
                                "</div> " +
                                "<ul class='submenu'> " +
                                "<li> " +
                                "<div style='float: left; '>参数</div> " +
                                "<div style='float: right; '>值&nbsp;&nbsp;&nbsp;&nbsp;</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left;'>更新时间</div> " +
                                "<div style='float: right; '>"+update_time+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>RRC_Setup_Succ_Rate(%)</br>RRC建立成功率</div> " +
                                "<div style='float: right; '>"+RRC_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>S1_Setup_Succ_Rate(%)</br>S1链路建立成功率</div> " +
                                "<div style='float: right; '>"+S1_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>ERAB_Setup_Succ_Rate(%)</br>ERAB建立成功率</div> " +
                                "<div style='float: right; '>"+ERAB_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>paging_Succ_Rate(%)</br>寻呼成功率</div> " +
                                "<div style='float: right; '>"+paging_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>Erab_Drop_Rate(%)</br>E-RAB掉线率</div> " +
                                "<div style='float: right;'>"+Erab_Drop_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>Within_System_HO_Succ_Rate(%)</br>系统内切换成功率</div> " +
                                "<div style='float: right; '>"+Within_System_HO_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>Cell_Utilization_Rate(%)</br>小区利用率</div> " +
                                "<div style='float: right; '>"+Cell_Utilization_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>UL_PRB_Occupy_Rate(%)</br>上行物理资源占用率</div> " +
                                "<div style='float: right; '>"+UL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>DL_PRB_Occupy_Rate(%)</br>下行物理资源占用率</div> " +
                                "<div style='float: right; '>"+DL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>Dl_ThroughputRate_Mbps(Mbps)</br>下行吞吐率</div> " +
                                "<div style='float: right; '>"+Dl_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>Ul_ThroughputRate_Mbps(Mbps)</br>上行吞吐率</div> " +
                                "<div style='float: right; '>"+Ul_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>DL_User_Speed_Mbps(Mbps)</br>下行用户速率</div> " +
                                "<div style='float: right; '>"+DL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>UL_User_Speed_Mbps(Mbps)</br>上行用户速率</div> " +
                                "<div style='float: right; '>"+UL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>UE_SessionTime(ms)</br>用户会话时长</div> " +
                                "<div style='float: right; '>"+UE_SessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>Drb_pmSessionTime(ms)</br>专用无线承载会话时长</div> " +
                                "<div style='float: right; '>"+Drb_pmSessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>DL_Active_User_Num</br>下行活动用户数</div> " +
                                "<div style='float: right; '>"+DL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "<li> " +
                                "<div style='float: left; '>UL_Active_User_Num</br>上行活动用户数</div> " +
                                "<div style='float: right; '>"+UL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                                "</li> " +
                                "</ul> " +
                                "</li>";
                            $('#tac').append(str1);
                        }
                    }



                    var str2 = "<li> " +
                        "<div class='link' id='" + ite.tac +
                        "'> " +
                        "<div style='display: inline-block; width: 70%'>"+ite.tac+"</div>" +
                        "<div style='display: inline-block; width: 20%'>"+type+"</div>" +
                        "<div class='details' style='display: inline-block;height:100%' onclick='goto(this)';></div>" +
                        "</div> " +
                        "<ul class='submenu'> " +
                        "<li> " +
                        "<div style='float: left; '>参数</div> " +
                        "<div style='float: right; '>值&nbsp;&nbsp;&nbsp;&nbsp;</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>更新时间</div> " +
                        "<div style='float: right; '>"+update_time+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>RRC_Setup_Succ_Rate(%)</br>RRC建立成功率</div> " +
                        "<div style='float: right; '>"+RRC_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>S1_Setup_Succ_Rate(%)</br>S1链路建立成功率</div> " +
                        "<div style='float: right; '>"+S1_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>ERAB_Setup_Succ_Rate(%)</br>ERAB建立成功率</div> " +
                        "<div style='float: right; '>"+ERAB_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>paging_Succ_Rate(%)</br>寻呼成功率</div> " +
                        "<div style='float: right; '>"+paging_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Erab_Drop_Rate(%)</br>E-RAB掉线率</div> " +
                        "<div style='float: right;'>"+Erab_Drop_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Within_System_HO_Succ_Rate(%)</br>系统内切换成功率</div> " +
                        "<div style='float: right; '>"+Within_System_HO_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Cell_Utilization_Rate(%)</br>小区利用率</div> " +
                        "<div style='float: right; '>"+Cell_Utilization_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_PRB_Occupy_Rate(%)</br>上行物理资源占用率</div> " +
                        "<div style='float: right; '>"+UL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_PRB_Occupy_Rate(%)</br>下行物理资源占用率</div> " +
                        "<div style='float: right; '>"+DL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Dl_ThroughputRate_Mbps(Mbps)</br>下行吞吐率</div> " +
                        "<div style='float: right; '>"+Dl_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Ul_ThroughputRate_Mbps(Mbps)</br>上行吞吐率</div> " +
                        "<div style='float: right; '>"+Ul_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_User_Speed_Mbps(Mbps)</br>下行用户速率</div> " +
                        "<div style='float: right; '>"+DL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_User_Speed_Mbps(Mbps)</br>上行用户速率</div> " +
                        "<div style='float: right; '>"+UL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UE_SessionTime(ms)</br>用户会话时长</div> " +
                        "<div style='float: right; '>"+UE_SessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Drb_pmSessionTime(ms)</br>专用无线承载会话时长</div> " +
                        "<div style='float: right; '>"+Drb_pmSessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_Active_User_Num</br>下行活动用户数</div> " +
                        "<div style='float: right; '>"+DL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_Active_User_Num</br>上行活动用户数</div> " +
                        "<div style='float: right; '>"+UL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "</ul> " +
                        "</li>";
                    $('#accordion').append(str2);
                });
            }
        }
    })
}

/*kpi比较*/
function kpi(quota_name, quota_val)
{
    var status = '';
    var temp = '';
    $.each(quota_threshold_tac, function(i,ite) {
        if(ite.quota_name == quota_name)
        {
            var threshold_1 = ite.threshold_1;
            var threshold_2 = ite.threshold_2;
            var threshold_3 = ite.threshold_3;
            if(ite.quota_unit == 0)
            {
                quota_val   = parseFloat(Number(quota_val));
                threshold_1 = parseFloat(Number(threshold_1));
                threshold_2 = parseFloat(Number(threshold_2));
                threshold_3 = parseFloat(Number(threshold_3));
            }
            if(ite.quota_unit == 1)
            {
                quota_val   = parseInt(Number(quota_val));
                threshold_1 = parseInt(Number(threshold_1));
                threshold_2 = parseInt(Number(threshold_2));
                threshold_3 = parseInt(Number(threshold_3));
            }
            //小于
            if(ite.quota_type == 0) {
                if(quota_val <= threshold_3) {
                    status = 3;
                }else if(quota_val <= threshold_2) {
                    status = 2;
                }else if(quota_val <= threshold_1) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
            //大于
            if(ite.quota_type == 1) {
                if(quota_val >= threshold_3) {
                    status = 3;
                }else if(quota_val >= threshold_2) {
                    status = 2;
                }else if(quota_val >= threshold_1) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
            arr.push(status);
            if(status == 0)
            {
                temp = '<font color="#06fa06">'+quota_val+'</font>';
            }
            else if(status == 1)
            {
                temp = '<font color="#f29f00">'+quota_val+'</font>';
            }
            else if(status == 2)
            {
                temp = '<font color="#cc00ff">'+quota_val+'</font>';
            }
            else if(status == 3)
            {
                temp = '<font color="#fa0606">'+quota_val+'</font>';
            }
        }
    });
    return temp;
}
/*加载地图*/
function draw_map()
{
    loading('start');

    map = L.map('map').setView([39.9087677478,116.3975780499], 10);
    url_google    = '../map/{z}/{x}/{y}.png';
    L.tileLayer(url_google,{minZoom: 10, maxZoom: 13, attribution: '2016 大连宏燊技术支持' }).addTo(map);

    // url_baidu    = '../map/{z}/{x}/{y}.png';
    // glayer_baidu = new L.TileLayer(url_baidu, {minZoom: 10, maxZoom: 13, attribution: '2016 爱立信技术支持' });
    // map          = new L.Map('map', {center: [39.92285, 116.40318], zoom: 10, layers: [glayer_baidu] });
    // baseLayers    = {"普通地图": glayer_baidu};
    // L.control.layers(baseLayers).addTo(map);

    function style() {
        return {
            fillColor: '#BBBBBB',
            weight: 2,
            opacity: 1,
            color: '#ffffff',
            dashArray: '3',
            fillOpacity: 0.7
        };
    }
    L.geoJson(statesData, {style: style}).addTo(map);




    var tac = '';
    markers = "";
    var parentGroup  = "";
    var mySubGroup  = "";
    var arrayOfMarkers  = new Array();
    var maxMarkers = "";
    var data = { 'tac':role_tac};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/get_max_station_by_tac",
        data:JSON.stringify(data),
        dataType:'json',
        async:false,
        success:function(response){
            if(response.status)
            {
                maxMarkers = response.data.maxStation;
            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });

    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/get_base_station_by_tac",
        data:JSON.stringify(data),
        dataType:'json',
        async:false,
        success:function(response){
            if(response.status)
            {
                $.each(response.data, function(i,ite) {
                    var each_tac = ite.tac;
                    var latitude = ite.latitude;
                    var longitude = ite.longitude;
                    var status = ite.status;

                    if (i == 0){
                            markers = createMarkerClusterGroup(status, each_tac,maxMarkers);
                            var marker = L.marker(new L.LatLng(latitude, longitude));
                            markers.addLayer(marker);
                            var popup = new L.Popup({closeButton: false});
                            markers.on('clusterclick', function (a) {
                                var visibleOne = markers.getVisibleParent(marker);
                                setCookie('tac',each_tac,1);
                                var a = "<a href='../lte/level2' >详细</a>";
                                var d = "<p>"+each_tac+"<br />"+a+" </p>";
                                popup.setContent(d);
                                popup.setLatLng(visibleOne.getLatLng());
                                map.openPopup(popup);
                        });
                        markers.on('click', function (a) {
                            var visibleOne = markers.getVisibleParent(marker);
                            setCookie('tac',each_tac,1);
                            var a = "<a href='../lte/level2' >详细</a>";
                            var d = "<p>"+each_tac+"<br />"+a+" </p>";
                            popup.setContent(d);
                            popup.setLatLng(visibleOne.getLatLng());
                            map.openPopup(popup);
                        });
                            pop.put(each_tac,marker);
                            tac = each_tac;
                    }else if (i == response.data.length-1){
                        map.addLayer(markers);
                    }else {
                        if (tac != each_tac) {
                            map.addLayer(markers);
                            markers = createMarkerClusterGroup(status, each_tac,maxMarkers);
                            var marker = L.marker(new L.LatLng(latitude, longitude));
                            markers.addLayer(marker);
                            var popup = new L.Popup({closeButton: false});
                            markers.on('clusterclick', function (a) {
                                var visibleOne = markers.getVisibleParent(marker);
                                setCookie('tac',each_tac,1);
                                var a = "<a href='../lte/level2' >详细</a>";
                                var d = "<p>"+each_tac+"<br />"+a+" </p>";
                                popup.setContent(d);
                                popup.setLatLng(visibleOne.getLatLng());
                                map.openPopup(popup);
                            });
                            markers.on('click', function (a) {
                                var visibleOne = markers.getVisibleParent(marker);
                                setCookie('tac',each_tac,1);
                                var a = "<a href='../lte/level2' >详细</a>";
                                var d = "<p>"+each_tac+"<br />"+a+" </p>";
                                popup.setContent(d);
                                popup.setLatLng(visibleOne.getLatLng());
                                map.openPopup(popup);
                            });
                            pop.put(each_tac,marker);
                            tac = each_tac;
                        } else {
                            var marker = L.marker(new L.LatLng(latitude, longitude));
                            markers.addLayer(marker);
                        }
                    }



                            // if (tac != each_tac) {
                            //     all_tac_array.push(one_tac_array);
                            //     tac = each_tac;
                            //     one_tac_array = new Array();
                            //     one_tac_array.push([latitude,longitude,each_tac,status]);
                            // } else {
                            //     one_tac_array.push([latitude, longitude, each_tac, status]);
                            // }

                });
            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });


    // for (var i = 0; i < all_tac_array.length; i++) {
    //     var a = all_tac_array[i];
    //     var markers = L.markerClusterGroup({
    //         maxClusterRadius: 120,
    //         disableClusteringAtZoom:12,
    //         iconCreateFunction: function(cluster) {
    //             var markers = cluster.getAllChildMarkers();
    //             return L.divIcon({
    //                 html: "<div><span>" + "xxx" + "</span></div>",
    //                 className: "mycluster mycluster-cluster-level0",
    //                 iconSize: new L.Point(40, 40)
    //             });
    //         },
    //         spiderfyOnMaxZoom: false, showCoverageOnHover: true, zoomToBoundsOnClick: false,singleMarkerMode:true
    //     });
    //     for (var j = 0; j < a.length; j++) {
    //         var b = a[j];
    //         tac_title = b[2];
    //         tac_status_for_map = b[3];
    //         var marker = L.marker(new L.LatLng(b[0], b[1]),{ title: b[2] });
    //         markers.addLayer(marker);
    //     }
    //     map.addLayer(markers);
    // }

    loading('stop');
}
function createMarkerClusterGroup (e,t,x) {
    if (e == 0){
        return L.markerClusterGroup({
            maxClusterRadius: x,
            disableClusteringAtZoom:16,
            spiderfyOnMaxZoom: false, showCoverageOnHover: true, zoomToBoundsOnClick: false,singleMarkerMode:true,
            iconCreateFunction: function(cluster) {
                return L.divIcon({
                    html: "<div><span>" + t + "</span></div>",
                    className: "mycluster mycluster-cluster-level0",
                    iconSize: new L.Point(40, 40)
                });
            }
        })
    } else if(e == 3){
        return L.markerClusterGroup({
            maxClusterRadius: x,
            disableClusteringAtZoom:16,
            spiderfyOnMaxZoom: false, showCoverageOnHover: true, zoomToBoundsOnClick: false,singleMarkerMode:true,
            iconCreateFunction: function(cluster) {
                return L.divIcon({
                    html: "<div><span>" + t + "</span></div>",
                    className: "mycluster mycluster-cluster-level1",
                    iconSize: new L.Point(40, 40)
                });
            }
        })
    }else if(e == 2){
        return L.markerClusterGroup({
            maxClusterRadius: x,
            disableClusteringAtZoom:16,
            spiderfyOnMaxZoom: false, showCoverageOnHover: true, zoomToBoundsOnClick: false,singleMarkerMode:true,
            iconCreateFunction: function(cluster) {
                return L.divIcon({
                    html: "<div><span>" + t + "</span></div>",
                    className: "mycluster mycluster-cluster-level2",
                    iconSize: new L.Point(40, 40)
                });
            }
        })
    }else if(e == 1){
        return L.markerClusterGroup({
            maxClusterRadius: x,
            disableClusteringAtZoom:16,
            spiderfyOnMaxZoom: false, showCoverageOnHover: true, zoomToBoundsOnClick: false,singleMarkerMode:true,
            iconCreateFunction: function(cluster) {
                return L.divIcon({
                    html: "<div><span>" + t + "</span></div>",
                    className: "mycluster mycluster-cluster-level3",
                    iconSize: new L.Point(40, 40)
                });
            }
        })
    }


}
/*跳转页面*/
function goto(obj)
{
    var id = $(obj).parent().attr("id");
    setCookie('tac',id,1);
    setCookie('keyword',$('#input_search').val(),1);
    window.location.href = "../lte/level2";
}
/*搜索*/
function input_search()
{
    $('#accordion').children().remove();
    var keyword = $('#input_search').val();
    var data = {'keyword':keyword , 'tac':role_tac};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/search_group_station",
        data:JSON.stringify(data),
        dataType:'json',
        async:false,
        success:function(response){
            var str = '';
            if(response.status)
            {
                $.each(response.data, function(i,ite) {
                    arr = new Array();

                    var t_RRC_Setup_Succ_Rate = ite.RRC_Setup_Succ_Rate;
                    if(t_RRC_Setup_Succ_Rate > 100){t_RRC_Setup_Succ_Rate = 100;}
                    if(t_RRC_Setup_Succ_Rate < 0){t_RRC_Setup_Succ_Rate = 0;}
                    var RRC_Setup_Succ_Rate   = kpi('RRC_setup_succ_Rate', t_RRC_Setup_Succ_Rate);

                    var t_S1_Setup_Succ_Rate = ite.S1_Setup_Succ_Rate;
                    if(t_S1_Setup_Succ_Rate > 100){t_S1_Setup_Succ_Rate = 100;}
                    if(t_S1_Setup_Succ_Rate < 0){t_S1_Setup_Succ_Rate = 0;}
                    var S1_Setup_Succ_Rate   = kpi('S1_Setup_Succ_Rate', t_S1_Setup_Succ_Rate);

                    var t_ERAB_Setup_Succ_Rate = ite.ERAB_Setup_Succ_Rate;
                    if(t_ERAB_Setup_Succ_Rate > 100){t_ERAB_Setup_Succ_Rate = 100;}
                    if(t_ERAB_Setup_Succ_Rate < 0){t_ERAB_Setup_Succ_Rate = 0;}
                    var ERAB_Setup_Succ_Rate   = kpi('ERAB_Setup_Succ_Rate', t_ERAB_Setup_Succ_Rate);

                    var t_paging_Succ_Rate = ite.paging_Succ_Rate;
                    if(t_paging_Succ_Rate > 100){t_paging_Succ_Rate = 100;}
                    if(t_paging_Succ_Rate < 0){t_paging_Succ_Rate = 100;}
                    var paging_Succ_Rate   = kpi('paging_Succ_Rate', t_paging_Succ_Rate);

                    var t_Cell_Utilization_Rate = ite.Cell_Utilization_Rate;
                    if(t_Cell_Utilization_Rate > 100){t_Cell_Utilization_Rate = 100;}
                    if(t_Cell_Utilization_Rate < 0){t_Cell_Utilization_Rate = 0;}
                    var Cell_Utilization_Rate 	= kpi('Cell_Utilization_Rate', t_Cell_Utilization_Rate);

                    var t_Within_System_HO_Succ_Rate = ite.Within_System_HO_Succ_Rate;
                    if(t_Within_System_HO_Succ_Rate > 100){t_Within_System_HO_Succ_Rate = 100;}
                    if(t_Within_System_HO_Succ_Rate < 0){t_Within_System_HO_Succ_Rate = 0;}
                    var Within_System_HO_Succ_Rate   = kpi('Within_System_HO_Succ_Rate', t_Within_System_HO_Succ_Rate);

                    var t_Erab_Drop_Rate = ite.Erab_Drop_Rate;
                    if(t_Erab_Drop_Rate > 100){t_Erab_Drop_Rate = 100;}
                    if(t_Erab_Drop_Rate < 0){t_Erab_Drop_Rate = 0;}
                    var Erab_Drop_Rate   = kpi('Erab_Drop_Rate', t_Erab_Drop_Rate);

                    var t_DL_PRB_Occupy_Rate = ite.DL_PRB_Occupy_Rate;
                    if(t_DL_PRB_Occupy_Rate > 100){t_DL_PRB_Occupy_Rate = 100;}
                    if(t_DL_PRB_Occupy_Rate < 0){t_DL_PRB_Occupy_Rate = 0;}
                    var DL_PRB_Occupy_Rate   = kpi('DL_PRB_Occupy_Rate', t_DL_PRB_Occupy_Rate);

                    var t_UL_PRB_Occupy_Rate = ite.UL_PRB_Occupy_Rate;
                    if(t_UL_PRB_Occupy_Rate > 100){t_UL_PRB_Occupy_Rate = 100;}
                    if(t_UL_PRB_Occupy_Rate < 0){t_UL_PRB_Occupy_Rate = 0;}
                    var UL_PRB_Occupy_Rate   = kpi('UL_PRB_Occupy_Rate', t_UL_PRB_Occupy_Rate);

                    var Dl_ThroughputRate_Mbps 	   = kpi('Dl_ThroughputRate_Mbps', ite.Dl_ThroughputRate_Mbps);
                    var Ul_ThroughputRate_Mbps 	   = kpi('Ul_ThroughputRate_Mbps', ite.Ul_ThroughputRate_Mbps);
                    var DL_User_Speed_Mbps 		   = kpi('DL_User_Speed_Mbps', ite.DL_User_Speed_Mbps);
                    var UL_User_Speed_Mbps 		   = kpi('UL_User_Speed_Mbps', ite.UL_User_Speed_Mbps);
                    var UE_SessionTime 			   = kpi('UE_SessionTime', ite.UE_SessionTime);
                    var Drb_pmSessionTime 		   = kpi('Drb_pmSessionTime', ite.Drb_pmSessionTime);
                    var DL_Active_User_Num 		   = kpi('DL_Active_User_Num', ite.DL_Active_User_Num);
                    var UL_Active_User_Num 		   = kpi('UL_Active_User_Num', ite.UL_Active_User_Num);
                    arr.sort();
                    var status = get_max(arr[0], arr[arr.length - 1]);
                    if(tac_status == '')
                    {
                        tac_status = ite.tac + '|' + status;
                    }
                    else
                    {
                        tac_status = tac_status + ',' + ite.tac + '|' + status;
                    }
                    var type   = '';
                    var tac_status_cube = '';
                    if(status == 0)
                    {
                        type   = '<font color="#06fa06">正常</font>';
                    }
                    else if(status == 1)
                    {
                        type   = '<font color="#f29f00">异常</font>';
                    }
                    else if(status == 2)
                    {
                        type   = '<font color="#cc00ff">异常</font>';
                    }
                    else if(status == 3)
                    {
                        type   = '<font color="#fa0606">异常</font>';
                    }
                    if(
                        ite.RRC_Setup_Succ_Rate == '0.0' &&
                        ite.S1_Setup_Succ_Rate == '0.0' &&
                        ite.ERAB_Setup_Succ_Rate == '0.0' &&
                        ite.paging_Succ_Rate == '0.0' &&
                        ite.Cell_Utilization_Rate == '0.0' &&
                        (ite.Within_System_HO_Succ_Rate == '0.0' || ite.Within_System_HO_Succ_Rate == '100') &&
                        ite.Erab_Drop_Rate == '0.0' &&
                        ite.DL_PRB_Occupy_Rate == '0.0' &&
                        ite.UL_PRB_Occupy_Rate == '0.0' &&
                        ite.Dl_ThroughputRate_Mbps == '0.0' &&
                        ite.Ul_ThroughputRate_Mbps == '0.0' &&
                        ite.DL_User_Speed_Mbps == '0.0' &&
                        ite.UL_User_Speed_Mbps == '0.0' &&
                        ite.UE_SessionTime == '0.0' &&
                        ite.Drb_pmSessionTime == '0.0' &&
                        ite.DL_Active_User_Num == '0.0' &&
                        ite.UL_Active_User_Num == '0.0'
                    ){
                        RRC_Setup_Succ_Rate   	   = 'N/A';
                        S1_Setup_Succ_Rate    	   = 'N/A';
                        ERAB_Setup_Succ_Rate  	   = 'N/A';
                        paging_Succ_Rate      	   = 'N/A';
                        Cell_Utilization_Rate 	   = 'N/A';
                        Within_System_HO_Succ_Rate = 'N/A';
                        Erab_Drop_Rate             = 'N/A';
                        DL_PRB_Occupy_Rate 		   = 'N/A';
                        UL_PRB_Occupy_Rate 		   = 'N/A';
                        Dl_ThroughputRate_Mbps 	   = 'N/A';
                        Ul_ThroughputRate_Mbps 	   = 'N/A';
                        DL_User_Speed_Mbps 		   = 'N/A';
                        UL_User_Speed_Mbps 		   = 'N/A';
                        UE_SessionTime 			   = 'N/A';
                        Drb_pmSessionTime 		   = 'N/A';
                        DL_Active_User_Num 		   = 'N/A';
                        UL_Active_User_Num 		   = 'N/A';
                    }
                    var str2 = "<li> " +
                        "<div class='link' id='" + ite.tac +
                        "'> " +
                        "<div style='display: inline-block; width: 70%'>"+ite.tac+"</div>" +
                        "<div style='display: inline-block; width: 20%'>"+type+"</div>" +
                        "<div class='details' style='display: inline-block;height:100%' onclick='goto(this)';></div>" +
                        "</div> " +
                        "<ul class='submenu'> " +
                        "<li> " +
                        "<div style='float: left;'>参数</div> " +
                        "<div style='float: right; '>值&nbsp;&nbsp;&nbsp;&nbsp;</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>更新时间</div> " +
                        "<div style='float: right; color:#06fa06'>"+update_time+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>RRC_Setup_Succ_Rate(%)</br>RRC建立成功率</div> " +
                        "<div style='float: right; '>"+RRC_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>S1_Setup_Succ_Rate(%)</br>S1链路建立成功率</div> " +
                        "<div style='float: right; '>"+S1_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>ERAB_Setup_Succ_Rate(%)</br>ERAB建立成功率</div> " +
                        "<div style='float: right; '>"+ERAB_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>paging_Succ_Rate(%)</br>寻呼成功率</div> " +
                        "<div style='float: right; '>"+paging_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Erab_Drop_Rate(%)</br>E-RAB掉线率</div> " +
                        "<div style='float: right;'>"+Erab_Drop_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Within_System_HO_Succ_Rate(%)</br>系统内切换成功率</div> " +
                        "<div style='float: right; '>"+Within_System_HO_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Cell_Utilization_Rate(%)</br>小区利用率</div> " +
                        "<div style='float: right; '>"+Cell_Utilization_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_PRB_Occupy_Rate(%)</br>上行物理资源占用率</div> " +
                        "<div style='float: right; '>"+UL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_PRB_Occupy_Rate(%)</br>下行物理资源占用率</div> " +
                        "<div style='float: right; '>"+DL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Dl_ThroughputRate_Mbps(Mbps)</br>下行吞吐率</div> " +
                        "<div style='float: right; '>"+Dl_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Ul_ThroughputRate_Mbps(Mbps)</br>上行吞吐率</div> " +
                        "<div style='float: right; '>"+Ul_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_User_Speed_Mbps(Mbps)</br>下行用户速率</div> " +
                        "<div style='float: right; '>"+DL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_User_Speed_Mbps(Mbps)</br>上行用户速率</div> " +
                        "<div style='float: right; '>"+UL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UE_SessionTime(ms)</br>用户会话时长</div> " +
                        "<div style='float: right; '>"+UE_SessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Drb_pmSessionTime(ms)</br>专用无线承载会话时长</div> " +
                        "<div style='float: right; '>"+Drb_pmSessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_Active_User_Num</br>下行活动用户数</div> " +
                        "<div style='float: right; '>"+DL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_Active_User_Num</br>上行活动用户数</div> " +
                        "<div style='float: right; '>"+UL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "</ul> " +
                        "</li>";
                    $('#accordion').append(str2);
                });
            }
            loadJs();
            locate();
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}

function locate() {
    var x ="";
    $(".link").click(function(){
        var id = $(this).attr("id");
        if (x == id){
            var popup = pop.get('popup');
            map.closePopup(popup);
            x = "";
        }else {
            x = id;
            var popup = new L.Popup({closeButton: false});
            var marker = pop.get(id);
            var visibleOne = markers.getVisibleParent(marker);
            setCookie('tac',id,1);
            var a = "<a href='../lte/level2' >详细</a>";
            var d = "<p>"+id+"<br />"+a+" </p>";
            popup.setContent(d);
            popup.setLatLng(visibleOne.getLatLng());
            map.openPopup(popup);
            pop.put('popup',popup);
            map.setView(visibleOne.getLatLng(),11);
        }
    });
}

// $(function() {
//     locate();
// });

function get_user_important() {
    var important_tacs ='';
    var important_nodes ='';
    var data = {userId : user_id};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/get_user_important",
        data:JSON.stringify(data),
        dataType:'json',
        async:false,
        success:function(response){
            var important_tac = response.data.important_tac;
            var important_node = response.data.important_node;
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
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
    var info={"important_tacs":important_tacs,"important_nodes":important_nodes};
    return info;
}
function init_important_node() {
    if((important_nodes.length == 1)&&(important_nodes[0]=='')){
        var str = "<li> " +
            "<div class='link_left'> " +
            "未设置重点关注NODE"+
            "</div> " +
            "</li>";
        $('#node').append(str);
    }
    var node = '';
    for (m = 0; m < important_nodes.length; m++) {
        if (node != ''){
            if (important_nodes[m]!='') {
                node += (",'" + important_nodes[m] + "'");
            }
        }else {
            node ="'"+ important_nodes[m] + "'";
        }
    }
    var data = {"enbName":node};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"POST",
        url:"../lte/get_important_node",
        data:JSON.stringify(data),
        dataType:'json',
        // async:false,
        error:function(e){
            console.log(e.responseText);
        },
        success:function(response){
            var str = '';
            if(response.status)
            {
                $.each(response.data, function(i,ite) {
                    arr = new Array();

                    var t_RRC_Setup_Succ_Rate = ite.RRC_Setup_Succ_Rate;
                    if(t_RRC_Setup_Succ_Rate > 100){t_RRC_Setup_Succ_Rate = 100;}
                    if(t_RRC_Setup_Succ_Rate < 0){t_RRC_Setup_Succ_Rate = 0;}
                    var RRC_Setup_Succ_Rate   = kpi_node('RRC_setup_succ_Rate', t_RRC_Setup_Succ_Rate);

                    var t_S1_Setup_Succ_Rate = ite.S1_Setup_Succ_Rate;
                    if(t_S1_Setup_Succ_Rate > 100){t_S1_Setup_Succ_Rate = 100;}
                    if(t_S1_Setup_Succ_Rate < 0){t_S1_Setup_Succ_Rate = 0;}
                    var S1_Setup_Succ_Rate   = kpi_node('S1_Setup_Succ_Rate', t_S1_Setup_Succ_Rate);

                    var t_ERAB_Setup_Succ_Rate = ite.ERAB_Setup_Succ_Rate;
                    if(t_ERAB_Setup_Succ_Rate > 100){t_ERAB_Setup_Succ_Rate = 100;}
                    if(t_ERAB_Setup_Succ_Rate < 0){t_ERAB_Setup_Succ_Rate = 0;}
                    var ERAB_Setup_Succ_Rate   = kpi_node('ERAB_Setup_Succ_Rate', t_ERAB_Setup_Succ_Rate);

                    var t_paging_Succ_Rate = ite.paging_Succ_Rate;
                    if(t_paging_Succ_Rate > 100){t_paging_Succ_Rate = 100;}
                    if(t_paging_Succ_Rate < 0){t_paging_Succ_Rate = 100;}
                    var paging_Succ_Rate   = kpi_node('paging_Succ_Rate', t_paging_Succ_Rate);

                    var t_Cell_Utilization_Rate = ite.Cell_Utilization_Rate;
                    if(t_Cell_Utilization_Rate > 100){t_Cell_Utilization_Rate = 100;}
                    if(t_Cell_Utilization_Rate < 0){t_Cell_Utilization_Rate = 0;}
                    var Cell_Utilization_Rate 	= kpi_node('Cell_Utilization_Rate', t_Cell_Utilization_Rate);

                    var t_Within_System_HO_Succ_Rate = ite.Within_System_HO_Succ_Rate;
                    if(t_Within_System_HO_Succ_Rate > 100){t_Within_System_HO_Succ_Rate = 100;}
                    if(t_Within_System_HO_Succ_Rate < 0){t_Within_System_HO_Succ_Rate = 0;}
                    var Within_System_HO_Succ_Rate   = kpi_node('Within_System_HO_Succ_Rate', t_Within_System_HO_Succ_Rate);

                    var t_Erab_Drop_Rate = ite.Erab_Drop_Rate;
                    if(t_Erab_Drop_Rate > 100){t_Erab_Drop_Rate = 100;}
                    if(t_Erab_Drop_Rate < 0){t_Erab_Drop_Rate = 0;}
                    var Erab_Drop_Rate   = kpi_node('Erab_Drop_Rate', t_Erab_Drop_Rate);

                    var t_DL_PRB_Occupy_Rate = ite.DL_PRB_Occupy_Rate;
                    if(t_DL_PRB_Occupy_Rate > 100){t_DL_PRB_Occupy_Rate = 100;}
                    if(t_DL_PRB_Occupy_Rate < 0){t_DL_PRB_Occupy_Rate = 0;}
                    var DL_PRB_Occupy_Rate   = kpi_node('DL_PRB_Occupy_Rate', t_DL_PRB_Occupy_Rate);

                    var t_UL_PRB_Occupy_Rate = ite.UL_PRB_Occupy_Rate;
                    if(t_UL_PRB_Occupy_Rate > 100){t_UL_PRB_Occupy_Rate = 100;}
                    if(t_UL_PRB_Occupy_Rate < 0){t_UL_PRB_Occupy_Rate = 0;}
                    var UL_PRB_Occupy_Rate   = kpi_node('UL_PRB_Occupy_Rate', t_UL_PRB_Occupy_Rate);

                    var Dl_ThroughputRate_Mbps 	   = kpi_node('Dl_ThroughputRate_Mbps', ite.Dl_ThroughputRate_Mbps);
                    var Ul_ThroughputRate_Mbps 	   = kpi_node('Ul_ThroughputRate_Mbps', ite.Ul_ThroughputRate_Mbps);
                    var DL_User_Speed_Mbps 		   = kpi_node('DL_User_Speed_Mbps', ite.DL_User_Speed_Mbps);
                    var UL_User_Speed_Mbps 		   = kpi_node('UL_User_Speed_Mbps', ite.UL_User_Speed_Mbps);
                    var UE_SessionTime 			   = kpi_node('UE_SessionTime', ite.UE_SessionTime);
                    var Drb_pmSessionTime 		   = kpi_node('Drb_pmSessionTime', ite.Drb_pmSessionTime);
                    var DL_Active_User_Num 		   = kpi_node('DL_Active_User_Num', ite.DL_Active_User_Num);
                    var UL_Active_User_Num 		   = kpi_node('UL_Active_User_Num', ite.UL_Active_User_Num);
                    arr.sort();
                    var status = get_max(arr[0], arr[arr.length - 1]);
                    if (ite.status == null){
                        status = 4;
                    }
                    var type   = '';
                    var tac_status_cube = '';
                    if(status == 0)
                    {
                        type   = '<font color="#06fa06">正常</font>';
                        tac_status_cube = "<div style='float: left;background:#06fa06;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    else if(status == 1)
                    {
                        type   = '<font color="#f29f00">异常</font>';
                        tac_status_cube = "<div style='float: left;background:#f29f00;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    else if(status == 2)
                    {
                        type   = '<font color="#cc00ff">异常</font>';
                        tac_status_cube = "<div style='float: left;background:#cc00ff;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    else if(status == 3)
                    {
                        type   = '<font color="#fa0606">异常</font>';
                        tac_status_cube = "<div style='float: left;background:#fa0606;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    else if(status == 4)
                    {
                        type   = '<font color="black">异常</font>';
                        tac_status_cube = "<div style='float: left;background:black;height: 15px;width: 15px;top: 2.5px'></div>"
                    }
                    if(
                        ite.RRC_Setup_Succ_Rate == '0.0' &&
                        ite.S1_Setup_Succ_Rate == '0.0' &&
                        ite.ERAB_Setup_Succ_Rate == '0.0' &&
                        ite.paging_Succ_Rate == '0.0' &&
                        ite.Cell_Utilization_Rate == '0.0' &&
                        (ite.Within_System_HO_Succ_Rate == '0.0' || ite.Within_System_HO_Succ_Rate == '100') &&
                        ite.Erab_Drop_Rate == '0.0' &&
                        ite.DL_PRB_Occupy_Rate == '0.0' &&
                        ite.UL_PRB_Occupy_Rate == '0.0' &&
                        ite.Dl_ThroughputRate_Mbps == '0.0' &&
                        ite.Ul_ThroughputRate_Mbps == '0.0' &&
                        ite.DL_User_Speed_Mbps == '0.0' &&
                        ite.UL_User_Speed_Mbps == '0.0' &&
                        ite.UE_SessionTime == '0.0' &&
                        ite.Drb_pmSessionTime == '0.0' &&
                        ite.DL_Active_User_Num == '0.0' &&
                        ite.UL_Active_User_Num == '0.0'
                    ){
                        RRC_Setup_Succ_Rate   	   = 'N/A';
                        S1_Setup_Succ_Rate    	   = 'N/A';
                        ERAB_Setup_Succ_Rate  	   = 'N/A';
                        paging_Succ_Rate      	   = 'N/A';
                        Cell_Utilization_Rate 	   = 'N/A';
                        Within_System_HO_Succ_Rate = 'N/A';
                        Erab_Drop_Rate             = 'N/A';
                        DL_PRB_Occupy_Rate 		   = 'N/A';
                        UL_PRB_Occupy_Rate 		   = 'N/A';
                        Dl_ThroughputRate_Mbps 	   = 'N/A';
                        Ul_ThroughputRate_Mbps 	   = 'N/A';
                        DL_User_Speed_Mbps 		   = 'N/A';
                        UL_User_Speed_Mbps 		   = 'N/A';
                        UE_SessionTime 			   = 'N/A';
                        Drb_pmSessionTime 		   = 'N/A';
                        DL_Active_User_Num 		   = 'N/A';
                        UL_Active_User_Num 		   = 'N/A';
                    }
                    if(
                        ite.RRC_Setup_Succ_Rate == null &&
                        ite.S1_Setup_Succ_Rate == null &&
                        ite.ERAB_Setup_Succ_Rate == null &&
                        ite.paging_Succ_Rate == null &&
                        ite.Cell_Utilization_Rate == null &&
                        (ite.Within_System_HO_Succ_Rate == null) &&
                        ite.Erab_Drop_Rate == null &&
                        ite.DL_PRB_Occupy_Rate == null &&
                        ite.UL_PRB_Occupy_Rate == null &&
                        ite.Dl_ThroughputRate_Mbps == null &&
                        ite.Ul_ThroughputRate_Mbps == null &&
                        ite.DL_User_Speed_Mbps == null &&
                        ite.UL_User_Speed_Mbps == null &&
                        ite.UE_SessionTime == null &&
                        ite.Drb_pmSessionTime == null &&
                        ite.DL_Active_User_Num == null &&
                        ite.UL_Active_User_Num == null
                    ){
                        RRC_Setup_Succ_Rate   	   = '无法获取';
                        S1_Setup_Succ_Rate    	   = '无法获取';
                        ERAB_Setup_Succ_Rate  	   = '无法获取';
                        paging_Succ_Rate      	   = '无法获取';
                        Cell_Utilization_Rate 	   = '无法获取';
                        Within_System_HO_Succ_Rate = '无法获取';
                        Erab_Drop_Rate             = '无法获取';
                        DL_PRB_Occupy_Rate 		   = '无法获取';
                        UL_PRB_Occupy_Rate 		   = '无法获取';
                        Dl_ThroughputRate_Mbps 	   = '无法获取';
                        Ul_ThroughputRate_Mbps 	   = '无法获取';
                        DL_User_Speed_Mbps 		   = '无法获取';
                        UL_User_Speed_Mbps 		   = '无法获取';
                        UE_SessionTime 			   = '无法获取';
                        Drb_pmSessionTime 		   = '无法获取';
                        DL_Active_User_Num 		   = '无法获取';
                        UL_Active_User_Num 		   = '无法获取';
                    }
                    if(ite.enb_id==null){
                        ite.enb_id = '无法获取';
                    }
                    if(ite.swversion==null){
                        ite.swversion = '无法获取';
                    }
                    if(ite.ip==null){
                        ite.ip = '无法获取';
                    }
                    if(ite.mme==null){
                        ite.mme = '无法获取';
                    }
                    if(ite.license_user==null){
                        ite.license_user = '无法获取';
                    }
                    if(ite.enb_name==null){
                        ite.enb_name = '无法获取';
                    }
                    if(ite.name==null){
                        ite.name = '无法获取';
                    }
                    if(ite.format==null){
                        ite.format = '无法获取';
                    }
                    if(ite.type==null){
                        ite.type = '';
                    }
                    if(ite.latitude==null){
                        ite.latitude = '无法获取';
                    }
                    if(ite.longitude==null){
                        ite.longitude = '无法获取';
                    }
                    var data = "data-name='"+ite.name+
                        "' data-latitude='"+ite.latitude+
                        "' data-longitude='"+ite.longitude+
                        "' data-type='"+ite.type+
                        "' data-format='"+ite.format+
                        "' data-enb_name='"+ite.enb_name+
                        "' data-status='"+status+

                        "' data-enb_id='"+ite.enb_id+
                        "' data-swversion='"+ite.swversion+
                        "' data-ip='"+ite.ip+
                        "' data-mme='"+ite.mme+
                        "' data-license_user='"+ite.license_user+

                        "'";
                    var str2 = "<li> " +
                        "<div class='link_left' id='" + ite.enb_name +
                        "' onclick='open_submenu(this)'> " +
                        "<div style='float: left;width: 86%'>"+ite.enb_name+"</div>" +
                        tac_status_cube +
                        "</div> " +
                        "<ul class='submenu'> " +
                        "<li> " +
                        "<div style='float: left;'>参数</div> " +
                        "<div style='float: right; '>值&nbsp;&nbsp;&nbsp;&nbsp;</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>更新时间</div> " +
                        "<div style='float: right;'>"+update_time+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>ENodeB Name</div> " +
                        "<div style='float: right;'>"+ite.enb_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>基站名称</div> " +
                        "<div style='float: right;'>"+ite.name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>经度</div> " +
                        "<div style='float: right;'>"+ite.longitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>纬度</div> " +
                        "<div style='float: right;'>"+ite.latitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>enb_id</div> " +
                        "<div style='float: right;'>"+ite.enb_id+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>swversion</div> " +
                        "<div style='float: right;'>"+ite.swversion+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>ip</div> " +
                        "<div style='float: right;'>"+ite.ip+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>mme</div> " +
                        "<div style='float: right;'>"+ite.mme+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>license_user</div> " +
                        "<div style='float: right;'>"+ite.license_user+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>RRC_Setup_Succ_Rate(%)</br>RRC建立成功率</div> " +
                        "<div style='float: right; '>"+RRC_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>S1_Setup_Succ_Rate(%)</br>S1链路建立成功率</div> " +
                        "<div style='float: right; '>"+S1_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>ERAB_Setup_Succ_Rate(%)</br>ERAB建立成功率</div> " +
                        "<div style='float: right; '>"+ERAB_Setup_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>paging_Succ_Rate(%)</br>寻呼成功率</div> " +
                        "<div style='float: right; '>"+paging_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Erab_Drop_Rate(%)</br>E-RAB掉线率</div> " +
                        "<div style='float: right;'>"+Erab_Drop_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Within_System_HO_Succ_Rate(%)</br>系统内切换成功率</div> " +
                        "<div style='float: right; '>"+Within_System_HO_Succ_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Cell_Utilization_Rate(%)</br>小区利用率</div> " +
                        "<div style='float: right; '>"+Cell_Utilization_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_PRB_Occupy_Rate(%)</br>上行物理资源占用率</div> " +
                        "<div style='float: right; '>"+UL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_PRB_Occupy_Rate(%)</br>下行物理资源占用率</div> " +
                        "<div style='float: right; '>"+DL_PRB_Occupy_Rate+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Dl_ThroughputRate_Mbps(Mbps)</br>下行吞吐率</div> " +
                        "<div style='float: right; '>"+Dl_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Ul_ThroughputRate_Mbps(Mbps)</br>上行吞吐率</div> " +
                        "<div style='float: right; '>"+Ul_ThroughputRate_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_User_Speed_Mbps(Mbps)</br>下行用户速率</div> " +
                        "<div style='float: right; '>"+DL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_User_Speed_Mbps(Mbps)</br>上行用户速率</div> " +
                        "<div style='float: right; '>"+UL_User_Speed_Mbps+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UE_SessionTime(ms)</br>用户会话时长</div> " +
                        "<div style='float: right; '>"+UE_SessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>Drb_pmSessionTime(ms)</br>专用无线承载会话时长</div> " +
                        "<div style='float: right; '>"+Drb_pmSessionTime+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>DL_Active_User_Num</br>下行活动用户数</div> " +
                        "<div style='float: right; '>"+DL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>UL_Active_User_Num</br>上行活动用户数</div> " +
                        "<div style='float: right; '>"+UL_Active_User_Num+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "</ul> " +
                        "</li>";
                    $('#node').append(str2);
                });
                $('.loading').remove();
                // $('#important_node').click();
            }
        }
    });
}
/*kpi比较*/
function kpi_node(quota_name, quota_val)
{
    var status = '';
    var temp = '';
    $.each(quota_threshold_node, function(i,ite) {
        if(ite.quota_name == quota_name)
        {
            var threshold_1 = ite.threshold_1;
            var threshold_2 = ite.threshold_2;
            var threshold_3 = ite.threshold_3;
            if(ite.quota_unit == 0)
            {
                quota_val   = parseFloat(Number(quota_val));
                threshold_1 = parseFloat(Number(threshold_1));
                threshold_2 = parseFloat(Number(threshold_2));
                threshold_3 = parseFloat(Number(threshold_3));
            }
            if(ite.quota_unit == 1)
            {
                quota_val   = parseInt(Number(quota_val));
                threshold_1 = parseInt(Number(threshold_1));
                threshold_2 = parseInt(Number(threshold_2));
                threshold_3 = parseInt(Number(threshold_3));
            }
            //小于
            if(ite.quota_type == 0) {
                if(quota_val <= threshold_3) {
                    status = 3;
                }else if(quota_val <= threshold_2) {
                    status = 2;
                }else if(quota_val <= threshold_1) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
            //大于
            if(ite.quota_type == 1) {
                if(quota_val >= threshold_3) {
                    status = 3;
                }else if(quota_val >= threshold_2) {
                    status = 2;
                }else if(quota_val >= threshold_1) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
            arr.push(status);
            if(status == 0)
            {
                temp = '<font color="#06fa06">'+quota_val+'</font>';
            }
            else if(status == 1)
            {
                temp = '<font color="#f29f00">'+quota_val+'</font>';
            }
            else if(status == 2)
            {
                temp = '<font color="#cc00ff">'+quota_val+'</font>';
            }
            else if(status == 3)
            {
                temp = '<font color="#fa0606">'+quota_val+'</font>';
            }
        }
    });
    return temp;
}
var x = '';
function open_submenu(obj) {
    var id = $(obj).attr("id");
    var $el = $(obj).parent().parent();
    $this = $(obj),
        $next = $this.next();
    $next.slideToggle();
    $this.parent().toggleClass('open');
    if (x == id){
        $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        x = "";
    }else {
        x = id;
        $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
    }
}

function check_select_net() {
    var x = $("#sel").val();
    if (x == 'LTE'){

    }else if(x == 'WCDMA'){

    }else {

    }

}