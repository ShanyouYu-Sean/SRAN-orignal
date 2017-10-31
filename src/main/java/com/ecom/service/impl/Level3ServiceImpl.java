package com.ecom.service.impl;

import com.ecom.dao.Level3Mapper;
import com.ecom.entity.BaseAlarm;
import com.ecom.entity.BaseCell;
import com.ecom.entity.QuotaThresholdCell;
import com.ecom.service.Level3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by a7289 on 2016/11/8 0008.
 */
@Service
public class Level3ServiceImpl implements Level3Service {

    @Resource
    private Level3Mapper level3Mapper;
    @Transactional
    public List<BaseCell> getCellByNode(BaseCell baseCell) {
        return level3Mapper.getCellByNode(baseCell);
    }

    public List<BaseCell> searchCellByNode(BaseCell baseCell) {
        return level3Mapper.searchCellByNode(baseCell);
    }

    @Transactional
    public List<BaseAlarm> getAlarmByNode(BaseAlarm baseAlarm) {
        return level3Mapper.getAlarmByNode(baseAlarm);
    }
    @Transactional
    public List<QuotaThresholdCell> getCellQuotaThreshold() {
        return level3Mapper.getCellQuotaThreshold();
    }
}
