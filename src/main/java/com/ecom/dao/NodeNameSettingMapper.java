package com.ecom.dao;

import com.ecom.entity.BaseStation;
import org.springframework.stereotype.Repository;

/**
 * Created by a7289 on 2016/11/10 0010.
 */
@Repository
public interface NodeNameSettingMapper {
    BaseStation checkNodeName(BaseStation baseStation);
    int modNodeName(BaseStation baseStation);
}
