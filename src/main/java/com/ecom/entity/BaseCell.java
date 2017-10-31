package com.ecom.entity;

import java.io.Serializable;

/**
 * Created by a7289 on 2016/11/8 0008.
 */
public class BaseCell extends com.ecom.entity.example.BaseCell implements Serializable,BaseStatus{

//    private String tac;
//
//    public String getTac() {
//        return tac;
//    }
//
//    public void setTac(String tac) {
//        this.tac = tac;
//    }

    private String nodeName;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BaseCell(){}
}
