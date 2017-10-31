/**
 * Created by a7289 on 2016/12/13 0013.
 */
/*检查并获取登录信息*/
check_login();
user_id  = getCookie('user_id');
/*显示用户名称*/
show_username();

$("#alarm_list").hide();
$("#cell_list").hide();
$("#bg").hide();
var cell_name = new Array();
var cell_status = new Array();
var station  = getCookie('station');
tac       	 = getStrParam(station, 'tac');
update_time  = getStrParam(station, 'update_time');
name         = getStrParam(station, 'name');
latitude     = getStrParam(station, 'latitude');
longitude    = getStrParam(station, 'longitude');
type         = getStrParam(station, 'type');
format       = getStrParam(station, 'format');
enb_name     = getStrParam(station, 'enb_name');
// status       = getStrParam(station, 'status');
status       = 0;
enb_id		 = getStrParam(station, 'enb_id');
swversion	 = getStrParam(station, 'swversion');
license_user = getStrParam(station, 'license_user');
mme		  	 = getStrParam(station, 'mme');
ip		  	 = getStrParam(station, 'ip');
if(enb_id==null||enb_id==''){
    enb_id = '无法获取';
}
if(swversion==null||swversion==''){
    swversion = '无法获取';
}
if(ip==null||ip==''){
    ip = '无法获取';
}
if(mme==null||mme==''){
    mme = '无法获取';
}
if(license_user==null||license_user==''){
    license_user = '无法获取';
}
if(enb_name==null||enb_name==''){
    enb_name = '无法获取';
}
if(name==null||name==''){
    name = '无法获取';
}
if(format==null||format==''){
    format = '无法获取';
}
if(type==null||type==''){
    type = '无法获取';
}
if(latitude==null||latitude==''){
    latitude = '无法获取';
}
if(longitude==null||longitude==''){
    longitude = '无法获取';
}

quota_threshold_cell = '';
/*获得cell门限*/
get_cell_quota_threshold();
/*获得更新时间*/
get_update_time();
/*获得cell的详细信息*/
init_cell_data();
/*加载地图*/
draw_map();




