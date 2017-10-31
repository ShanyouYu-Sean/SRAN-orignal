package com.ecom.entity;

import com.ecom.entity.example.Role3gWithBLOBs;
import com.ecom.entity.example.RoleWithBLOBs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a7289 on 2017/2/27 0027.
 */
public class Role3g extends Role3gWithBLOBs implements Serializable {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Role3g(){}
}
