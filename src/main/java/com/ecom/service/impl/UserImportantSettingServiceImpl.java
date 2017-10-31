package com.ecom.service.impl;

import com.ecom.dao.UserImportantSettingMapper;
import com.ecom.dao.example.UserImportantMapper;
import com.ecom.entity.BaseStation;
import com.ecom.entity.User;
import com.ecom.entity.UserImportant;
import com.ecom.service.UserImportantSettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by a7289 on 2016/12/30 0030.
 */
@Service
public class UserImportantSettingServiceImpl implements UserImportantSettingService {

    @Resource
    UserImportantSettingMapper userImportantSettingMapper;

    @Transactional
    public List<User> initUserImportant() {
        return userImportantSettingMapper.initUserImportant();
    }

    @Transactional
    public User getAllUserTac(User user) {
        return userImportantSettingMapper.getAllUserTac(user);
    }

    @Transactional
    public List<BaseStation> getNodeByTac(BaseStation baseStation) {
        return userImportantSettingMapper.getNodeByTac(baseStation);
    }

    @Transactional
    public UserImportant checkUserImportantExist(UserImportant userImportant) {
        return userImportantSettingMapper.checkUserImportantExist(userImportant);
    }

    @Transactional
    public int addUserImportant(UserImportant userImportant) {
        return userImportantSettingMapper.addUserImportant(userImportant);
    }

    @Transactional
    public int modUserImportant(UserImportant userImportant) {
        return userImportantSettingMapper.modUserImportant(userImportant);
    }

}
