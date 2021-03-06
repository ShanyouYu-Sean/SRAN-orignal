package com.ecom.entity.example;

import java.io.Serializable;

public class BaseCell implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.cell_name
     *
     * @mbggenerated
     */
    private String cellName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.RRC_setup_succ_Rate
     *
     * @mbggenerated
     */
    private String rrcSetupSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.S1_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    private String s1SetupSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.ERAB_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    private String erabSetupSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.paging_Succ_Rate
     *
     * @mbggenerated
     */
    private String pagingSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.Erab_Drop_Rate
     *
     * @mbggenerated
     */
    private String erabDropRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.Within_System_HO_Succ_Rate
     *
     * @mbggenerated
     */
    private String withinSystemHoSuccRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.Cell_Utilization_Rate
     *
     * @mbggenerated
     */
    private String cellUtilizationRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.UL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    private String ulPrbOccupyRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.DL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    private String dlPrbOccupyRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.Dl_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    private String dlThroughputrateMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.Ul_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    private String ulThroughputrateMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.DL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    private String dlUserSpeedMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.UL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    private String ulUserSpeedMbps;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.UE_SessionTime
     *
     * @mbggenerated
     */
    private String ueSessiontime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.Drb_pmSessionTime
     *
     * @mbggenerated
     */
    private String drbPmsessiontime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.DL_Active_User_Num
     *
     * @mbggenerated
     */
    private String dlActiveUserNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_cell.UL_Active_User_Num
     *
     * @mbggenerated
     */
    private String ulActiveUserNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table base_cell
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.cell_name
     *
     * @return the value of base_cell.cell_name
     *
     * @mbggenerated
     */
    public String getCellName() {
        return cellName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.cell_name
     *
     * @param cellName the value for base_cell.cell_name
     *
     * @mbggenerated
     */
    public void setCellName(String cellName) {
        this.cellName = cellName == null ? null : cellName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.status
     *
     * @return the value of base_cell.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.status
     *
     * @param status the value for base_cell.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.RRC_setup_succ_Rate
     *
     * @return the value of base_cell.RRC_setup_succ_Rate
     *
     * @mbggenerated
     */
    public String getRrcSetupSuccRate() {
        return rrcSetupSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.RRC_setup_succ_Rate
     *
     * @param rrcSetupSuccRate the value for base_cell.RRC_setup_succ_Rate
     *
     * @mbggenerated
     */
    public void setRrcSetupSuccRate(String rrcSetupSuccRate) {
        this.rrcSetupSuccRate = rrcSetupSuccRate == null ? null : rrcSetupSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.S1_Setup_Succ_Rate
     *
     * @return the value of base_cell.S1_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public String getS1SetupSuccRate() {
        return s1SetupSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.S1_Setup_Succ_Rate
     *
     * @param s1SetupSuccRate the value for base_cell.S1_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public void setS1SetupSuccRate(String s1SetupSuccRate) {
        this.s1SetupSuccRate = s1SetupSuccRate == null ? null : s1SetupSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.ERAB_Setup_Succ_Rate
     *
     * @return the value of base_cell.ERAB_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public String getErabSetupSuccRate() {
        return erabSetupSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.ERAB_Setup_Succ_Rate
     *
     * @param erabSetupSuccRate the value for base_cell.ERAB_Setup_Succ_Rate
     *
     * @mbggenerated
     */
    public void setErabSetupSuccRate(String erabSetupSuccRate) {
        this.erabSetupSuccRate = erabSetupSuccRate == null ? null : erabSetupSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.paging_Succ_Rate
     *
     * @return the value of base_cell.paging_Succ_Rate
     *
     * @mbggenerated
     */
    public String getPagingSuccRate() {
        return pagingSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.paging_Succ_Rate
     *
     * @param pagingSuccRate the value for base_cell.paging_Succ_Rate
     *
     * @mbggenerated
     */
    public void setPagingSuccRate(String pagingSuccRate) {
        this.pagingSuccRate = pagingSuccRate == null ? null : pagingSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.Erab_Drop_Rate
     *
     * @return the value of base_cell.Erab_Drop_Rate
     *
     * @mbggenerated
     */
    public String getErabDropRate() {
        return erabDropRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.Erab_Drop_Rate
     *
     * @param erabDropRate the value for base_cell.Erab_Drop_Rate
     *
     * @mbggenerated
     */
    public void setErabDropRate(String erabDropRate) {
        this.erabDropRate = erabDropRate == null ? null : erabDropRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.Within_System_HO_Succ_Rate
     *
     * @return the value of base_cell.Within_System_HO_Succ_Rate
     *
     * @mbggenerated
     */
    public String getWithinSystemHoSuccRate() {
        return withinSystemHoSuccRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.Within_System_HO_Succ_Rate
     *
     * @param withinSystemHoSuccRate the value for base_cell.Within_System_HO_Succ_Rate
     *
     * @mbggenerated
     */
    public void setWithinSystemHoSuccRate(String withinSystemHoSuccRate) {
        this.withinSystemHoSuccRate = withinSystemHoSuccRate == null ? null : withinSystemHoSuccRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.Cell_Utilization_Rate
     *
     * @return the value of base_cell.Cell_Utilization_Rate
     *
     * @mbggenerated
     */
    public String getCellUtilizationRate() {
        return cellUtilizationRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.Cell_Utilization_Rate
     *
     * @param cellUtilizationRate the value for base_cell.Cell_Utilization_Rate
     *
     * @mbggenerated
     */
    public void setCellUtilizationRate(String cellUtilizationRate) {
        this.cellUtilizationRate = cellUtilizationRate == null ? null : cellUtilizationRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.UL_PRB_Occupy_Rate
     *
     * @return the value of base_cell.UL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public String getUlPrbOccupyRate() {
        return ulPrbOccupyRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.UL_PRB_Occupy_Rate
     *
     * @param ulPrbOccupyRate the value for base_cell.UL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public void setUlPrbOccupyRate(String ulPrbOccupyRate) {
        this.ulPrbOccupyRate = ulPrbOccupyRate == null ? null : ulPrbOccupyRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.DL_PRB_Occupy_Rate
     *
     * @return the value of base_cell.DL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public String getDlPrbOccupyRate() {
        return dlPrbOccupyRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.DL_PRB_Occupy_Rate
     *
     * @param dlPrbOccupyRate the value for base_cell.DL_PRB_Occupy_Rate
     *
     * @mbggenerated
     */
    public void setDlPrbOccupyRate(String dlPrbOccupyRate) {
        this.dlPrbOccupyRate = dlPrbOccupyRate == null ? null : dlPrbOccupyRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.Dl_ThroughputRate_Mbps
     *
     * @return the value of base_cell.Dl_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public String getDlThroughputrateMbps() {
        return dlThroughputrateMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.Dl_ThroughputRate_Mbps
     *
     * @param dlThroughputrateMbps the value for base_cell.Dl_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public void setDlThroughputrateMbps(String dlThroughputrateMbps) {
        this.dlThroughputrateMbps = dlThroughputrateMbps == null ? null : dlThroughputrateMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.Ul_ThroughputRate_Mbps
     *
     * @return the value of base_cell.Ul_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public String getUlThroughputrateMbps() {
        return ulThroughputrateMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.Ul_ThroughputRate_Mbps
     *
     * @param ulThroughputrateMbps the value for base_cell.Ul_ThroughputRate_Mbps
     *
     * @mbggenerated
     */
    public void setUlThroughputrateMbps(String ulThroughputrateMbps) {
        this.ulThroughputrateMbps = ulThroughputrateMbps == null ? null : ulThroughputrateMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.DL_User_Speed_Mbps
     *
     * @return the value of base_cell.DL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public String getDlUserSpeedMbps() {
        return dlUserSpeedMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.DL_User_Speed_Mbps
     *
     * @param dlUserSpeedMbps the value for base_cell.DL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public void setDlUserSpeedMbps(String dlUserSpeedMbps) {
        this.dlUserSpeedMbps = dlUserSpeedMbps == null ? null : dlUserSpeedMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.UL_User_Speed_Mbps
     *
     * @return the value of base_cell.UL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public String getUlUserSpeedMbps() {
        return ulUserSpeedMbps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.UL_User_Speed_Mbps
     *
     * @param ulUserSpeedMbps the value for base_cell.UL_User_Speed_Mbps
     *
     * @mbggenerated
     */
    public void setUlUserSpeedMbps(String ulUserSpeedMbps) {
        this.ulUserSpeedMbps = ulUserSpeedMbps == null ? null : ulUserSpeedMbps.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.UE_SessionTime
     *
     * @return the value of base_cell.UE_SessionTime
     *
     * @mbggenerated
     */
    public String getUeSessiontime() {
        return ueSessiontime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.UE_SessionTime
     *
     * @param ueSessiontime the value for base_cell.UE_SessionTime
     *
     * @mbggenerated
     */
    public void setUeSessiontime(String ueSessiontime) {
        this.ueSessiontime = ueSessiontime == null ? null : ueSessiontime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.Drb_pmSessionTime
     *
     * @return the value of base_cell.Drb_pmSessionTime
     *
     * @mbggenerated
     */
    public String getDrbPmsessiontime() {
        return drbPmsessiontime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.Drb_pmSessionTime
     *
     * @param drbPmsessiontime the value for base_cell.Drb_pmSessionTime
     *
     * @mbggenerated
     */
    public void setDrbPmsessiontime(String drbPmsessiontime) {
        this.drbPmsessiontime = drbPmsessiontime == null ? null : drbPmsessiontime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.DL_Active_User_Num
     *
     * @return the value of base_cell.DL_Active_User_Num
     *
     * @mbggenerated
     */
    public String getDlActiveUserNum() {
        return dlActiveUserNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.DL_Active_User_Num
     *
     * @param dlActiveUserNum the value for base_cell.DL_Active_User_Num
     *
     * @mbggenerated
     */
    public void setDlActiveUserNum(String dlActiveUserNum) {
        this.dlActiveUserNum = dlActiveUserNum == null ? null : dlActiveUserNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_cell.UL_Active_User_Num
     *
     * @return the value of base_cell.UL_Active_User_Num
     *
     * @mbggenerated
     */
    public String getUlActiveUserNum() {
        return ulActiveUserNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_cell.UL_Active_User_Num
     *
     * @param ulActiveUserNum the value for base_cell.UL_Active_User_Num
     *
     * @mbggenerated
     */
    public void setUlActiveUserNum(String ulActiveUserNum) {
        this.ulActiveUserNum = ulActiveUserNum == null ? null : ulActiveUserNum.trim();
    }
}