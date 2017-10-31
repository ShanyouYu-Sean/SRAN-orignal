package com.ecom.dao;

import com.ecom.entity.BaseStation;
import com.ecom.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Repository
public interface RoleSettingMapper {
    int addRole(Role role);
    List<BaseStation> getDicTac();
    List<BaseStation> getAllTac();
    int modRole(Role role);
    int delRole(Role role);
}
