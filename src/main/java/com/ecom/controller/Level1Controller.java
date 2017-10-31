package com.ecom.controller;

import com.ecom.entity.*;
import com.ecom.service.Level1Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a7289 on 2016/11/2 0002.
 */
@Controller
@RequestMapping("/lte")
public class Level1Controller {
    @Resource
    private Level1Service level1Service;

    @RequestMapping(value="level1")
    public String level1Init(){
        return "html/level1";
    }

    @RequestMapping(value = "get_group_station_by_tac",method = RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> getGroupStationByTac(@RequestBody BaseTac baseTac){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseTac> baseTacList = level1Service.getGroupStationByTac(baseTac);
        if (baseTacList == null || baseTacList.size() == 0){
            msg = "获得GroupStation失败";
        }else{
            msg = "获得GroupStation成功";
            status = true;
            for (int i = 0; i < baseTacList.size(); i++) {
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("tac", baseTacList.get(i).getTac());
                data.put("status", baseTacList.get(i).getStatus());
                data.put("RRC_Setup_Succ_Rate", baseTacList.get(i).getRrcSetupSuccRate());
                data.put("S1_Setup_Succ_Rate", baseTacList.get(i).getS1SetupSuccRate());
                data.put("ERAB_Setup_Succ_Rate", baseTacList.get(i).getErabSetupSuccRate());
                data.put("paging_Succ_Rate", baseTacList.get(i).getPagingSuccRate());
                data.put("Erab_Drop_Rate", baseTacList.get(i).getErabDropRate());
                data.put("Within_System_HO_Succ_Rate", baseTacList.get(i).getWithinSystemHoSuccRate());
                data.put("Cell_Utilization_Rate", baseTacList.get(i).getCellUtilizationRate());
                data.put("UL_PRB_Occupy_Rate", baseTacList.get(i).getUlPrbOccupyRate());
                data.put("DL_PRB_Occupy_Rate", baseTacList.get(i).getDlPrbOccupyRate());
                data.put("Dl_ThroughputRate_Mbps", baseTacList.get(i).getDlThroughputrateMbps());
                data.put("Ul_ThroughputRate_Mbps", baseTacList.get(i).getUlThroughputrateMbps());
                data.put("DL_User_Speed_Mbps", baseTacList.get(i).getDlUserSpeedMbps());
                data.put("UL_User_Speed_Mbps", baseTacList.get(i).getUlUserSpeedMbps());
                data.put("UE_SessionTime", baseTacList.get(i).getUeSessiontime());
                data.put("Drb_pmSessionTime", baseTacList.get(i).getDrbPmsessiontime());
                data.put("DL_Active_User_Num", baseTacList.get(i).getDlActiveUserNum());
                data.put("UL_Active_User_Num", baseTacList.get(i).getUlActiveUserNum());

                dataList.add(i,data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "search_group_station", method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> searchGroupStation(@RequestBody BaseStation baseStation){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseTac> groupStationList = level1Service.searchGroupStation(baseStation);
        if (groupStationList == null || groupStationList.size()==0){
            msg = "搜索groupStation错误";
        }else{
            msg = "搜索groupStation成功";
            status = true;
            for (int i = 0; i < groupStationList.size(); i++){
                    Map<String , Object> data = new HashMap<String , Object>();
                    data.put("tac",groupStationList.get(i).getTac());
                    data.put("status",groupStationList.get(i).getStatus());
                    data.put("RRC_Setup_Succ_Rate",groupStationList.get(i).getRrcSetupSuccRate());
                    data.put("S1_Setup_Succ_Rate",groupStationList.get(i).getS1SetupSuccRate());
                    data.put("ERAB_Setup_Succ_Rate",groupStationList.get(i).getErabSetupSuccRate());
                    data.put("paging_Succ_Rate",groupStationList.get(i).getPagingSuccRate());
                    data.put("Erab_Drop_Rate",groupStationList.get(i).getErabDropRate());
                    data.put("Within_System_HO_Succ_Rate",groupStationList.get(i).getWithinSystemHoSuccRate());
                    data.put("Cell_Utilization_Rate",groupStationList.get(i).getCellUtilizationRate());
                    data.put("UL_PRB_Occupy_Rate",groupStationList.get(i).getUlPrbOccupyRate());
                    data.put("DL_PRB_Occupy_Rate",groupStationList.get(i).getDlPrbOccupyRate());
                    data.put("Dl_ThroughputRate_Mbps",groupStationList.get(i).getDlThroughputrateMbps());
                    data.put("Ul_ThroughputRate_Mbps",groupStationList.get(i).getUlThroughputrateMbps());
                    data.put("DL_User_Speed_Mbps",groupStationList.get(i).getDlUserSpeedMbps());
                    data.put("UL_User_Speed_Mbps",groupStationList.get(i).getUlUserSpeedMbps());
                    data.put("UE_SessionTime",groupStationList.get(i).getUeSessiontime());
                    data.put("Drb_pmSessionTime",groupStationList.get(i).getDrbPmsessiontime());
                    data.put("DL_Active_User_Num",groupStationList.get(i).getDlActiveUserNum());
                    data.put("UL_Active_User_Num",groupStationList.get(i).getUlActiveUserNum());

                dataList.add(i,data);

            }

        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_user_role_tac",method = RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> getUserRoleTac(@RequestBody User user){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Role userRoleTac = level1Service.getUserRoleTac(user);
        if (userRoleTac != null){
            data.put("role_tac",userRoleTac.getRoleTac());
            msg = "获得用户角色成功";
            status = true;
        }else{
//            data.put("role_tac","");
            msg = "获得用户角色失败";
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_update_time",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> getUpdataTime(){
        boolean status = true;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Tac tac = level1Service.getUpdateTime();
        if (tac != null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String time = sdf.format(tac.getTime());
            data.put("time",time);
            msg="获得更新时间成功";
        }else{
            msg="获得更新时间失败";
            data.put("time","");
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);
        return jsonResult;

    }

    @RequestMapping(value = "get_tac_quota_threshold",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> getTacQuotaThreshold(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<QuotaThresholdTac> quotaThresholdTacList = level1Service.getTacQuotaThreshold();
        if (quotaThresholdTacList == null || quotaThresholdTacList.size() == 0){
            msg = "获得QuotaThresholdTac失败";
        }else{
            msg = "获得QuotaThresholdTac成功";
            status = true;
            for (int i = 0; i < quotaThresholdTacList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("quota_name",quotaThresholdTacList.get(i).getQuotaName());
                data.put("quota_type",quotaThresholdTacList.get(i).getQuotaType());
                data.put("threshold_1",quotaThresholdTacList.get(i).getThreshold1());
                data.put("threshold_2",quotaThresholdTacList.get(i).getThreshold2());
                data.put("threshold_3",quotaThresholdTacList.get(i).getThreshold3());
                data.put("quota_unit",quotaThresholdTacList.get(i).getQuotaUnit());

                dataList.add(i,data);
            }
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_tac_base_station",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> getTacBaseStation(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseStation> baseStationList = level1Service.getTacBaseStation();
        if (baseStationList == null || baseStationList.size() == 0){
            msg = "获得TacBaseStation失败";
        }else{
            msg = "获得TacBaseStation成功";
            status = true;
            for (int i = 0; i < baseStationList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("tac", baseStationList.get(i).getTac());
                data.put("latitude",baseStationList.get(i).getLatitude());
                data.put("longitude",baseStationList.get(i).getLongitude());

                dataList.add(i,data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_max_station_by_tac",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getMaxStationByTac(@RequestBody BaseStation baseStation){
        boolean status = false;
        String msg = "";
//        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer maxStation = level1Service.getMaxStationByTac(baseStation);
        if (maxStation == null || maxStation == 0){
            msg = "获得maxStation失败";
        }else{
            msg = "获得maxStation成功";
            status = true;
            data.put("maxStation",maxStation);
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_base_station_by_tac",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getBaseStationByTac(@RequestBody BaseStation baseStation){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseStation> baseStationList = level1Service.getBaseStationByTac(baseStation);
        if (baseStationList == null || baseStationList.size() == 0){
            msg = "获得BaseStationByTac失败";
        }else{
            msg = "获得BaseStationByTac成功";
            status = true;
            for (int i = 0; i < baseStationList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("tac", baseStationList.get(i).getTac());
                data.put("latitude",baseStationList.get(i).getLatitude());
                data.put("longitude",baseStationList.get(i).getLongitude());
                data.put("status",baseStationList.get(i).getBaseTac().getStatus());

                dataList.add(i,data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_user_important",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getUserImportant(@RequestBody UserImportant userImportant){
        boolean status = false;
        String msg = "";
//        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        UserImportant currentUserImportant = level1Service.getUserImportant(userImportant);
        if (currentUserImportant == null){
            msg = "获得UserImportant失败";
        }else{
            msg = "获得UserImportant成功";
            status = true;
            if (currentUserImportant.getImportantTac() == null){
                currentUserImportant.setImportantTac("");
            }
            if (currentUserImportant.getImportantNode() == null){
                currentUserImportant.setImportantNode("");
            }
            data.put("important_tac",currentUserImportant.getImportantTac());
            data.put("important_node",currentUserImportant.getImportantNode());
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_important_node", method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> getImportantNode(@RequestBody BaseStation baseStation){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseStation> baseStationList = level1Service.getImportantNode(baseStation);
        if (baseStationList == null || baseStationList.size()==0){
            msg = "获得重点关注node错误";
        }else{
            msg = "获得重点关注node成功";
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
}
