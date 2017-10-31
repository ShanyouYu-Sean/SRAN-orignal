package com.ecom.controller;

import com.ecom.entity.BaseAlarm;
import com.ecom.entity.BaseCell;
import com.ecom.entity.QuotaThresholdCell;
import com.ecom.service.Level3Service;
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
 * Created by a7289 on 2016/11/8 0008.
 */
@Controller
@RequestMapping("/lte")
public class Level3Controller {
    @Resource
    private Level3Service level3Service;

    @RequestMapping(value = "level3")
    public String init(){
        return "html/level3";
    }

    @RequestMapping(value = "get_cell_by_node",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> getCellByNode(@RequestBody BaseCell baseCell){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseCell> baseCellList = level3Service.getCellByNode(baseCell);
        if (baseCellList == null || baseCellList.size() == 0){
            msg = "获得baseCell错误";
        }else{
            msg = "获得baseCell成功";
            status = true;
            for (int i = 0; i < baseCellList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("cell_name",baseCellList.get(i).getCellName());
                data.put("status",baseCellList.get(i).getStatus());
                data.put("RRC_Setup_Succ_Rate",baseCellList.get(i).getRrcSetupSuccRate());
                data.put("S1_Setup_Succ_Rate",baseCellList.get(i).getS1SetupSuccRate());
                data.put("ERAB_Setup_Succ_Rate",baseCellList.get(i).getErabSetupSuccRate());
                data.put("paging_Succ_Rate",baseCellList.get(i).getPagingSuccRate());
                data.put("Erab_Drop_Rate",baseCellList.get(i).getErabDropRate());
                data.put("Within_System_HO_Succ_Rate",baseCellList.get(i).getWithinSystemHoSuccRate());
                data.put("Cell_Utilization_Rate",baseCellList.get(i).getCellUtilizationRate());
                data.put("UL_PRB_Occupy_Rate",baseCellList.get(i).getUlPrbOccupyRate());
                data.put("DL_PRB_Occupy_Rate",baseCellList.get(i).getDlPrbOccupyRate());
                data.put("Dl_ThroughputRate_Mbps",baseCellList.get(i).getDlThroughputrateMbps());
                data.put("Ul_ThroughputRate_Mbps",baseCellList.get(i).getUlThroughputrateMbps());
                data.put("DL_User_Speed_Mbps",baseCellList.get(i).getDlUserSpeedMbps());
                data.put("UL_User_Speed_Mbps",baseCellList.get(i).getUlUserSpeedMbps());
                data.put("UE_SessionTime",baseCellList.get(i).getUeSessiontime());
                data.put("Drb_pmSessionTime",baseCellList.get(i).getDrbPmsessiontime());
                data.put("DL_Active_User_Num",baseCellList.get(i).getDlActiveUserNum());
                data.put("UL_Active_User_Num",baseCellList.get(i).getUlActiveUserNum());

                dataList.add(i,data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "search_cell_by_node",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> searchCellByNode(@RequestBody BaseCell baseCell){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseCell> baseCellList = level3Service.searchCellByNode(baseCell);
        if (baseCellList == null || baseCellList.size() == 0){
            msg = "获得baseCell错误";
        }else{
            msg = "获得baseCell成功";
            status = true;
            for (int i = 0; i < baseCellList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("cell_name",baseCellList.get(i).getCellName());
                data.put("status",baseCellList.get(i).getStatus());
                data.put("RRC_Setup_Succ_Rate",baseCellList.get(i).getRrcSetupSuccRate());
                data.put("S1_Setup_Succ_Rate",baseCellList.get(i).getS1SetupSuccRate());
                data.put("ERAB_Setup_Succ_Rate",baseCellList.get(i).getErabSetupSuccRate());
                data.put("paging_Succ_Rate",baseCellList.get(i).getPagingSuccRate());
                data.put("Erab_Drop_Rate",baseCellList.get(i).getErabDropRate());
                data.put("Within_System_HO_Succ_Rate",baseCellList.get(i).getWithinSystemHoSuccRate());
                data.put("Cell_Utilization_Rate",baseCellList.get(i).getCellUtilizationRate());
                data.put("UL_PRB_Occupy_Rate",baseCellList.get(i).getUlPrbOccupyRate());
                data.put("DL_PRB_Occupy_Rate",baseCellList.get(i).getDlPrbOccupyRate());
                data.put("Dl_ThroughputRate_Mbps",baseCellList.get(i).getDlThroughputrateMbps());
                data.put("Ul_ThroughputRate_Mbps",baseCellList.get(i).getUlThroughputrateMbps());
                data.put("DL_User_Speed_Mbps",baseCellList.get(i).getDlUserSpeedMbps());
                data.put("UL_User_Speed_Mbps",baseCellList.get(i).getUlUserSpeedMbps());
                data.put("UE_SessionTime",baseCellList.get(i).getUeSessiontime());
                data.put("Drb_pmSessionTime",baseCellList.get(i).getDrbPmsessiontime());
                data.put("DL_Active_User_Num",baseCellList.get(i).getDlActiveUserNum());
                data.put("UL_Active_User_Num",baseCellList.get(i).getUlActiveUserNum());

                dataList.add(i,data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_alarm_by_node",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> getAlarmByNode(@RequestBody BaseAlarm baseAlarm){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseAlarm> baseAlarmList = level3Service.getAlarmByNode(baseAlarm);
        if (baseAlarmList == null || baseAlarmList.size() == 0){
            msg = "获得alarmList失败";
        }else{
            msg = "获得alarmList成功";
            status = true;
            for (int i = 0; i < baseAlarmList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("node_name",baseAlarmList.get(i).getNodeName());
                data.put("mo",baseAlarmList.get(i).getMo());
                data.put("alarm_time",baseAlarmList.get(i).getAlarmTime());
                data.put("alarm_name",baseAlarmList.get(i).getAlarmName());
                data.put("alarm_name_dis",baseAlarmList.get(i).getDicAlarmName().getAlarmNameDis());
                data.put("alarm_type",baseAlarmList.get(i).getAlarmType());
                data.put("alarm_type_name",baseAlarmList.get(i).getDicAlarmType().getAlarmTypeName());

                dataList.add(i,data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_cell_quota_threshold",method = RequestMethod.GET)
    public @ResponseBody
    Map<String , Object> getCellQuotaThreshold(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<QuotaThresholdCell> quotaThresholdCellList = level3Service.getCellQuotaThreshold();
        if (quotaThresholdCellList == null || quotaThresholdCellList.size()==0){
            msg = "获得quotaThresholdCellList失败";
        }else{
            msg = "获得quotaThresholdCellList成功";
            status = true;
            for (int i = 0; i < quotaThresholdCellList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("quota_name",quotaThresholdCellList.get(i).getQuotaName());
                data.put("quota_type",quotaThresholdCellList.get(i).getQuotaType());
                data.put("threshold_1",quotaThresholdCellList.get(i).getThreshold1());
                data.put("threshold_2",quotaThresholdCellList.get(i).getThreshold2());
                data.put("threshold_3",quotaThresholdCellList.get(i).getThreshold3());
                data.put("quota_unit",quotaThresholdCellList.get(i).getQuotaUnit());

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
