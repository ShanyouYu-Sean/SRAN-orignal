package com.ecom.entity;

import java.io.Serializable;

/**
 * Created by a7289 on 2016/11/2 0002.
 */
public class BaseStation extends com.ecom.entity.example.BaseStation implements Serializable {


    private BaseTac baseTac;
    private BaseNode baseNode;
    private String order;
    private String keyword;


    public BaseTac getBaseTac() {
        return baseTac;
    }

    public void setBaseTac(BaseTac baseTac) {
        this.baseTac = baseTac;
    }

    public BaseNode getBaseNode() {
        return baseNode;
    }

    public void setBaseNode(BaseNode baseNode) {
        this.baseNode = baseNode;
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

    public BaseStation(){}
}
