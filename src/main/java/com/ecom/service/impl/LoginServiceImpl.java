package com.ecom.service.impl;

import com.ecom.dao.LoginMapper;
import com.ecom.entity.Role;
import com.ecom.entity.User;
import com.ecom.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by a7289 on 2016/10/31 0031.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;
    @Transactional
    public Role login(User user) {
        return loginMapper.login(user);
    }
}
