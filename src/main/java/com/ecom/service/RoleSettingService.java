package com.ecom.service;

import com.ecom.entity.BaseStation;
import com.ecom.entity.Role;

import java.util.List;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
public interface RoleSettingService {
    int addRole(Role role);
    List<BaseStation> getDicTac();
    List<BaseStation> getAllTac();
    int modRole(Role role);
    int delRole(Role role);
}
