package com.ecom.service;

import com.ecom.entity.Role;
import com.ecom.entity.User;

import java.util.List;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
public interface UserSettingService {
    int addUser(User user);
    int modUser(User user);
    List<User> getUser();
    List<Role> getRole();
    int delUser(User user);
    User getPassById(User user);

}
