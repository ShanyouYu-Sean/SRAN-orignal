package com.ecom.util;

import com.ecom.entity.*;
import com.ecom.service.StatusService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by a7289 on 2016/11/18 0018.
 */
@Component
public class StatusUtil {
    @Resource
    private StatusService service;
    public static StatusService statusService;
    @PostConstruct
    public void init(){
        StatusUtil.statusService = service;
    }

    public static void tacStatus() {
        List<QuotaThresholdTac> quotaThresholdTacList = statusService.getTacQuotaThreshold();
        List<BaseTac> baseTacList = statusService.getTacStatus();
        for (int i = 0; i < baseTacList.size(); i++) {
            List<Integer> statusList = new ArrayList();
            for (int j = 0; j < quotaThresholdTacList.size(); j++) {
                Integer status = modStatus(quotaThresholdTacList.get(j),baseTacList.get(i));
                statusList.add(status);
            }
            Integer maxStatus = Collections.max(statusList);
            BaseTac baseTac = new BaseTac();
            baseTac.setStatus(maxStatus);
            baseTac.setTac(baseTacList.get(i).getTac());
            statusService.modTacStatus(baseTac);
        }
    }

    public static void nodeStatus() {
        List<QuotaThresholdNode> quotaThresholdNodeList = statusService.getNodeQuotaThreshold();
        List<BaseNode> baseNodeList = statusService.getNodeStatus();
        for (int i = 0; i < baseNodeList.size(); i++) {
            List<Integer> statusList = new ArrayList();
            for (int j = 0; j < quotaThresholdNodeList.size(); j++) {
                Integer status = modStatus(quotaThresholdNodeList.get(j),baseNodeList.get(i));
                statusList.add(status);
            }
            Integer maxStatus = Collections.max(statusList);
            BaseNode baseNode = new BaseNode();
            baseNode.setStatus(maxStatus);
            baseNode.setNodeName(baseNodeList.get(i).getNodeName());
            statusService.modNodeStatus(baseNode);
        }
    }

    public static void cellStatus() {
        List<QuotaThresholdCell> quotaThresholdCellList = statusService.getCellQuotaThreshold();
        List<BaseCell> baseCellList = statusService.getCellStatus();
        for (int i = 0; i < baseCellList.size(); i++) {
            List<Integer> statusList = new ArrayList();
            for (int j = 0; j < quotaThresholdCellList.size(); j++) {
                Integer status = modStatus(quotaThresholdCellList.get(j),baseCellList.get(i));
                statusList.add(status);
            }
            Integer maxStatus = Collections.max(statusList);
            BaseCell baseCell = new BaseCell();
            baseCell.setStatus(maxStatus);
            baseCell.setCellName(baseCellList.get(i).getCellName());
            statusService.modCellStatus(baseCell);
        }
    }

