package com.ecom.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a7289 on 2016/11/8 0008.
 */
public class DicAlarmType extends com.ecom.entity.example.DicAlarmType implements Serializable {
    private List<BaseAlarm> baseAlarmList;

    public List<BaseAlarm> getBaseAlarmList() {
        return baseAlarmList;
    }

    public void setBaseAlarmList(List<BaseAlarm> baseAlarmList) {
        this.baseAlarmList = baseAlarmList;
    }

    public DicAlarmType(){}
}
