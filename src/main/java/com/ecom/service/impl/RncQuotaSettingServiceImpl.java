package com.ecom.service.impl;

import com.ecom.dao.RncQuotaSettingMapper;
import com.ecom.entity.QuotaThresholdRnc3g;
import com.ecom.entity.QuotaThresholdTac;
import com.ecom.service.RncQuotaSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by a7289 on 2017/1/22 0022.
 */
@Service
public class RncQuotaSettingServiceImpl implements RncQuotaSettingService {

    @Resource
    private RncQuotaSettingMapper rncQuotaSettingMapper;

    public int modRncQuotaThreshold(QuotaThresholdRnc3g quotaThresholdRnc3g) {
        return rncQuotaSettingMapper.modRncQuotaThreshold(quotaThresholdRnc3g);
    }

    public List<QuotaThresholdRnc3g> getRncQuotaThreshold() {
        return rncQuotaSettingMapper.getRncQuotaThreshold();
    }
}
