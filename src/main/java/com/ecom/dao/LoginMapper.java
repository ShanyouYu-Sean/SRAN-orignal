package com.ecom.dao;

import com.ecom.entity.Role;
import com.ecom.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by a7289 on 2016/10/28 0028.
 */
@Repository
public interface LoginMapper {
    Role login(User user);
}
