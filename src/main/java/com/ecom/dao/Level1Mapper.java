package com.ecom.dao;

import com.ecom.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2016/10/28 0028.
 */
@Repository
public interface Level1Mapper {
    List<BaseTac> getGroupStationByTac(BaseTac baseTac);
    List<BaseTac> searchGroupStation(BaseStation baseStation);
    Role getUserRoleTac(User user);
    Role3g getUserRoleRnc(User user);
    Tac getUpdateTime();
    List<QuotaThresholdTac> getTacQuotaThreshold();
    List<BaseStation> getTacBaseStation();
    List<BaseStation> getBaseStationByTac(BaseStation baseStation);
    List<BaseStation> getBaseStation3gByRnc(BaseStation3g baseStation3g);
    Integer getMaxStationByTac(BaseStation baseStation);
    UserImportant getUserImportant(UserImportant userImportant);
    List<BaseStation> getImportantNode(BaseStation baseStation);
}
