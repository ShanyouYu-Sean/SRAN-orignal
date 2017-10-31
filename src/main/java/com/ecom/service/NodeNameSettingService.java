package com.ecom.service;

import com.ecom.entity.BaseStation;

/**
 * Created by a7289 on 2016/11/10 0010.
 */
public interface NodeNameSettingService {
    BaseStation checkNodeName(BaseStation baseStation);
    int modNodeName(BaseStation baseStation);
}
