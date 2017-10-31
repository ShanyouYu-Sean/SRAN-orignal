package com.ecom.service.impl;

import com.ecom.dao.UserSettingMapper;
import com.ecom.entity.Role;
import com.ecom.entity.User;
import com.ecom.service.UserSettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Service
public class UserSettingServiceImpl implements UserSettingService {

    @Resource
    private UserSettingMapper userSettingMapper;
    @Transactional
    public int addUser(User user) {
        return userSettingMapper.addUser(user);
    }
    @Transactional
    public int modUser(User user) {
        return userSettingMapper.modUser(user);
    }
    @Transactional
    public List<User> getUser() {
        return userSettingMapper.getUser();
    }
    @Transactional
    public List<Role> getRole() {
        return userSettingMapper.getRole();
    }
    @Transactional
    public int delUser(User user) {
        return userSettingMapper.delUser(user);
    }
    @Transactional
    public User getPassById(User user) {
        return userSettingMapper.getPassById(user);
    }
}
