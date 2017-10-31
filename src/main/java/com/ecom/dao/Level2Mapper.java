package com.ecom.dao;

import com.ecom.entity.BaseStation;
import com.ecom.entity.QuotaThresholdNode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2016/10/28 0028.
 */
@Repository
public interface Level2Mapper {
    List<BaseStation> getStationByTac(BaseStation baseStation);
    List<QuotaThresholdNode> getNodeQuotaThreshold();
    List<BaseStation> searchStation(BaseStation baseStation);
}
