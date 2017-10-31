package com.ecom.service;

import com.ecom.entity.BaseAlarm;
import com.ecom.entity.BaseCell;
import com.ecom.entity.QuotaThresholdCell;

import java.util.List;

/**
 * Created by a7289 on 2016/11/8 0008.
 */
public interface Level3Service {
    List<BaseCell> getCellByNode(BaseCell baseCell);
    List<BaseCell> searchCellByNode(BaseCell baseCell);
    List<BaseAlarm> getAlarmByNode(BaseAlarm baseAlarm);
    List<QuotaThresholdCell> getCellQuotaThreshold();
}
