package com.ecom.controller;

import com.ecom.entity.QuotaThresholdCell;
import com.ecom.service.CellQuotaSettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Controller
@RequestMapping("/lte")
public class CellQuotaSettingController {
    @Resource  private CellQuotaSettingService cellQuotaSettingService;

    @RequestMapping(value = "mod_cell_quota_threshold",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> modCellQuotaThreshold(@RequestBody QuotaThresholdCell quotaThresholdCell){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer row = cellQuotaSettingService.modCellQuotaThreshold(quotaThresholdCell);
        if (row == null){
            msg = "更新cell门限失败";
        }else{
            msg = "更新cell门限成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }
}
