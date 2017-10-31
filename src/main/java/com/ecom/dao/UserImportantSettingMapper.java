package com.ecom.dao;

import com.ecom.entity.BaseStation;
import com.ecom.entity.User;
import com.ecom.entity.UserImportant;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2016/12/30 0030.
 */
@Repository
public interface UserImportantSettingMapper {
    List<User> initUserImportant();
    User getAllUserTac(User user);
    List<BaseStation> getNodeByTac(BaseStation baseStation);
    UserImportant checkUserImportantExist(UserImportant userImportant);
    int addUserImportant(UserImportant userImportant);
    int modUserImportant(UserImportant userImportant);
}
