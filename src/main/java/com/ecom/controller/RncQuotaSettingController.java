package com.ecom.controller;

import com.ecom.entity.QuotaThresholdRnc3g;
import com.ecom.entity.QuotaThresholdTac;
import com.ecom.service.RncQuotaSettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a7289 on 2017/1/22 0022.
 */
@Controller
@RequestMapping("/lte")
public class RncQuotaSettingController {

    @Resource
    private RncQuotaSettingService rncQuotaSettingService;

    @RequestMapping(value = "mod_rnc_quota_threshold",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> modRncQuotaThreshold(@RequestBody QuotaThresholdRnc3g quotaThresholdRnc3g){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer row = rncQuotaSettingService.modRncQuotaThreshold(quotaThresholdRnc3g);
        if (row == null){
            msg = "更新rnc门限失败";
        }else{
            msg = "更新rnc门限成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }

    @RequestMapping(value = "get_rnc_quota_threshold",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> getRncQuotaThreshold(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<QuotaThresholdRnc3g> quotaThresholdRnc3gList = rncQuotaSettingService.getRncQuotaThreshold();
        if (quotaThresholdRnc3gList == null || quotaThresholdRnc3gList.size() == 0){
            msg = "获得QuotaThresholdRnc失败";
        }else{
            msg = "获得QuotaThresholdRnc成功";
            status = true;
            for (int i = 0; i < quotaThresholdRnc3gList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("quota_name",quotaThresholdRnc3gList.get(i).getQuotaName());
                data.put("quota_type",quotaThresholdRnc3gList.get(i).getQuotaType());
                data.put("threshold_1",quotaThresholdRnc3gList.get(i).getThreshold1());
                data.put("threshold_2",quotaThresholdRnc3gList.get(i).getThreshold2());
                data.put("threshold_3",quotaThresholdRnc3gList.get(i).getThreshold3());
                data.put("quota_unit",quotaThresholdRnc3gList.get(i).getQuotaUnit());

                dataList.add(i,data);
            }
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }
}