/*获得cell门限*/
function get_cell_quota_threshold()
{
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"get",
        url:"../lte/get_cell_quota_threshold",
        dataType:'json',
        async:false,
        success:function(response){
            //	console.log(response.data);
            quota_threshold_cell = response.data;
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
}
function kpi(quota_name, quota_val)
{
    var status ='';
    var temp = '';
    $.each(quota_threshold_cell, function(i,ite) {
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
/*获取cell的详细信息数据*/
function init_cell_data()
{
    var data = {'nodeName':enb_name,'order':'desc'};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"POST",
        url:"../lte/get_cell_by_node",
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
                    var status_cell = get_max(arr[0], arr[arr.length - 1]);
                    if (ite.status == null){
                        status_cell = 4;
                    }
                    var type   = '';
                    if(status_cell == 0)
                    {
                        type   = '<font color="#06fa06">正常</font>';
                    }
                    else if(status_cell == 1)
                    {
                        type   = '<font color="#f29f00">异常：告警级别 一 级</font>';
                        cell_name.push(ite.cell_name);
                        cell_status.push('<font color="#f29f00">一级告警</font>');
                    }
                    else if(status_cell == 2)
                    {
                        type   = '<font color="#cc00ff">异常：告警级别 二 级</font>';
                        cell_name.push(ite.cell_name);
                        cell_status.push('<font color="#cc00ff">二级告警级别</font>');
                    }
                    else if(status_cell == 3)
                    {
                        type   = '<font color="#fa0606">异常：告警级别 三 级</font>';
                        cell_name.push(ite.cell_name);
                        cell_status.push('<font color="#fa0606">三级告警</font>');
                    }
                    if (status_cell> status){
                        status = status_cell;
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
                    if(enb_id==null){
                        enb_id = '无法获取';
                    }
                    if(swversion==null){
                        swversion = '无法获取';
                    }
                    if(ip==null){
                        ip = '无法获取';
                    }
                    if(mme==null){
                        mme = '无法获取';
                    }
                    if(license_user==null){
                        license_user = '无法获取';
                    }
                    if(enb_name==null){
                        enb_name = '无法获取';
                    }
                    if(name==null){
                        name = '无法获取';
                    }
                    if(format==null){
                        format = '无法获取';
                    }
                    if(type==null){
                        type = '无法获取';
                    }
                    if(latitude==null){
                        latitude = '无法获取';
                    }
                    if(longitude==null){
                        longitude = '无法获取';
                    }
                    // var data = "data-name='"+ite.name+
                    //     "' data-latitude='"+ite.latitude+
                    //     "' data-longitude='"+ite.longitude+
                    //     "' data-type='"+ite.type+
                    //     "' data-format='"+ite.format+
                    //     "' data-enb_name='"+ite.enb_name+
                    //     "' data-status='"+status+
                    //
                    //     "' data-enb_id='"+ite.enb_id+
                    //     "' data-swversion='"+ite.swversion+
                    //     "' data-ip='"+ite.ip+
                    //     "' data-mme='"+ite.mme+
                    //     "' data-license_user='"+ite.license_user+
                    //
                    //     "' data-RRC_Setup_Succ_Rate='"+RRC_Setup_Succ_Rate+
                    //     "' data-S1_Setup_Succ_Rate='"+S1_Setup_Succ_Rate+
                    //     "' data-ERAB_Setup_Succ_Rate='"+ERAB_Setup_Succ_Rate+
                    //     "' data-paging_Succ_Rate='"+paging_Succ_Rate+
                    //     "' data-Erab_Drop_Rate='"+Erab_Drop_Rate+
                    //     "' data-Within_System_HO_Succ_Rate='"+Within_System_HO_Succ_Rate+
                    //     "' data-Cell_Utilization_Rate='"+Cell_Utilization_Rate+
                    //     "' data-UL_PRB_Occupy_Rate='"+UL_PRB_Occupy_Rate+
                    //     "' data-DL_PRB_Occupy_Rate='"+DL_PRB_Occupy_Rate+
                    //     "' data-Dl_ThroughputRate_Mbps='"+Dl_ThroughputRate_Mbps+
                    //     "' data-Ul_ThroughputRate_Mbps='"+Ul_ThroughputRate_Mbps+
                    //     "' data-DL_User_Speed_Mbps='"+DL_User_Speed_Mbps+
                    //     "' data-UL_User_Speed_Mbps='"+UL_User_Speed_Mbps+
                    //     "' data-UE_SessionTime='"+UE_SessionTime+
                    //     "' data-Drb_pmSessionTime='"+Drb_pmSessionTime+
                    //     "' data-DL_Active_User_Num='"+DL_Active_User_Num+
                    //     "' data-UL_Active_User_Num='"+UL_Active_User_Num+
                    //
                    //     "'";
                    var str2 = "<li> " +
                        "<div class='link' id='" + ite.cell_name +
                        "'> " +
                        "<div style='display: inline-block; width: 50%'>"+ite.cell_name+"</div>" +
                        "<div style='display: inline-block; width: 50% ;text-align:center'>"+type+"</div>" +
                        // "<div class='details' style='display: inline-block;height:100%'" + data + " onclick='select_station(this)';></div>" +
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
                        "<div style='float: left;'>Cell Name</div> " +
                        "<div style='float: right;'>"+ite.cell_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>基站名称</div> " +
                        "<div style='float: right;'>"+name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>经度</div> " +
                        "<div style='float: right;'>"+longitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>纬度</div> " +
                        "<div style='float: right;'>"+latitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>enb_id</div> " +
                        "<div style='float: right;'>"+enb_id+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>swversion</div> " +
                        "<div style='float: right;'>"+swversion+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>ip</div> " +
                        "<div style='float: right;'>"+ip+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>mme</div> " +
                        "<div style='float: right; '>"+mme+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>license_user</div> " +
                        "<div style='float: right; '>"+license_user+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
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

            }else {
                status = 4;
                var str2 = "<li> " +
                    "<div class='link'> " +
                    "<div style='display: inline-block;width: 70% '>无法获取</div>" +
                    "<div style='display: inline-block; width: 20%'>无法获取</div>" +
                    // "<div class='details' style='display: inline-block;height:100%'" + data + " onclick='select_station(this)';></div>" +
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
                    "<div style='float: left;'>Cell Name</div> " +
                    "<div style='float: right;'>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>基站名称</div> " +
                    "<div style='float: right;'>"+name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>经度</div> " +
                    "<div style='float: right;'>"+longitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>纬度</div> " +
                    "<div style='float: right;'>"+latitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>enb_id</div> " +
                    "<div style='float: right;'>"+enb_id+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>swversion</div> " +
                    "<div style='float: right;'>"+swversion+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left;'>ip</div> " +
                    "<div style='float: right;'>"+ip+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left;'>mme</div> " +
                    "<div style='float: right;'>"+mme+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left;'>license_user</div> " +
                    "<div style='float: right;'>"+license_user+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>RRC_Setup_Succ_Rate(%)</br>RRC建立成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>S1_Setup_Succ_Rate(%)</br>S1链路建立成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>ERAB_Setup_Succ_Rate(%)</br>ERAB建立成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>paging_Succ_Rate(%)</br>寻呼成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Erab_Drop_Rate(%)</br>E-RAB掉线率</div> " +
                    "<div style='float: right;'>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Within_System_HO_Succ_Rate(%)</br>系统内切换成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Cell_Utilization_Rate(%)</br>小区利用率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UL_PRB_Occupy_Rate(%)</br>上行物理资源占用率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>DL_PRB_Occupy_Rate(%)</br>下行物理资源占用率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Dl_ThroughputRate_Mbps(Mbps)</br>下行吞吐率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Ul_ThroughputRate_Mbps(Mbps)</br>上行吞吐率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>DL_User_Speed_Mbps(Mbps)</br>下行用户速率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UL_User_Speed_Mbps(Mbps)</br>上行用户速率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UE_SessionTime(ms)</br>用户会话时长</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Drb_pmSessionTime(ms)</br>专用无线承载会话时长</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>DL_Active_User_Num</br>下行活动用户数</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UL_Active_User_Num</br>上行活动用户数</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "</ul> " +
                    "</li>";
                $('#accordion').append(str2);
            }
        }
    })
}
/*加载地图*/
function draw_map()
{
    loading('start');

    var fault  = '';
    var status_type   = '';
    var markLvl	= 0;
    if(status == 0)
    {
        // alert(status);
        markLvl = 0;
        fault  = '正常';
        status_type   = '<font color="#06fa06">正常</font>';
    }
    else if(status == 1)
    {
        // alert(status);
        markLvl = 3;
        fault  = '一级故障';
        status_type   = '<font color="#f29f00">异常：告警级别 一 级</font>';
    }
    else if(status == 2)
    {
        // alert(status);
        markLvl = 4;
        fault  = '二级故障';
        status_type   = '<font color="#cc00ff">异常：告警级别 二 级</font>';
    }
    else if(status == 3)
    {
        // alert(status);
        markLvl = 5;
        fault  = '三级故障';
        status_type   = '<font color="#fa0606">异常：告警级别 三 级</font>';
    }
    else if(status == 4)
    {
        // alert(status);
        markLvl = 1;
        fault  = '无法获取';
        status_type   = '';
    }
    $('.status_type').append(status_type);

    // var map = L.map('map').setView([39.9087677478,116.3975780499], 15);
    // url_google    = '../map/{z}/{x}/{y}.png';
    // L.tileLayer(url_google,{minZoom: 10, maxZoom: 13, attribution: '2016 爱立信技术支持' }).addTo(map);

    var url_baidu    = '../map/{z}/{x}/{y}.png';
    var glayer_baidu = new L.TileLayer(url_baidu, {minZoom: 10, maxZoom: 15, attribution: '2016 大连宏燊技术支持' });
    var map          = new L.Map('map', { center: [39.92285, 116.40318], zoom: 15, layers: [glayer_baidu] ,closePopupOnClick:false});
    var baseLayers    = {"普通地图": glayer_baidu};
    L.control.layers(baseLayers).addTo(map);

    //添加自定类图标的点标记
    var markIcon     = {icon: L.AwesomeMarkers.icon({icon: 'star',  prefix: 'glyphicon', markerColor: 'lvl'+markLvl})};


    var x = xtile(longitude, 15);
    var y = ytile(latitude, 15);
    if(
        (x>26874 && x<27098) && (y>12269&&y<=12480)
    )
    {
        var marker = new L.Marker([latitude, longitude], markIcon);
        map.addLayer(marker);
        var a = "<a onclick=\"open_cell('"+enb_name+"')\">详细</a>";
        var b = "<a onclick=\"open_alarm('"+enb_name+"')\">告警</a>";
        var tips = '';
        if (status == 0||status==4){
            tips = status_type;
        }else {
            for (var i = 0; i < cell_name.length; i++){
                if (i == cell_name.length-1){
                    var tip = cell_name[i].replace(enb_name,'')+' : '+cell_status[i];
                }else {
                    var tip = cell_name[i].replace(enb_name,'') + ' : ' + cell_status[i] + '<br/>';
                }
                tips += tip;
            }
        }
        var popup = new L.Popup({closeButton: false, autoClose:false});
        popup.setContent("<p>"+tips+"<br />"+type+" - "+format+"<br />  "+name+" <br /> "+a + "   " + b +" </p>");
        marker.bindPopup(popup).openPopup();
        map.setView([latitude,longitude]);
    }

    $('.leaflet-control-attribution').remove();
    $('.leaflet-control-locate').remove();
    $('.leaflet-control-layers').remove();

    loading('stop');


}
/*打开cell详细信息*/
function open_cell(node_name)
{
    if(node_name == ''){node_name = 'null';}
    var data = {'nodeName': node_name};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/get_cell_by_node",
        data:JSON.stringify(data),
        dataType:'json',
        success:function(response){
            //	console.log(response);
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
                    arr.sort();
                    var status = get_max(arr[0], arr[arr.length - 1]);
                    var cell_status = "";
                    if (ite.status == null){
                        status = 4;
                    }
                    if(status == 0) {
                        cell_status = '<span><font color="#06fa06">正常</font></span>';
                    }else if(status == 1) {
                        cell_status = '<span><font color="#f29f00">一 级告警</font></span>';
                    }else if(status == 2) {
                        cell_status = '<span><font color="#cc00ff">二 级告警</font></span>';
                    }else if(status == 3) {
                        cell_status = '<span><font color="#fa0606">三 级告警</font></span>';
                    }else if(status == 4) {
                        cell_status = '<span><font color="#fa0606">无 法获取</font></span>';
                    }
                    $('#cell_name').append("<div><span>"+ite.cell_name+"</span></div>");
                    $('#status').append("<div>"+cell_status+"</div>");
                    $('#RRC_Setup_Succ_Rate').append("<div><span>"+RRC_Setup_Succ_Rate+"</span></div>");
                    $('#S1_Setup_Succ_Rate').append("<div><span>"+S1_Setup_Succ_Rate+"</span></div>");
                    $('#ERAB_Setup_Succ_Rate').append("<div><span>"+ERAB_Setup_Succ_Rate+"</span></div>");
                    $('#paging_Succ_Rate').append("<div><span>"+paging_Succ_Rate+"</span></div>");
                    $('#Erab_Drop_Rate').append("<div><span>"+Erab_Drop_Rate+"</span></div>");
                    $('#Within_System_HO_Succ_Rate').append("<div><span>"+Within_System_HO_Succ_Rate+"</span></div>");
                    $('#Cell_Utilization_Rate').append("<div><span>"+Cell_Utilization_Rate+"</span></div>");
                    $('#UL_PRB_Occupy_Rate').append("<div><span>"+UL_PRB_Occupy_Rate+"</span></div>");
                    $('#DL_PRB_Occupy_Rate').append("<div><span>"+DL_PRB_Occupy_Rate+"</span></div>");
                    $('#Dl_ThroughputRate_Mbps').append("<div><span>"+Dl_ThroughputRate_Mbps+"</span></div>");
                    $('#Ul_ThroughputRate_Mbps').append("<div><span>"+Ul_ThroughputRate_Mbps+"</span></div>");
                    $('#DL_User_Speed_Mbps').append("<div><span>"+DL_User_Speed_Mbps+"</span></div>");
                    $('#UL_User_Speed_Mbps').append("<div><span>"+UL_User_Speed_Mbps+"</span></div>");
                    $('#UE_SessionTime').append("<div><span>"+UE_SessionTime+"</span></div>");
                    $('#Drb_pmSessionTime').append("<div><span>"+Drb_pmSessionTime+"</span></div>");
                    $('#DL_Active_User_Num').append("<div><span>"+DL_Active_User_Num+"</span></div>");
                    $('#UL_Active_User_Num').append("<div><span>"+UL_Active_User_Num+"</span></div>");
                });
            }
            else
            {
                $('#cell_name').append("<span>"+enb_name+"</span>");
                $('#status').append("<div>"+"无法获取"+"</div>");
                $('#RRC_Setup_Succ_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#S1_Setup_Succ_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#ERAB_Setup_Succ_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#paging_Succ_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#Erab_Drop_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#Within_System_HO_Succ_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#Cell_Utilization_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#UL_PRB_Occupy_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#DL_PRB_Occupy_Rate').append("<div><span>"+"无法获取"+"</span></div>");
                $('#Dl_ThroughputRate_Mbps').append("<div><span>"+"无法获取"+"</span></div>");
                $('#Ul_ThroughputRate_Mbps').append("<div><span>"+"无法获取"+"</span></div>");
                $('#DL_User_Speed_Mbps').append("<div><span>"+"无法获取"+"</span></div>");
                $('#UL_User_Speed_Mbps').append("<div><span>"+"无法获取"+"</span></div>");
                $('#UE_SessionTime').append("<div><span>"+"无法获取"+"</span></div>");
                $('#Drb_pmSessionTime').append("<div><span>"+"无法获取"+"</span></div>");
                $('#DL_Active_User_Num').append("<div><span>"+"无法获取"+"</span></div>");
                $('#UL_Active_User_Num').append("<div><span>"+"无法获取"+"</span></div>");
            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
    $("#map").addClass("frosted-glass");
    $("#bg").slideDown(500,function (){
        $("#alarm_list").hide();
        $("#cell_list").show();
        $('#bg').perfectScrollbar();
        $('#bg').animate({scrollTop:0},0);
    });

}
/*打开alarm详细信息*/
function open_alarm(node_name)
{
    var data = {'nodeName':node_name};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/get_alarm_by_node",
        data:JSON.stringify(data),
        dataType:'json',
        success:function(response){
            $('.list').remove();
            if(response.status)
            {
                var str = '';
                $('#inner_alarm_list').append("<li><div class='link_alarm'>" +
            "<div  style='float: left;  width: 150px; color: #ffffff'>告警名称</div> " +
            "<div  style='float: left;  width: 100px'>&nbsp;</div> " +
            "<div  style='float: left;  width: 150px; color: #ffffff'>告警级别</div>" +
                    "<div  style='float: left;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>" +
            "</div></li>");
                $.each(response.data, function(i,ite) {

                    var str2 = "<li> " +
                        "<div class='link_alarm' id='" + i +
                        "' onclick='open_submenu(this)'> " +
                        "<div  style='float: left;  width: 150px'>" +
                        ite.alarm_name_dis +"</div>" +
                        "<div  style='float: left;  width: 100px'>&nbsp;</div>" +
                        "<div  style='float: left;  width: 150px'>" +
                        ite.alarm_type_name+"</div>" +
                        "<div id='detail-alarm'  style='float: left;'>详细</div>" +
                        "</div> " +
                        "<ul class='submenuAlarm'> " +
                        "<li> " +
                        "<div  style='float: left;  width: 150px'>ENodeB Name</div>" +
                        "<div  style='float: left;  width: 100px'>&nbsp;</div>" +
                        "<div  style='float: left;  width: 150px'>" +
                        ite.node_name+"</div>" +
                        "</li> " +
                        "<li> " +
                        "<div  style='float: left;  width: 150px'>MO</div>" +
                        "<div  style='float: left;  width: 100px'>&nbsp;</div>" +
                        "<div  style='float: left;  width: 150px'>" +
                        ite.mo+"</div>" +
                        "</li> " +
                        "<li> " +
                        "<div  style='float: left;  width: 150px'>告警时间</div>" +
                        "<div  style='float: left;  width: 100px'>&nbsp;</div>" +
                        "<div  style='float: left;  width: 150px'>" +
                        ite.alarm_time+"</div>" +
                        "</li> " +
                        "<li> " +
                        "<div  style='float: left;  width: 150px'>告警名称</div>" +
                        "<div  style='float: left;  width: 100px'>&nbsp;</div>" +
                        "<div  style='float: left;  width: 150px'>" +
                        ite.alarm_name_dis+"</div>" +
                        "</li> " +
                        "<li> " +
                        "<div  style='float: left;  width: 150px'>告警级别</div>" +
                        "<div  style='float: left;  width: 100px'>&nbsp;</div>" +
                        "<div  style='float: left;  width: 150px'>" +
                        ite.alarm_type_name+"</div>" +
                        "</li> " +
                        "</ul> " +
                        "</li>";
                    $('#inner_alarm_list').append(str2);
                });
                $('#inner_alarm_list').append('<li><div>&nbsp;</div></li>');
            }
            else
            {
                $('#inner_alarm_list').append('<li><div class="link_alarm">没有告警</div></li>');
                $('#inner_alarm_list').append('<li><div>&nbsp;</div></li>');
            }
        },
        error:function(e){
            console.log(e.responseText);
        }
    });
    $("#map").addClass("frosted-glass");
    $("#bg").slideDown(500,function (){
        $("#cell_list").hide();
        $("#alarm_list").show();
        $('#bg').perfectScrollbar();
        $('#bg').animate({scrollTop:0},0);
    });

}
var x = '';
function open_submenu(obj) {
        var id = $(obj).attr("id");
        var $el = $('.link_alarm').parent();
        $this = $(obj),
            $next = $this.next();
        $next.slideToggle();
        $this.parent().toggleClass('openAlarm');
        if (x == id){
            $el.find('.submenuAlarm').not($next).slideUp().parent().removeClass('open');
            $(obj).css("color","white");
            x = "";
        }else {
            x = id;
            $('.link_alarm').not(obj).css("color","white");
            $el.find('.submenuAlarm').not($next).slideUp().parent().removeClass('open');
            $(obj).css("color","red");
        }
}
/*搜索*/
function input_search()
{


    $('#accordion').children().remove();
    var keyword = $('#input_search').val();
    var data = {'nodeName':enb_name,'keyword':keyword,'order':'desc'};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/search_cell_by_node",
        data:JSON.stringify(data),
        dataType:'json',
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
                    var status_cell = get_max(arr[0], arr[arr.length - 1]);
                    if (ite.status == null){
                        status_cell = 4;
                    }
                    var type   = '';
                    if(status_cell == 0)
                    {
                        type   = '<font color="#06fa06">正常</font>';
                    }
                    else if(status_cell == 1)
                    {
                        type   = '<font color="#f29f00">异常：告警级别 一 级</font>';
                        cell_name.push(ite.cell_name);
                        cell_status.push('<font color="#f29f00">一级告警</font>');
                    }
                    else if(status_cell == 2)
                    {
                        type   = '<font color="#cc00ff">异常：告警级别 二 级</font>';
                        cell_name.push(ite.cell_name);
                        cell_status.push('<font color="#cc00ff">二级告警</font>');
                    }
                    else if(status_cell == 3)
                    {
                        type   = '<font color="#fa0606">异常：告警级别 三 级</font>';
                        cell_name.push(ite.cell_name);
                        cell_status.push('<font color="#fa0606">三级告警</font>');
                    }
                    if (status_cell> status){
                        status = status_cell;
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
                    if(enb_id==null){
                        enb_id = '无法获取';
                    }
                    if(swversion==null){
                        swversion = '无法获取';
                    }
                    if(ip==null){
                        ip = '无法获取';
                    }
                    if(mme==null){
                        mme = '无法获取';
                    }
                    if(license_user==null){
                        license_user = '无法获取';
                    }
                    if(enb_name==null){
                        enb_name = '无法获取';
                    }
                    if(name==null){
                        name = '无法获取';
                    }
                    if(format==null){
                        format = '无法获取';
                    }
                    if(type==null){
                        type = '无法获取';
                    }
                    if(latitude==null){
                        latitude = '无法获取';
                    }
                    if(longitude==null){
                        longitude = '无法获取';
                    }
                    // var data = "data-name='"+ite.name+
                    //     "' data-latitude='"+ite.latitude+
                    //     "' data-longitude='"+ite.longitude+
                    //     "' data-type='"+ite.type+
                    //     "' data-format='"+ite.format+
                    //     "' data-enb_name='"+ite.enb_name+
                    //     "' data-status='"+status+
                    //
                    //     "' data-enb_id='"+ite.enb_id+
                    //     "' data-swversion='"+ite.swversion+
                    //     "' data-ip='"+ite.ip+
                    //     "' data-mme='"+ite.mme+
                    //     "' data-license_user='"+ite.license_user+
                    //
                    //     "' data-RRC_Setup_Succ_Rate='"+RRC_Setup_Succ_Rate+
                    //     "' data-S1_Setup_Succ_Rate='"+S1_Setup_Succ_Rate+
                    //     "' data-ERAB_Setup_Succ_Rate='"+ERAB_Setup_Succ_Rate+
                    //     "' data-paging_Succ_Rate='"+paging_Succ_Rate+
                    //     "' data-Erab_Drop_Rate='"+Erab_Drop_Rate+
                    //     "' data-Within_System_HO_Succ_Rate='"+Within_System_HO_Succ_Rate+
                    //     "' data-Cell_Utilization_Rate='"+Cell_Utilization_Rate+
                    //     "' data-UL_PRB_Occupy_Rate='"+UL_PRB_Occupy_Rate+
                    //     "' data-DL_PRB_Occupy_Rate='"+DL_PRB_Occupy_Rate+
                    //     "' data-Dl_ThroughputRate_Mbps='"+Dl_ThroughputRate_Mbps+
                    //     "' data-Ul_ThroughputRate_Mbps='"+Ul_ThroughputRate_Mbps+
                    //     "' data-DL_User_Speed_Mbps='"+DL_User_Speed_Mbps+
                    //     "' data-UL_User_Speed_Mbps='"+UL_User_Speed_Mbps+
                    //     "' data-UE_SessionTime='"+UE_SessionTime+
                    //     "' data-Drb_pmSessionTime='"+Drb_pmSessionTime+
                    //     "' data-DL_Active_User_Num='"+DL_Active_User_Num+
                    //     "' data-UL_Active_User_Num='"+UL_Active_User_Num+
                    //
                    //     "'";
                    var str2 = "<li> " +
                        "<div class='link' id='" + ite.cell_name +
                        "'> " +
                        "<div style='display: inline-block; width: 50%'>"+ite.cell_name+"</div>" +
                        "<div style='display: inline-block; width: 50% ;text-align:center'>"+type+"</div>" +
                        // "<div class='details' style='display: inline-block;height:100%'" + data + " onclick='select_station(this)';></div>" +
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
                        "<div style='float: left;'>Cell Name</div> " +
                        "<div style='float: right;'>"+ite.cell_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>基站名称</div> " +
                        "<div style='float: right;'>"+name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>经度</div> " +
                        "<div style='float: right;'>"+longitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>纬度</div> " +
                        "<div style='float: right;'>"+latitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>enb_id</div> " +
                        "<div style='float: right;'>"+enb_id+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>swversion</div> " +
                        "<div style='float: right;'>"+swversion+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>ip</div> " +
                        "<div style='float: right;'>"+ip+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>mme</div> " +
                        "<div style='float: right; '>"+mme+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>license_user</div> " +
                        "<div style='float: right; '>"+license_user+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
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
            }else {
                status = 4;
                var str2 = "<li> " +
                    "<div class='link'> " +
                    "<div style='display: inline-block;width: 70% '>无法获取</div>" +
                    "<div style='display: inline-block; width: 20%'>无法获取</div>" +
                    // "<div class='details' style='display: inline-block;height:100%'" + data + " onclick='select_station(this)';></div>" +
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
                    "<div style='float: left;'>Cell Name</div> " +
                    "<div style='float: right;'>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>基站名称</div> " +
                    "<div style='float: right;'>"+name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>经度</div> " +
                    "<div style='float: right;'>"+longitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>纬度</div> " +
                    "<div style='float: right;'>"+latitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>enb_id</div> " +
                    "<div style='float: right;'>"+enb_id+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>swversion</div> " +
                    "<div style='float: right;'>"+swversion+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left;'>ip</div> " +
                    "<div style='float: right;'>"+ip+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left;'>mme</div> " +
                    "<div style='float: right;'>"+mme+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left;'>license_user</div> " +
                    "<div style='float: right;'>"+license_user+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>RRC_Setup_Succ_Rate(%)</br>RRC建立成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>S1_Setup_Succ_Rate(%)</br>S1链路建立成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>ERAB_Setup_Succ_Rate(%)</br>ERAB建立成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>paging_Succ_Rate(%)</br>寻呼成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Erab_Drop_Rate(%)</br>E-RAB掉线率</div> " +
                    "<div style='float: right;'>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Within_System_HO_Succ_Rate(%)</br>系统内切换成功率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Cell_Utilization_Rate(%)</br>小区利用率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UL_PRB_Occupy_Rate(%)</br>上行物理资源占用率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>DL_PRB_Occupy_Rate(%)</br>下行物理资源占用率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Dl_ThroughputRate_Mbps(Mbps)</br>下行吞吐率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Ul_ThroughputRate_Mbps(Mbps)</br>上行吞吐率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>DL_User_Speed_Mbps(Mbps)</br>下行用户速率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UL_User_Speed_Mbps(Mbps)</br>上行用户速率</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UE_SessionTime(ms)</br>用户会话时长</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>Drb_pmSessionTime(ms)</br>专用无线承载会话时长</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>DL_Active_User_Num</br>下行活动用户数</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "<li> " +
                    "<div style='float: left; '>UL_Active_User_Num</br>上行活动用户数</div> " +
                    "<div style='float: right; '>无法获取&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                    "</li> " +
                    "</ul> " +
                    "</li>";
                $('#accordion').append(str2);
            }
            loadJs();
        },
        error:function(e){
            console.log(e.responseText);
        }
    });

}

$(function() {
    $("#close_cell").click(function(){
        $("#bg").slideUp(500,function () {
            $("#map").css("z-index","-1000");
            $("div.link_cell *").not(".keep").each(function() {
                $(this).remove();
            });
            $("#alarm_list").hide();
            $("#cell_list").hide();
            $("#map").removeClass("frosted-glass");
            $("#map").css("z-index","0");
        });
    });
    $("#close_alarm").click(function(){
        $("#bg").slideUp(500,function () {
            $("#map").css("z-index","-1000");
            $("#alarm_list").hide();
            $("#cell_list").hide();
            $("#map").removeClass("frosted-glass");
            $('#inner_alarm_list').children().remove();
            $("#map").css("z-index","0");
        });

    });
});


