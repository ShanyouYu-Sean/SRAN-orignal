package com.ecom.service.impl;

import com.ecom.dao.TacQuotaSettingMapper;
import com.ecom.entity.QuotaThresholdTac;
import com.ecom.service.TacQuotaSettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Service
public class TacQuotaSettingServiceImpl implements TacQuotaSettingService {

    @Resource
    private TacQuotaSettingMapper tacQuotaSettingMapper;

    @Transactional
    public int modTacQuotaThreshold(QuotaThresholdTac quotaThresholdTac) {
        return tacQuotaSettingMapper.modTacQuotaThreshold(quotaThresholdTac);
    }
}
