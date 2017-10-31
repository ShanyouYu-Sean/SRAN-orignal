package com.ecom.service.impl;

import com.ecom.dao.RoleSettingMapper;
import com.ecom.entity.BaseStation;
import com.ecom.entity.Role;
import com.ecom.service.RoleSettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Service
public class RoleSettingServiceImpl implements RoleSettingService {

    @Resource
    private RoleSettingMapper roleSettingMapper;
    @Transactional
    public int addRole(Role role) {
        return roleSettingMapper.addRole(role);
    }
    @Transactional
    public List<BaseStation> getDicTac() {
        return roleSettingMapper.getDicTac();
    }
    @Transactional
    public List<BaseStation> getAllTac() {
        return roleSettingMapper.getAllTac();
    }
    @Transactional
    public int modRole(Role role) {
        return roleSettingMapper.modRole(role);
    }
    @Transactional
    public int delRole(Role role) {
        return roleSettingMapper.delRole(role);
    }
}
