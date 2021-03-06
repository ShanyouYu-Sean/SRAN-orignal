package com.ecom.entity.example;

import java.io.Serializable;
import java.util.Date;

public class Tac implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.time
     *
     * @mbggenerated
     */
    private Date time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.tac
     *
     * @mbggenerated
     */
    private String tac;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.RRC_setup_succ_Rate
     *
     * @mbggenerated
     */
    private String rrcSetupSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.S1_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    private String s1SetupSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.ERAB_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    private String erabSetupSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.paging_Succ_Rate
     *
     * @mbggenerated
     */
    private String pagingSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.Erab_Drop_Rate
     *
     * @mbggenerated
     */
    private String erabDropRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.ProcessorLoad_MAX
     *
     * @mbggenerated
     */
    private String processorloadMax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.Within_System_HO_Succ_Rate
     *
     * @mbggenerated
     */
    private String withinSystemHoSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.To_WCDMA_Ho_Succ_Rate
     *
     * @mbggenerated
     */
    private String toWcdmaHoSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.Session_Setup_Success_Rate
     *
     * @mbggenerated
     */
    private String sessionSetupSuccessRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.Cell_Utilization_Rate
     *
     * @mbggenerated
     */
    private String cellUtilizationRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.UL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    private String ulPrbOccupyRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.DL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    private String dlPrbOccupyRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.Dl_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    private String dlThroughputrateMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.Ul_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    private String ulThroughputrateMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.DL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    private String dlUserSpeedMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.UL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    private String ulUserSpeedMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.UE_SessionTime
     *
     * @mbggenerated
     */
    private String ueSessiontime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.Drb_pmSessionTime
     *
     * @mbggenerated
     */
    private String drbPmsessiontime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.DL_Active_User_Num
     *
     * @mbggenerated
     */
    private String dlActiveUserNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tac.UL_Active_User_Num
     *
     * @mbggenerated
     */
    private String ulActiveUserNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tac
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.id
     *
     * @return the value of tac.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.id
     *
     * @param id the value for tac.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.status
     *
     * @return the value of tac.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.status
     *
     * @param status the value for tac.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.time
     *
     * @return the value of tac.time
     *
     * @mbggenerated
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.time
     *
     * @param time the value for tac.time
     *
     * @mbggenerated
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.tac
     *
     * @return the value of tac.tac
     *
     * @mbggenerated
     */
    public String getTac() {
        return tac;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.tac
     *
     * @param tac the value for tac.tac
     *
     * @mbggenerated
     */
    public void setTac(String tac) {
        this.tac = tac == null ? null : tac.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.RRC_setup_succ_Rate
     *
     * @return the value of tac.RRC_setup_succ_Rate
     *
     * @mbggenerated
     */
    public String getRrcSetupSuccRate() {
        return rrcSetupSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.RRC_setup_succ_Rate
     *
     * @param rrcSetupSuccRate the value for tac.RRC_setup_succ_Rate
     *
     * @mbggenerated
     */
    public void setRrcSetupSuccRate(String rrcSetupSuccRate) {
        this.rrcSetupSuccRate = rrcSetupSuccRate == null ? null : rrcSetupSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.S1_Setup_Succ_Rate
     *
     * @return the value of tac.S1_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public String getS1SetupSuccRate() {
        return s1SetupSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.S1_Setup_Succ_Rate
     *
     * @param s1SetupSuccRate the value for tac.S1_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public void setS1SetupSuccRate(String s1SetupSuccRate) {
        this.s1SetupSuccRate = s1SetupSuccRate == null ? null : s1SetupSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.ERAB_Setup_Succ_Rate
     *
     * @return the value of tac.ERAB_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public String getErabSetupSuccRate() {
        return erabSetupSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.ERAB_Setup_Succ_Rate
     *
     * @param erabSetupSuccRate the value for tac.ERAB_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public void setErabSetupSuccRate(String erabSetupSuccRate) {
        this.erabSetupSuccRate = erabSetupSuccRate == null ? null : erabSetupSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.paging_Succ_Rate
     *
     * @return the value of tac.paging_Succ_Rate
     *
     * @mbggenerated
     */
    public String getPagingSuccRate() {
        return pagingSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.paging_Succ_Rate
     *
     * @param pagingSuccRate the value for tac.paging_Succ_Rate
     *
     * @mbggenerated
     */
    public void setPagingSuccRate(String pagingSuccRate) {
        this.pagingSuccRate = pagingSuccRate == null ? null : pagingSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.Erab_Drop_Rate
     *
     * @return the value of tac.Erab_Drop_Rate
     *
     * @mbggenerated
     */
    public String getErabDropRate() {
        return erabDropRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.Erab_Drop_Rate
     *
     * @param erabDropRate the value for tac.Erab_Drop_Rate
     *
     * @mbggenerated
     */
    public void setErabDropRate(String erabDropRate) {
        this.erabDropRate = erabDropRate == null ? null : erabDropRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.ProcessorLoad_MAX
     *
     * @return the value of tac.ProcessorLoad_MAX
     *
     * @mbggenerated
     */
    public String getProcessorloadMax() {
        return processorloadMax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.ProcessorLoad_MAX
     *
     * @param processorloadMax the value for tac.ProcessorLoad_MAX
     *
     * @mbggenerated
     */
    public void setProcessorloadMax(String processorloadMax) {
        this.processorloadMax = processorloadMax == null ? null : processorloadMax.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.Within_System_HO_Succ_Rate
     *
     * @return the value of tac.Within_System_HO_Succ_Rate
     *
     * @mbggenerated
     */
    public String getWithinSystemHoSuccRate() {
        return withinSystemHoSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.Within_System_HO_Succ_Rate
     *
     * @param withinSystemHoSuccRate the value for tac.Within_System_HO_Succ_Rate
     *
     * @mbggenerated
     */
    public void setWithinSystemHoSuccRate(String withinSystemHoSuccRate) {
        this.withinSystemHoSuccRate = withinSystemHoSuccRate == null ? null : withinSystemHoSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.To_WCDMA_Ho_Succ_Rate
     *
     * @return the value of tac.To_WCDMA_Ho_Succ_Rate
     *
     * @mbggenerated
     */
    public String getToWcdmaHoSuccRate() {
        return toWcdmaHoSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.To_WCDMA_Ho_Succ_Rate
     *
     * @param toWcdmaHoSuccRate the value for tac.To_WCDMA_Ho_Succ_Rate
     *
     * @mbggenerated
     */
    public void setToWcdmaHoSuccRate(String toWcdmaHoSuccRate) {
        this.toWcdmaHoSuccRate = toWcdmaHoSuccRate == null ? null : toWcdmaHoSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.Session_Setup_Success_Rate
     *
     * @return the value of tac.Session_Setup_Success_Rate
     *
     * @mbggenerated
     */
    public String getSessionSetupSuccessRate() {
        return sessionSetupSuccessRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.Session_Setup_Success_Rate
     *
     * @param sessionSetupSuccessRate the value for tac.Session_Setup_Success_Rate
     *
     * @mbggenerated
     */
    public void setSessionSetupSuccessRate(String sessionSetupSuccessRate) {
        this.sessionSetupSuccessRate = sessionSetupSuccessRate == null ? null : sessionSetupSuccessRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.Cell_Utilization_Rate
     *
     * @return the value of tac.Cell_Utilization_Rate
     *
     * @mbggenerated
     */
    public String getCellUtilizationRate() {
        return cellUtilizationRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.Cell_Utilization_Rate
     *
     * @param cellUtilizationRate the value for tac.Cell_Utilization_Rate
     *
     * @mbggenerated
     */
    public void setCellUtilizationRate(String cellUtilizationRate) {
        this.cellUtilizationRate = cellUtilizationRate == null ? null : cellUtilizationRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.UL_PRB_Occupy_Rate
     *
     * @return the value of tac.UL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public String getUlPrbOccupyRate() {
        return ulPrbOccupyRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.UL_PRB_Occupy_Rate
     *
     * @param ulPrbOccupyRate the value for tac.UL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public void setUlPrbOccupyRate(String ulPrbOccupyRate) {
        this.ulPrbOccupyRate = ulPrbOccupyRate == null ? null : ulPrbOccupyRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.DL_PRB_Occupy_Rate
     *
     * @return the value of tac.DL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public String getDlPrbOccupyRate() {
        return dlPrbOccupyRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.DL_PRB_Occupy_Rate
     *
     * @param dlPrbOccupyRate the value for tac.DL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public void setDlPrbOccupyRate(String dlPrbOccupyRate) {
        this.dlPrbOccupyRate = dlPrbOccupyRate == null ? null : dlPrbOccupyRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.Dl_ThroughputRate_Mbps
     *
     * @return the value of tac.Dl_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public String getDlThroughputrateMbps() {
        return dlThroughputrateMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.Dl_ThroughputRate_Mbps
     *
     * @param dlThroughputrateMbps the value for tac.Dl_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public void setDlThroughputrateMbps(String dlThroughputrateMbps) {
        this.dlThroughputrateMbps = dlThroughputrateMbps == null ? null : dlThroughputrateMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.Ul_ThroughputRate_Mbps
     *
     * @return the value of tac.Ul_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public String getUlThroughputrateMbps() {
        return ulThroughputrateMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.Ul_ThroughputRate_Mbps
     *
     * @param ulThroughputrateMbps the value for tac.Ul_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public void setUlThroughputrateMbps(String ulThroughputrateMbps) {
        this.ulThroughputrateMbps = ulThroughputrateMbps == null ? null : ulThroughputrateMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.DL_User_Speed_Mbps
     *
     * @return the value of tac.DL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public String getDlUserSpeedMbps() {
        return dlUserSpeedMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.DL_User_Speed_Mbps
     *
     * @param dlUserSpeedMbps the value for tac.DL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public void setDlUserSpeedMbps(String dlUserSpeedMbps) {
        this.dlUserSpeedMbps = dlUserSpeedMbps == null ? null : dlUserSpeedMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.UL_User_Speed_Mbps
     *
     * @return the value of tac.UL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public String getUlUserSpeedMbps() {
        return ulUserSpeedMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.UL_User_Speed_Mbps
     *
     * @param ulUserSpeedMbps the value for tac.UL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public void setUlUserSpeedMbps(String ulUserSpeedMbps) {
        this.ulUserSpeedMbps = ulUserSpeedMbps == null ? null : ulUserSpeedMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.UE_SessionTime
     *
     * @return the value of tac.UE_SessionTime
     *
     * @mbggenerated
     */
    public String getUeSessiontime() {
        return ueSessiontime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.UE_SessionTime
     *
     * @param ueSessiontime the value for tac.UE_SessionTime
     *
     * @mbggenerated
     */
    public void setUeSessiontime(String ueSessiontime) {
        this.ueSessiontime = ueSessiontime == null ? null : ueSessiontime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.Drb_pmSessionTime
     *
     * @return the value of tac.Drb_pmSessionTime
     *
     * @mbggenerated
     */
    public String getDrbPmsessiontime() {
        return drbPmsessiontime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.Drb_pmSessionTime
     *
     * @param drbPmsessiontime the value for tac.Drb_pmSessionTime
     *
     * @mbggenerated
     */
    public void setDrbPmsessiontime(String drbPmsessiontime) {
        this.drbPmsessiontime = drbPmsessiontime == null ? null : drbPmsessiontime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.DL_Active_User_Num
     *
     * @return the value of tac.DL_Active_User_Num
     *
     * @mbggenerated
     */
    public String getDlActiveUserNum() {
        return dlActiveUserNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.DL_Active_User_Num
     *
     * @param dlActiveUserNum the value for tac.DL_Active_User_Num
     *
     * @mbggenerated
     */
    public void setDlActiveUserNum(String dlActiveUserNum) {
        this.dlActiveUserNum = dlActiveUserNum == null ? null : dlActiveUserNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tac.UL_Active_User_Num
     *
     * @return the value of tac.UL_Active_User_Num
     *
     * @mbggenerated
     */
    public String getUlActiveUserNum() {
        return ulActiveUserNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tac.UL_Active_User_Num
     *
     * @param ulActiveUserNum the value for tac.UL_Active_User_Num
     *
     * @mbggenerated
     */
    public void setUlActiveUserNum(String ulActiveUserNum) {
        this.ulActiveUserNum = ulActiveUserNum == null ? null : ulActiveUserNum.trim();
    }
}