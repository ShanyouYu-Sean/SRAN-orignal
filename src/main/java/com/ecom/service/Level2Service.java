package com.ecom.service;

import com.ecom.entity.BaseStation;
import com.ecom.entity.QuotaThresholdNode;

import java.util.List;

/**
 * Created by a7289 on 2016/11/4 0004.
 */
public interface Level2Service {
    List<BaseStation> getStationByTac(BaseStation baseStation);
    List<QuotaThresholdNode> getNodeQuotaThreshold();
    List<BaseStation> searchStation(BaseStation baseStation);
}
