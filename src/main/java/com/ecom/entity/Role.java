package com.ecom.entity;

import com.ecom.entity.example.RoleWithBLOBs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a7289 on 2016/10/31 0031.
 */
public class Role extends RoleWithBLOBs implements Serializable {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Role(){}
}
