package com.ecom.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a7289 on 2017/2/27 0027.
 */
public class BaseRnc3g extends com.ecom.entity.example.BaseRnc3g implements Serializable,BaseStatus3g {

    private List<BaseStation3g> baseStation3gList;

    public List<BaseStation3g> getBaseStation3gList() {
        return baseStation3gList;
    }

    public void setBaseStation3gList(List<BaseStation3g> baseStation3gList) {
        this.baseStation3gList = baseStation3gList;
    }

    public BaseRnc3g(){}
}
