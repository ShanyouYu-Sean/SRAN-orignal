package com.ecom.entity;

import com.ecom.entity.example.BaseStation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a7289 on 2016/11/3 0003.
 */
public class BaseTac extends com.ecom.entity.example.BaseTac implements Serializable,BaseStatus{

    private List<BaseStation> baseStationList;

    public List<BaseStation> getBaseStationList() {
        return baseStationList;
    }

    public void setBaseStationList(List<BaseStation> baseStationList) {
        this.baseStationList = baseStationList;
    }

    public BaseTac(){}

//    private String nodeName;
//    private String cellName;
//
//    public String getNodeName() {
//        return nodeName;
//    }
//
//    public void setNodeName(String nodeName) {
//        this.nodeName = nodeName;
//    }
//
//    public String getCellName() {
//        return cellName;
//    }
//
//    public void setCellName(String cellName) {
//        this.cellName = cellName;
//    }
}
