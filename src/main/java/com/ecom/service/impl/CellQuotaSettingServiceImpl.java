package com.ecom.service.impl;

import com.ecom.dao.CellQuotaSettingMapper;
import com.ecom.entity.QuotaThresholdCell;
import com.ecom.service.CellQuotaSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Service
public class CellQuotaSettingServiceImpl implements CellQuotaSettingService {

    @Resource
    private CellQuotaSettingMapper cellQuotaSettingMapper;

    public int modCellQuotaThreshold(QuotaThresholdCell quotaThresholdCell) {
        return cellQuotaSettingMapper.modCellQuotaThreshold(quotaThresholdCell);
    }
}
