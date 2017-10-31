package com.ecom.entity;

import java.io.Serializable;

/**
 * Created by a7289 on 2016/11/8 0008.
 */
public class BaseAlarm extends com.ecom.entity.example.BaseAlarm implements Serializable {
    private DicAlarmName dicAlarmName;
    private DicAlarmType dicAlarmType;

    public DicAlarmName getDicAlarmName() {
        return dicAlarmName;
    }

    public void setDicAlarmName(DicAlarmName dicAlarmName) {
        this.dicAlarmName = dicAlarmName;
    }

    public DicAlarmType getDicAlarmType() {
        return dicAlarmType;
    }

    public void setDicAlarmType(DicAlarmType dicAlarmType) {
        this.dicAlarmType = dicAlarmType;
    }

    public BaseAlarm(){}
}