    public static void compare(BaseQuota baseQuota,String baseName,Integer status){
        if (baseQuota.getQuotaType() == 0){
            if (baseQuota.getQuotaUnit() == 0){
                if(Double.valueOf(baseName) <= Double.valueOf(baseQuota.getThreshold3())) {
                    status = 3;
                }else if(Double.valueOf(baseName) <= Double.valueOf(baseQuota.getThreshold2())) {
                    status = 2;
                }else if(Double.valueOf(baseName) <= Double.valueOf(baseQuota.getThreshold1())) {
                    status = 1;
                }else{
                    status = 0;
                }
            }else {
                if(Double.valueOf(baseName) >= Double.valueOf(baseQuota.getThreshold3())) {
                    status = 3;
                }else if(Double.valueOf(baseName) >= Double.valueOf(baseQuota.getThreshold2())) {
                    status = 2;
                }else if(Double.valueOf(baseName) >= Double.valueOf(baseQuota.getThreshold1())) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
        }else {
            if (baseQuota.getQuotaUnit() == 0){
                if((int)((double)Double.valueOf(baseName)) <= (int)((double)Double.valueOf(baseQuota.getThreshold3()))) {
                    status = 3;
                }else if((int)((double)Double.valueOf(baseName)) <= (int)((double)Double.valueOf(baseQuota.getThreshold2()))) {
                    status = 2;
                }else if((int)((double)Double.valueOf(baseName)) <= (int)((double)Double.valueOf(baseQuota.getThreshold1()))) {
                    status = 1;
                }else{
                    status = 0;
                }
            }else {
                if((int)((double)Double.valueOf(baseName)) >= (int)((double)Double.valueOf(baseQuota.getThreshold3()))) {
                    status = 3;
                }else if((int)((double)Double.valueOf(baseName)) >= (int)((double)Double.valueOf(baseQuota.getThreshold2()))) {
                    status = 2;
                }else if((int)((double)Double.valueOf(baseName)) >= (int)((double)Double.valueOf(baseQuota.getThreshold1()))) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
        }
    }

    public static Integer modStatus(BaseQuota baseQuota, BaseStatus baseStatus) {
        Integer status = 0;
        if (baseQuota.getQuotaName().equals("Session_Setup_Success_Rate")){
//            compare(baseQuota,baseStatus.getRrcSetupSuccRate());
        }else if (baseQuota.getQuotaName().equals("RRC_setup_succ_Rate")){
            compare(baseQuota,baseStatus.getRrcSetupSuccRate(),status);
        }else if (baseQuota.getQuotaName().equals("ERAB_Setup_Succ_Rate")){
            compare(baseQuota,baseStatus.getErabSetupSuccRate(),status);
        }else if (baseQuota.getQuotaName().equals("paging_Succ_Rate")){
            compare(baseQuota,baseStatus.getPagingSuccRate(),status);
        }else if (baseQuota.getQuotaName().equals("Cell_Utilization_Rate")){
            compare(baseQuota,baseStatus.getCellUtilizationRate(),status);
        }else if (baseQuota.getQuotaName().equals("Within_System_HO_Succ_Rate")){
            compare(baseQuota,baseStatus.getWithinSystemHoSuccRate(),status);
        }else if (baseQuota.getQuotaName().equals("Erab_Drop_Rate")){
            compare(baseQuota,baseStatus.getErabDropRate(),status);
        }else if (baseQuota.getQuotaName().equals("DL_PRB_Occupy_Rate")){
            compare(baseQuota,baseStatus.getDlPrbOccupyRate(),status);
        }else if (baseQuota.getQuotaName().equals("UL_PRB_Occupy_Rate")){
            compare(baseQuota,baseStatus.getUlPrbOccupyRate(),status);
        }else if (baseQuota.getQuotaName().equals("Dl_ThroughputRate_Mbps")){
            compare(baseQuota,baseStatus.getDlThroughputrateMbps(),status);
        }else if (baseQuota.getQuotaName().equals("Ul_ThroughputRate_Mbps")){
            compare(baseQuota,baseStatus.getUlThroughputrateMbps(),status);
        }else if (baseQuota.getQuotaName().equals("DL_User_Speed_Mbps")){
            compare(baseQuota,baseStatus.getDlUserSpeedMbps(),status);
        }else if (baseQuota.getQuotaName().equals("UL_User_Speed_Mbps")){
            compare(baseQuota,baseStatus.getUlUserSpeedMbps(),status);
        }else if (baseQuota.getQuotaName().equals("UE_SessionTime")){
            compare(baseQuota,baseStatus.getUeSessiontime(),status);
        }else if (baseQuota.getQuotaName().equals("Drb_pmSessionTime")){
            compare(baseQuota,baseStatus.getDrbPmsessiontime(),status);
        }else if (baseQuota.getQuotaName().equals("DL_Active_User_Num")){
            compare(baseQuota,baseStatus.getDlActiveUserNum(),status);
        }else if (baseQuota.getQuotaName().equals("UL_Active_User_Num")){
            compare(baseQuota,baseStatus.getUlActiveUserNum(),status);
        }
        return status;
    }
}
