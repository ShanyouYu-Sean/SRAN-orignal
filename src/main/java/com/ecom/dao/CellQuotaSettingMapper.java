package com.ecom.dao;

import com.ecom.entity.QuotaThresholdCell;
import org.springframework.stereotype.Repository;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Repository
public interface CellQuotaSettingMapper {
    int modCellQuotaThreshold(QuotaThresholdCell quotaThresholdCell);
}
