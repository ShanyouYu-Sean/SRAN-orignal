package com.ecom.entity;

import com.ecom.entity.BaseNode3g;
import com.ecom.entity.BaseRnc3g;

import java.io.Serializable;

/**
 * Created by a7289 on 2017/2/27 0027.
 */
public class BaseStation3g extends com.ecom.entity.example.BaseStation3g implements Serializable {

    private BaseRnc3g baseRnc3g;
    private BaseNode3g baseNode3g;
    private String order;
    private String keyword;

    public BaseRnc3g getBaseRnc3g() {
        return baseRnc3g;
    }

    public void setBaseRnc3g(BaseRnc3g baseRnc3g) {
        this.baseRnc3g = baseRnc3g;
    }

    public BaseNode3g getBaseNode3g() {
        return baseNode3g;
    }

    public void setBaseNode3g(BaseNode3g baseNode3g) {
        this.baseNode3g = baseNode3g;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BaseStation3g(){}
}
