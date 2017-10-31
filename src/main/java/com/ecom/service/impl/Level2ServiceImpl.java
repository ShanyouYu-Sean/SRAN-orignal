package com.ecom.service.impl;

import com.ecom.dao.Level2Mapper;
import com.ecom.entity.BaseStation;
import com.ecom.entity.QuotaThresholdNode;
import com.ecom.service.Level2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by a7289 on 2016/11/4 0004.
 */
@Service
public class Level2ServiceImpl implements Level2Service{

    @Resource
    private Level2Mapper level2Mapper;

    @Transactional
    public List<BaseStation> getStationByTac(BaseStation baseStation) {
        return level2Mapper.getStationByTac(baseStation);
    }

    @Transactional
    public List<QuotaThresholdNode> getNodeQuotaThreshold() {
        return level2Mapper.getNodeQuotaThreshold();
    }

    @Transactional
    public List<BaseStation> searchStation(BaseStation baseStation) {
        return level2Mapper.searchStation(baseStation);
    }

}
