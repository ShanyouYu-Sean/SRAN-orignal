/**
 * Created by a7289 on 2016/12/12 0012.
 */
map = '';
var zoom;
var pop = new Map();
/*检查并获取登录信息*/
check_login();
user_id  = getCookie('user_id');
/*显示用户名称*/
show_username();
tac  =getCookie('tac');
update_time = '';
quota_threshold_node = '';
mapData = [];
/*获得node门限*/
get_node_quota_threshold();
/*获得更新时间*/
get_update_time();
/*根据传来的tac获得相应的station的node数据*/
init_node_data();
/*加载地图*/
draw_map();



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
/*获取node的详细信息数据*/
function init_node_data()
{
    var data = {'order':'','tac':tac, 'area':''};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"POST",
        url:"../lte/get_station_by_tac",
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
                    if (ite.status == null){
                        status = 4;
                    }
                    var type   = '';
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
                    else if(status == 4)
                    {
                        type   = '<font color="black">无法获取</font>';
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
                        "<div class='link' id='" + ite.enb_name +
                        "'> " +
                        "<div style='display: inline-block; width: 70%'>"+ite.enb_name+"</div>" +
                        "<div style='display: inline-block; width: 20%'>"+type+"</div>" +
                        "<div class='details' style='display: inline-block;height:100%'" + data + " onclick='select_station(this)';></div>" +
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
                    $('#accordion').append(str2);

                    var a = "<a onclick='select_station(this)' "+data+" >详细</a>";
                    var d = "<p>"+ite.enb_name+"<br />"+ite.type+" - "+ite.format+"<br />  "+ite.name+" <br /> "+a+" </p>";
                    mapData.push({
                        lon: ite.longitude,
                        lat: ite.latitude,
                        h: new Date().toString(),
                        d: d,
                        s: status,
                        n: ite.enb_name
                    });
                });
                window.mapData = mapData;
            }
        }
    });
     var keyword = getCookie('keyword');
    if((keyword != null && keyword != '')&&(tac.indexOf(keyword) == -1)){
        $('#input_search').val(keyword);
        input_search();
        $('#input_search').text(keyword);
    }
}

