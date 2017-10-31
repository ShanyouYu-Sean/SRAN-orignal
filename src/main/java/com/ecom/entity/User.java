package com.ecom.entity;

import java.io.Serializable;

/**
 * Created by a7289 on 2016/10/31 0031.
 */
public class User extends com.ecom.entity.example.User implements Serializable {

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private UserImportant userImportant;

    public UserImportant getUserImportant() {
        return userImportant;
    }

    public void setUserImportant(UserImportant userImportant) {
        this.userImportant = userImportant;
    }

    public User(){}

}

