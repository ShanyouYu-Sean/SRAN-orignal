package com.ecom.service.impl;

import com.ecom.dao.NodeQuotaSettingMapper;
import com.ecom.entity.QuotaThresholdNode;
import com.ecom.service.NodeQuotaSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Service
public class NodeQuotaSettingServiceImpl implements NodeQuotaSettingService {

    @Resource
    private NodeQuotaSettingMapper nodeQuotaSettingMapper;

    public int modNodeQuotaThreshold(QuotaThresholdNode quotaThresholdNode) {
        return nodeQuotaSettingMapper.modNodeQuotaThreshold(quotaThresholdNode);
    }
}
