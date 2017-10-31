package com.ecom.service.impl;

import com.ecom.dao.Level1Mapper;
import com.ecom.entity.*;
import com.ecom.service.Level1Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by a7289 on 2016/11/2 0002.
 */
@Service
public class Level1ServiceImpl implements Level1Service{
    @Resource
    private Level1Mapper level1Mapper;

    @Transactional
    public List<BaseTac> getGroupStationByTac(BaseTac baseTac) {
        return level1Mapper.getGroupStationByTac(baseTac);
    }
    @Transactional
    public List<BaseTac> searchGroupStation(BaseStation baseStation) {
        return level1Mapper.searchGroupStation(baseStation);
    }
    @Transactional
    public Role getUserRoleTac(User user) {
        return level1Mapper.getUserRoleTac(user);
    }

    @Transactional
    public Role3g getUserRoleRnc(User user) {
        return level1Mapper.getUserRoleRnc(user);
    }

    @Transactional
    public Tac getUpdateTime() {
        return level1Mapper.getUpdateTime();
    }
    @Transactional
    public List<QuotaThresholdTac> getTacQuotaThreshold() {
        return level1Mapper.getTacQuotaThreshold();
    }
    @Transactional
    public List<BaseStation> getTacBaseStation() {
        return level1Mapper.getTacBaseStation();
    }
    @Transactional
    public List<BaseStation> getBaseStationByTac(BaseStation baseStation) {
        return level1Mapper.getBaseStationByTac(baseStation);
    }
    @Transactional
    public List<BaseStation> getBaseStation3gByRnc(BaseStation3g baseStation3g) {
        return level1Mapper.getBaseStation3gByRnc(baseStation3g);
    }

    @Transactional
    public Integer getMaxStationByTac(BaseStation baseStation) {
        return level1Mapper.getMaxStationByTac(baseStation);
    }
    @Transactional
    public UserImportant getUserImportant(UserImportant userImportant) {
        return level1Mapper.getUserImportant(userImportant);
    }
    @Transactional
    public List<BaseStation> getImportantNode(BaseStation baseStation) {
        return level1Mapper.getImportantNode(baseStation);
    }
}
