package com.ecom.entity;

/**
 * Created by a7289 on 2016/11/21 0021.
 */
public interface BaseStatus {

     Integer getStatus() ;

     void setStatus(Integer status);

     String getRrcSetupSuccRate();

     void setRrcSetupSuccRate(String rrcSetupSuccRate);

     String getS1SetupSuccRate();

     void setS1SetupSuccRate(String s1SetupSuccRate);

     String getErabSetupSuccRate();

     void setErabSetupSuccRate(String erabSetupSuccRate);

     String getPagingSuccRate();

     void setPagingSuccRate(String pagingSuccRate);

     String getErabDropRate();

     void setErabDropRate(String erabDropRate);

     String getWithinSystemHoSuccRate();

     void setWithinSystemHoSuccRate(String withinSystemHoSuccRate);

     String getCellUtilizationRate();

     void setCellUtilizationRate(String cellUtilizationRate);

     String getUlPrbOccupyRate();

     void setUlPrbOccupyRate(String ulPrbOccupyRate);

     String getDlPrbOccupyRate();

     void setDlPrbOccupyRate(String dlPrbOccupyRate);

     String getDlThroughputrateMbps();

     void setDlThroughputrateMbps(String dlThroughputrateMbps);

     String getUlThroughputrateMbps();

     void setUlThroughputrateMbps(String ulThroughputrateMbps);

     String getDlUserSpeedMbps() ;

     void setDlUserSpeedMbps(String dlUserSpeedMbps);

     String getUlUserSpeedMbps();

     void setUlUserSpeedMbps(String ulUserSpeedMbps) ;

     String getUeSessiontime();

     void setUeSessiontime(String ueSessiontime);

     String getDrbPmsessiontime() ;

     void setDrbPmsessiontime(String drbPmsessiontime) ;

     String getDlActiveUserNum() ;

     void setDlActiveUserNum(String dlActiveUserNum);

     String getUlActiveUserNum() ;

     void setUlActiveUserNum(String ulActiveUserNum);

//     String getTac();
//     void setTac(String tac);
//
//     String getNodeName();
//     void setNodeName(String nodeName);
//
//     String getCellName();
//     void setCellName(String cellName);
}
