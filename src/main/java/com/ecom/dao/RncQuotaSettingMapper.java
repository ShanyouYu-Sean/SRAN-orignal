package com.ecom.dao;

import com.ecom.entity.QuotaThresholdRnc3g;
import com.ecom.entity.QuotaThresholdTac;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2017/1/22 0022.
 */
@Repository
public interface RncQuotaSettingMapper {
    int modRncQuotaThreshold(QuotaThresholdRnc3g quotaThresholdRnc3g);
    List<QuotaThresholdRnc3g> getRncQuotaThreshold();
}
