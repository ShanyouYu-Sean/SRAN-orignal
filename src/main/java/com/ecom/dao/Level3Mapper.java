package com.ecom.dao;

import com.ecom.entity.BaseAlarm;
import com.ecom.entity.BaseCell;
import com.ecom.entity.QuotaThresholdCell;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2016/10/28 0028.
 */
@Repository
public interface Level3Mapper {
    List<BaseCell> getCellByNode(BaseCell baseCell);
    List<BaseCell> searchCellByNode(BaseCell baseCell);
    List<BaseAlarm> getAlarmByNode(BaseAlarm baseAlarm);
    List<QuotaThresholdCell> getCellQuotaThreshold();
}
