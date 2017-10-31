package com.ecom.service.impl;

import com.ecom.dao.NodeNameSettingMapper;
import com.ecom.entity.BaseStation;
import com.ecom.service.NodeNameSettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by a7289 on 2016/11/10 0010.
 */
@Service
public class NodeNameSettingServiceImpl implements NodeNameSettingService {

    @Resource
    private NodeNameSettingMapper nodeNameSettingMapper;
    @Transactional
    public BaseStation checkNodeName(BaseStation baseStation) {
        return nodeNameSettingMapper.checkNodeName(baseStation);
    }

    public int modNodeName(BaseStation baseStation) {
        return nodeNameSettingMapper.modNodeName(baseStation);
    }
}
