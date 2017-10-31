package com.ecom.entity;

/**
 * Created by a7289 on 2016/11/21 0021.
 */
public interface BaseQuota {

     String getQuotaName();

     void setQuotaName(String quotaName);

     Integer getQuotaType();

     void setQuotaType(Integer quotaType);

     String getThreshold1();

     void setThreshold1(String threshold1);

     String getThreshold2();

     void setThreshold2(String threshold2);

     String getThreshold3();

     void setThreshold3(String threshold3);

     Integer getQuotaUnit();

     void setQuotaUnit(Integer quotaUnit);
}
