package com.ecom.controller;

import com.ecom.entity.BaseStation;
import com.ecom.entity.Role;
import com.ecom.service.RoleSettingService;
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
 * Created by a7289 on 2016/11/9 0009.
 */
@Controller
@RequestMapping("/lte")
public class RoleSettingController {
    @Resource
    private RoleSettingService roleSettingService;

    @RequestMapping(value = "add_role",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> addRole(@RequestBody Role role){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer roleId = roleSettingService.addRole(role);
        if (roleId == null){
            msg = "添加角色失败";
        }else{
            msg = "添加角色成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }

    @RequestMapping(value = "get_dic_tac",method = RequestMethod.GET)
    public @ResponseBody
    Map<String , Object> getDicTac(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseStation> baseStationList = roleSettingService.getDicTac();
        List<BaseStation> allTacList = roleSettingService.getAllTac();
        if (baseStationList == null || baseStationList.size()==0){
            msg = "获得dic_tac失败";
        }else{
            msg = "获得dic_tac成功";
            status = true;
            outer:
            for (int j = 0; j < allTacList.size(); j++) {
                Map<String, Object> data = new HashMap<String, Object>();
                for (int i = 0; i < baseStationList.size(); i++) {
                    if (allTacList.get(j).getTac().equals(baseStationList.get(i).getTac())){
                        data.put("available","true");
                        data.put("tac",allTacList.get(j).getTac());
                        dataList.add(data);
                        continue outer;
                    }
                }
                data.put("available","false");
                data.put("tac",allTacList.get(j).getTac());
                dataList.add(data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "mod_role",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> modRole(@RequestBody Role role){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer row = roleSettingService.modRole(role);
        if (row == null){
            msg = "修改角色失败";
        }else{
            msg = "修改角色成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }

    @RequestMapping(value = "del_role",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> delRole(@RequestBody Role role){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer row = roleSettingService.delRole(role);
        if (row == null){
            msg = "删除角色失败";
        }else{
            msg = "删除角色成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }
}
