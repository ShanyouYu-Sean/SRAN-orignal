package com.ecom.dao;

import com.ecom.entity.QuotaThresholdNode;
import org.springframework.stereotype.Repository;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Repository
public interface NodeQuotaSettingMapper {
    int modNodeQuotaThreshold(QuotaThresholdNode quotaThresholdNode);
}
