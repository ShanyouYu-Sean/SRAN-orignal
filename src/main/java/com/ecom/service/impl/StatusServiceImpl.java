package com.ecom.service.impl;

import com.ecom.dao.StatusMapper;
import com.ecom.entity.*;
import com.ecom.service.StatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by a7289 on 2016/11/21 0021.
 */
@Service
public class StatusServiceImpl implements StatusService {
    @Resource
    private StatusMapper statusMapper;
    @Transactional
    public List<QuotaThresholdTac> getTacQuotaThreshold() {
        return statusMapper.getTacQuotaThreshold();
    }
    @Transactional
    public List<QuotaThresholdNode> getNodeQuotaThreshold() {
        return statusMapper.getNodeQuotaThreshold();
    }
    @Transactional
    public List<QuotaThresholdCell> getCellQuotaThreshold() {
        return statusMapper.getCellQuotaThreshold();
    }
    @Transactional
    public List<BaseTac> getTacStatus() {
        return statusMapper.getTacStatus();
    }
    @Transactional
    public List<BaseNode> getNodeStatus() {
        return statusMapper.getNodeStatus();
    }
    @Transactional
    public List<BaseCell> getCellStatus() {
        return statusMapper.getCellStatus();
    }
    @Transactional
    public void modTacStatus(BaseTac baseTac) {
         statusMapper.modTacStatus(baseTac);
    }
    @Transactional
    public void modNodeStatus(BaseNode baseNode) {
         statusMapper.modNodeStatus(baseNode);
    }
    @Transactional
    public void modCellStatus(BaseCell baseCell) {
         statusMapper.modCellStatus(baseCell);
    }
}
