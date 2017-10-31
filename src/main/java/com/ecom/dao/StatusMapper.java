package com.ecom.dao;

import com.ecom.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a7289 on 2016/11/21 0021.
 */
@Repository
public interface StatusMapper {
     List<QuotaThresholdTac> getTacQuotaThreshold();
     List<QuotaThresholdNode> getNodeQuotaThreshold();
     List<QuotaThresholdCell> getCellQuotaThreshold();
     List<BaseTac> getTacStatus();
     List<BaseNode> getNodeStatus();
     List<BaseCell> getCellStatus();
     void modTacStatus(BaseTac baseTac);
     void modNodeStatus(BaseNode baseNode);
     void modCellStatus(BaseCell baseCell);
}
