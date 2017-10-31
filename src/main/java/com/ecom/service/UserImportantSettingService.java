package com.ecom.service;

import com.ecom.entity.BaseStation;
import com.ecom.entity.User;
import com.ecom.entity.UserImportant;

import java.util.List;

/**
 * Created by a7289 on 2016/12/30 0030.
 */
public interface UserImportantSettingService {
    List<User> initUserImportant();
    User getAllUserTac(User user);
    List<BaseStation> getNodeByTac(BaseStation baseStation);
    UserImportant checkUserImportantExist(UserImportant userImportant);
    int addUserImportant(UserImportant userImportant);
    int modUserImportant(UserImportant userImportant);
}
