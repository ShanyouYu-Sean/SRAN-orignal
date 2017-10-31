package com.ecom.dao;

import com.ecom.entity.Role;
import com.ecom.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Repository
public interface UserSettingMapper {
    int addUser(User user);
    int modUser(User user);
    List<User> getUser();
    List<Role> getRole();
    int delUser(User user);
    User getPassById(User user);
}
