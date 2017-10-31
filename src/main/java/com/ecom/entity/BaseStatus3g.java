package com.ecom.entity;

/**
 * Created by a7289 on 2017/2/27 0027.
 */
public interface BaseStatus3g {
     Integer getStatus();

     void setStatus(Integer status);

     String getRrcSuc();

     void setRrcSuc(String rrcSuc);

     String getPsRrcSucRate();

     void setPsRrcSucRate(String psRrcSucRate);

     String getSpchRrcSucRate();

     void setSpchRrcSucRate(String spchRrcSucRate);

     String getSpchRabSuc();

     void setSpchRabSuc(String spchRabSuc);

     String getPsRabSucc();

     void setPsRabSucc(String psRabSucc);

     String getShoSuccess();

     void setShoSuccess(String shoSuccess);

     String getSpchDrop();

     void setSpchDrop(String spchDrop);

     String getPsDrop();

     void setPsDrop(String psDrop);
}
