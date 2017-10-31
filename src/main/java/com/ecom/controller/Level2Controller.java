package com.ecom.controller;

import com.ecom.entity.BaseStation;
import com.ecom.entity.QuotaThresholdNode;
import com.ecom.service.Level2Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a7289 on 2016/11/4 0004.
 */
@Controller
@RequestMapping("/lte")
public class Level2Controller {
    @Resource
    private Level2Service level2Service;

    @RequestMapping(value = "level2")
    public String init(){
        return "html/level2";
    }

    @RequestMapping(value = "get_station_by_tac", method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> getStationByTac(@RequestBody BaseStation baseStation){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseStation> baseStationList = level2Service.getStationByTac(baseStation);
        if (baseStationList == null || baseStationList.size()==0){
            msg = "获得基站信息错误";
        }else{
            msg = "获得基站信息成功";
            status = true;
            for (int i = 0; i < baseStationList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("name",baseStationList.get(i).getName());
                data.put("type",baseStationList.get(i).getType());
                data.put("format",baseStationList.get(i).getFormat());
                data.put("enb_name",baseStationList.get(i).getEnbName());
                data.put("longitude",baseStationList.get(i).getLongitude());
                data.put("latitude",baseStationList.get(i).getLatitude());
                data.put("enb_id",baseStationList.get(i).getEnbId());
                data.put("swversion",baseStationList.get(i).getSwversion());
                data.put("ip",baseStationList.get(i).getIp());
                data.put("mme",baseStationList.get(i).getMme());
                data.put("license_user",baseStationList.get(i).getLicenseUser());
                if (baseStationList.get(i).getBaseNode()!=null){

                data.put("status",baseStationList.get(i).getBaseNode().getStatus());
                data.put("RRC_Setup_Succ_Rate",baseStationList.get(i).getBaseNode().getRrcSetupSuccRate());
                data.put("S1_Setup_Succ_Rate",baseStationList.get(i).getBaseNode().getS1SetupSuccRate());
                data.put("ERAB_Setup_Succ_Rate",baseStationList.get(i).getBaseNode().getErabSetupSuccRate());
                data.put("paging_Succ_Rate",baseStationList.get(i).getBaseNode().getPagingSuccRate());
                data.put("Erab_Drop_Rate",baseStationList.get(i).getBaseNode().getErabDropRate());
                data.put("Within_System_HO_Succ_Rate",baseStationList.get(i).getBaseNode().getWithinSystemHoSuccRate());
                data.put("Cell_Utilization_Rate",baseStationList.get(i).getBaseNode().getCellUtilizationRate());
                data.put("UL_PRB_Occupy_Rate",baseStationList.get(i).getBaseNode().getUlPrbOccupyRate());
                data.put("DL_PRB_Occupy_Rate",baseStationList.get(i).getBaseNode().getDlPrbOccupyRate());
                data.put("Dl_ThroughputRate_Mbps",baseStationList.get(i).getBaseNode().getDlThroughputrateMbps());
                data.put("Ul_ThroughputRate_Mbps",baseStationList.get(i).getBaseNode().getUlThroughputrateMbps());
                data.put("DL_User_Speed_Mbps",baseStationList.get(i).getBaseNode().getDlUserSpeedMbps());
                data.put("UL_User_Speed_Mbps",baseStationList.get(i).getBaseNode().getUlUserSpeedMbps());
                data.put("UE_SessionTime",baseStationList.get(i).getBaseNode().getUeSessiontime());
                data.put("Drb_pmSessionTime",baseStationList.get(i).getBaseNode().getDrbPmsessiontime());
                data.put("DL_Active_User_Num",baseStationList.get(i).getBaseNode().getDlActiveUserNum());
                data.put("UL_Active_User_Num",baseStationList.get(i).getBaseNode().getUlActiveUserNum());

                }
                dataList.add(i,data);

            }

        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_node_quota_threshold", method = RequestMethod.GET)
    public @ResponseBody
    Map<String , Object> getNodeQuotaThreshold(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<QuotaThresholdNode> quotaThresholdNodeList = level2Service.getNodeQuotaThreshold();
        if (quotaThresholdNodeList == null || quotaThresholdNodeList.size() == 0){
            msg = "获得node_quota_threshold失败";
        }else{
            msg = "获得node_quota_threshold成功";
            status = true;
            for (int i = 0; i < quotaThresholdNodeList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("quota_name",quotaThresholdNodeList.get(i).getQuotaName());
                data.put("quota_type",quotaThresholdNodeList.get(i).getQuotaType());
                data.put("threshold_1",quotaThresholdNodeList.get(i).getThreshold1());
                data.put("threshold_2",quotaThresholdNodeList.get(i).getThreshold2());
                data.put("threshold_3",quotaThresholdNodeList.get(i).getThreshold3());
                data.put("quota_unit",quotaThresholdNodeList.get(i).getQuotaUnit());

                dataList.add(i,data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;

    }

    @RequestMapping(value = "search_station", method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> searchStation(@RequestBody BaseStation baseStation) {
        boolean status = false;
        String msg = "";
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseStation> baseStationList = level2Service.searchStation(baseStation);
        if (baseStationList == null || baseStationList.size()==0){
            msg = "获得基站信息错误";
        }else{
            msg = "获得基站信息成功";
            status = true;
            for (int i = 0; i < baseStationList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("name",baseStationList.get(i).getName());
                data.put("type",baseStationList.get(i).getType());
                data.put("format",baseStationList.get(i).getFormat());
                data.put("enb_name",baseStationList.get(i).getEnbName());
                data.put("longitude",baseStationList.get(i).getLongitude());
                data.put("latitude",baseStationList.get(i).getLatitude());
                data.put("enb_id",baseStationList.get(i).getEnbId());
                data.put("swversion",baseStationList.get(i).getSwversion());
                data.put("ip",baseStationList.get(i).getIp());
                data.put("mme",baseStationList.get(i).getMme());
                data.put("license_user",baseStationList.get(i).getLicenseUser());
                if (baseStationList.get(i).getBaseNode()!=null) {
                    data.put("status", baseStationList.get(i).getBaseNode().getStatus());
                    data.put("RRC_Setup_Succ_Rate", baseStationList.get(i).getBaseNode().getRrcSetupSuccRate());
                    data.put("S1_Setup_Succ_Rate", baseStationList.get(i).getBaseNode().getS1SetupSuccRate());
                    data.put("ERAB_Setup_Succ_Rate", baseStationList.get(i).getBaseNode().getErabSetupSuccRate());
                    data.put("paging_Succ_Rate", baseStationList.get(i).getBaseNode().getPagingSuccRate());
                    data.put("Erab_Drop_Rate", baseStationList.get(i).getBaseNode().getErabDropRate());
                    data.put("Within_System_HO_Succ_Rate", baseStationList.get(i).getBaseNode().getWithinSystemHoSuccRate());
                    data.put("Cell_Utilization_Rate", baseStationList.get(i).getBaseNode().getCellUtilizationRate());
                    data.put("UL_PRB_Occupy_Rate", baseStationList.get(i).getBaseNode().getUlPrbOccupyRate());
                    data.put("DL_PRB_Occupy_Rate", baseStationList.get(i).getBaseNode().getDlPrbOccupyRate());
                    data.put("Dl_ThroughputRate_Mbps", baseStationList.get(i).getBaseNode().getDlThroughputrateMbps());
                    data.put("Ul_ThroughputRate_Mbps", baseStationList.get(i).getBaseNode().getUlThroughputrateMbps());
                    data.put("DL_User_Speed_Mbps", baseStationList.get(i).getBaseNode().getDlUserSpeedMbps());
                    data.put("UL_User_Speed_Mbps", baseStationList.get(i).getBaseNode().getUlUserSpeedMbps());
                    data.put("UE_SessionTime", baseStationList.get(i).getBaseNode().getUeSessiontime());
                    data.put("Drb_pmSessionTime", baseStationList.get(i).getBaseNode().getDrbPmsessiontime());
                    data.put("DL_Active_User_Num", baseStationList.get(i).getBaseNode().getDlActiveUserNum());
                    data.put("UL_Active_User_Num", baseStationList.get(i).getBaseNode().getUlActiveUserNum());
                }
                dataList.add(i,data);

            }

        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }
}