/*kpi比较*/
function kpi(quota_name, quota_val)
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
/*加载地图*/
function draw_map()
{
    loading('start');

    map = L.map('map').setView([39.9087677478,116.3975780499], 13);
    url_google    = '../map/{z}/{x}/{y}.png';
    L.tileLayer(url_google,{minZoom: 10, maxZoom: 13, attribution: '2016 大连宏燊技术支持' }).addTo(map);

    // url_baidu    = '../map/{z}/{x}/{y}.png';
    // glayer_baidu = new L.TileLayer(url_baidu, {minZoom: 10, maxZoom: 15, attribution: '2016 爱立信技术支持' });
    // map          = new L.Map('map', {center: [39.92285, 116.40318], zoom: 13, layers: [glayer_baidu] });
    // baseLayers    = {"普通地图": glayer_baidu};
    // L.control.layers(baseLayers).addTo(map);

    //添加自定类图标的点标记
    var darkIcon = L.Icon.extend({options: {iconAnchor:   [13, 42]}});
    var lvl0      = new darkIcon({iconUrl: '../images/marker-icon-green.png'});
    var lvl1      = new darkIcon({iconUrl: '../images/marker-icon-orange.png'});
    var lvl2      = new darkIcon({iconUrl: '../images/marker-icon-purple.png'});
    var lvl3      = new darkIcon({iconUrl: '../images/marker-icon-red.png'});
    var lvl4      = new darkIcon({iconUrl: '../images/marker-icon-grey.png'});


    var oms = new OverlappingMarkerSpiderfier(map);

    var bounds = new L.LatLngBounds();
    for (var i = window.mapData.length - 1; i >=0 ; i --) {
        var datum = window.mapData[i];
        var x = xtile(datum.lon, 15);
        var y = ytile(datum.lat, 15);
        if(
            (x>26874 && x<27098) && (y>12269&&y<=12480)
        )
        {

            var loc = new L.LatLng(datum.lat, datum.lon);
            bounds.extend(loc);
            if(datum.s == 0)
            {
                var marker = new L.Marker(loc, {icon: lvl0});
            }
            else if(datum.s == 1)
            {
                var marker = new L.Marker(loc, {icon: lvl1});
            }
            else if(datum.s == 2)
            {
                var marker = new L.Marker(loc, {icon: lvl2});
            }
            else if(datum.s == 3)
            {
                var marker = new L.Marker(loc, {icon: lvl3});
            }
            else if(datum.s == 4)
            {
                var marker = new L.Marker(loc, {icon: lvl4});
            }
            marker.desc   = datum.d;
            marker.status = datum.s;
            marker.n = datum.n;
            map.addLayer(marker);
            oms.addMarker(marker);
            pop.put(marker.n,marker);

        }
    }
    map.fitBounds(bounds);
    zoom = map.getBoundsZoom(bounds);

    var popup = new L.Popup({closeButton: false, offset: new L.Point(0.5, -24)});
    oms.addListener('click', function(marker) {
        popup.setContent(marker.desc);
        popup.setLatLng(marker.getLatLng());
        map.openPopup(popup);
    });

    oms.addListener('spiderfy', function(markers) {
        for (var i = 0, len = markers.length; i < len; i ++)
        {
            if(markers[i].status == 0)
            {
                markers[i].setIcon(lvl0);
            }
            else if(markers[i].status == 1)
            {
                markers[i].setIcon(lvl1);
            }
            else if(markers[i].status == 2)
            {
                markers[i].setIcon(lvl2);
            }
            else if(markers[i].status == 3)
            {
                markers[i].setIcon(lvl3);
            }
            else if(markers[i].status == 4)
            {
                markers[i].setIcon(lvl4);
            }
        }
        map.closePopup();
    });

    oms.addListener('unspiderfy', function(markers) {
        for (var i = 0, len = markers.length; i < len; i ++)
        {
            if(markers[i].status == 4)
            {
                markers[i].setIcon(lvl4);
            }
            else if(markers[i].status == 3)
            {
                markers[i].setIcon(lvl3);
            }
            else if(markers[i].status == 2)
            {
                markers[i].setIcon(lvl2);
            }
            else if(markers[i].status == 1)
            {
                markers[i].setIcon(lvl1);
            }
            else if(markers[i].status == 0)
            {
                markers[i].setIcon(lvl0);
            }
        }
    });

    window.map = map;
    window.oms = oms;


    loading('stop');

}
/*跳转页面*/
function select_station(obj) {
    var station = 'name='+$(obj).data('name')+
            '&latitude='+$(obj).data('latitude')+
            '&longitude='+$(obj).data('longitude')+
            '&type='+$(obj).data('type')+
            '&format='+$(obj).data('format')+
            '&enb_name='+$(obj).data('enb_name')+
            '&status='+$(obj).data('status')+

            '&tac='+tac+
            '&enb_id='+$(obj).data('enb_id')+
            '&swversion='+$(obj).data('swversion')+
            '&ip='+$(obj).data('ip')+
            '&mme='+$(obj).data('mme')+
            '&license_user='+$(obj).data('license_user')+

            '&RRC_Setup_Succ_Rate='+$(obj).data('rrc_setup_succ_rate')+
            '&S1_Setup_Succ_Rate='+$(obj).data('s1_setup_succ_rate')+
            '&ERAB_Setup_Succ_Rate='+$(obj).data('erab_setup_succ_rate')+
            '&paging_Succ_Rate='+$(obj).data('paging_succ_rate')+
            '&Erab_Drop_Rate='+$(obj).data('erab_drop_rate')+
            '&Within_System_HO_Succ_Rate='+$(obj).data('within_system_ho_succ_rate')+
            '&Cell_Utilization_Rate='+$(obj).data('cell_utilization_rate')+
            '&UL_PRB_Occupy_Rate='+$(obj).data('ul_prb_occupy_rate')+
            '&DL_PRB_Occupy_Rate='+$(obj).data('dl_prb_occupy_rate')+
            '&Dl_ThroughputRate_Mbps='+$(obj).data('dl_throughputrate_mbps')+
            '&Ul_ThroughputRate_Mbps='+$(obj).data('ul_throughputrate_mbps')+
            '&DL_User_Speed_Mbps='+$(obj).data('dl_user_speed_mbps')+
            '&UL_User_Speed_Mbps='+$(obj).data('ul_user_speed_mbps')+
            '&UE_SessionTime='+$(obj).data('ue_sessiontime')+
            '&Drb_pmSessionTime='+$(obj).data('drb_pmsessiontime')+
            '&DL_Active_User_Num='+$(obj).data('dl_active_user_num')+
            '&UL_Active_User_Num='+$(obj).data('ul_active_user_num')+
            '&update_time='+update_time
        ;
    setCookie('station',station,1);
    $('#input_search').val('');
    window.location.href = '../lte/level3';
}
/*搜索*/
function input_search()
{
    $('#accordion').children().remove();
    var keyword = $('#input_search').val();
    var data = {'tac':tac,'keyword':keyword};
    $.ajax({
        contentType : 'application/json;charset=utf-8',
        type:"post",
        url:"../lte/search_station",
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
                    var status = get_max(arr[0], arr[arr.length - 1]);
                    if (ite.status == null){
                        status = 4;
                    }
                    var type   = '';
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
                    else if(status == 4)
                    {
                        type   = '<font color="black">无法获取</font>';
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
                        ite.type = '无法获取';
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
                        "<div class='link' id='" + ite.enb_name +
                        "'> " +
                        "<div style='display: inline-block;width: 70% '>"+ite.enb_name+"</div>" +
                        "<div style='display: inline-block; width: 20%'>"+type+"</div>" +
                        "<div class='details' style='display: inline-block;height:100%'" + data + " onclick='select_station(this)';></div>" +
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
                        "<div style='float: left;'>ENodeB Name</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.enb_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>基站名称</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.name+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>经度</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.longitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>纬度</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.latitude+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>enb_id</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.enb_id+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left; '>swversion</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.swversion+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>ip</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.ip+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>mme</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.mme+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
                        "</li> " +
                        "<li> " +
                        "<div style='float: left;'>license_user</div> " +
                        "<div style='float: right; color:#06fa06'>"+ite.license_user+"&nbsp;&nbsp;&nbsp;&nbsp;"+"</div> " +
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
            };
            var cookieKeyword = getCookie('keyword');
            if(cookieKeyword == null || cookieKeyword == ''){
                loadJs();
                locate();
            };
            delCookie('keyword');
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
            var popup = new L.Popup({closeButton: false, offset: new L.Point(0.5, -24)});
            var marker = pop.get(id);
            popup.setContent(marker.desc);
            popup.setLatLng(marker.getLatLng());
            map.openPopup(popup);
            pop.put('popup',popup);
            if (zoom == 15){
                map.setView(marker.getLatLng(),15);
            }else {
                map.setView(marker.getLatLng(),14);
            }

        }
    });
}

$(function() {
    locate();
});