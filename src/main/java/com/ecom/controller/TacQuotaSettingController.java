package com.ecom.controller;

import com.ecom.entity.QuotaThresholdTac;
import com.ecom.service.TacQuotaSettingService;
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
public class TacQuotaSettingController {

    @Resource
    private TacQuotaSettingService tacQuotaSettingService;

    @RequestMapping(value = "mod_tac_quota_threshold",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> modTacQuotaThreshold(@RequestBody QuotaThresholdTac quotaThresholdTac){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer row = tacQuotaSettingService.modTacQuotaThreshold(quotaThresholdTac);
        if (row == null){
            msg = "更新tac门限失败";
        }else{
            msg = "更新tac门限成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }
}
