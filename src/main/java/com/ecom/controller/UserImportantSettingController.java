package com.ecom.controller;

import com.ecom.entity.BaseQuota;
import com.ecom.entity.BaseStation;
import com.ecom.entity.User;
import com.ecom.entity.UserImportant;
import com.ecom.service.UserImportantSettingService;
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
 * Created by a7289 on 2016/12/30 0030.
 */
@Controller
@RequestMapping("/lte")
public class UserImportantSettingController {

    @Resource
    UserImportantSettingService userImportantSettingService;

    @RequestMapping(value = "init_user_important", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> initUserImportant() {
        boolean status = false;
        String msg = "";
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<User> userList = userImportantSettingService.initUserImportant();
        if (userList == null || userList.size() == 0) {
            msg = "获取用户关注列表失败";
        } else {
            msg = "获取用户关注列表成功";
            status = true;
            for (int i = 0; i < userList.size(); i++) {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user_name", userList.get(i).getUserName());
                data.put("user_id", userList.get(i).getUserId());
                if (userList.get(i).getUserImportant() != null) {
                    data.put("important_tac", userList.get(i).getUserImportant().getImportantTac());
                    data.put("important_node", userList.get(i).getUserImportant().getImportantNode());
                } else {
                    data.put("important_tac", "");
                    data.put("important_node", "");
                }

                dataList.add(data);
            }
        }
        jsonResult.put("status", status);
        jsonResult.put("msg", msg);
        jsonResult.put("data", dataList);
        jsonResult.put("url", url);
        return jsonResult;
    }

    @RequestMapping(value = "get_all_user_tac", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> getAllUserTac(@RequestBody User user) {
        boolean status = false;
        String msg = "";
//        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        User userImportantSetting = userImportantSettingService.getAllUserTac(user);
//        List<BaseStation> baseStationList = userImportantSettingService.getDicTac();
//        List<BaseStation> allTacList = userImportantSettingService.getAllTac();
        if ((userImportantSetting == null) || (userImportantSetting.getRole() == null) || (userImportantSetting.getRole().getRoleTac() == null) || (userImportantSetting.getRole().getRoleTac().equals("")) ) {
            msg = "获取用户tac列表失败";
        } else {
            msg = "获取用户tac列表成功";
            status = true;
            data.put("user_role_tac",userImportantSetting.getRole().getRoleTac());
            data.put("user_id",userImportantSetting.getUserId());
            data.put("user_name",userImportantSetting.getUserName());
            data.put("role_name",userImportantSetting.getRole().getRoleName());
        }
            jsonResult.put("status", status);
            jsonResult.put("msg", msg);
            jsonResult.put("data", data);
            jsonResult.put("url", url);
            return jsonResult;
        }

    @RequestMapping(value = "get_node_by_tac", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> getNodeByTac(@RequestBody BaseStation baseStation) {
        boolean status = false;
        String msg = "";
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<BaseStation> importantNodeList = userImportantSettingService.getNodeByTac(baseStation);
//        List<BaseStation> allTacList = userImportantSettingService.getAllTac();
        if ((importantNodeList == null) || (importantNodeList.size() == 0)) {
            msg = "获取用户node列表失败";
        } else {
            msg = "获取用户node列表成功";
            status = true;
            for (int i = 0; i < importantNodeList.size(); i++) {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("node", importantNodeList.get(i).getEnbName());
                dataList.add(data);
            }
        }
        jsonResult.put("status", status);
        jsonResult.put("msg", msg);
        jsonResult.put("data", dataList);
        jsonResult.put("url", url);
        return jsonResult;
    }

    @RequestMapping(value = "check_user_important_exist", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> checkUserImportantExist(@RequestBody UserImportant userImportant) {
        boolean status = false;
        String msg = "";
//        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        UserImportant user = userImportantSettingService.checkUserImportantExist(userImportant);
        if ((user == null) || (user.getUserId() == null)) {
            msg = "用户关注列表不存在";
        } else {
            msg = "用户关注列表存在";
            status = true;
            data.put("user_id",user.getUserId());
        }
        jsonResult.put("status", status);
        jsonResult.put("msg", msg);
        jsonResult.put("data", data);
        jsonResult.put("url", url);
        return jsonResult;
    }

    @RequestMapping(value = "add_user_important",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> addUserImportant(@RequestBody UserImportant userImportant){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer userImportantId = userImportantSettingService.addUserImportant(userImportant);
        if (userImportantId == null){
            msg = "添加关注列表失败";
        }else{
            msg = "添加关注列表成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }

    @RequestMapping(value = "mod_user_important",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> modUserImportant(@RequestBody UserImportant userImportant){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer row = userImportantSettingService.modUserImportant(userImportant);
        if (row == null){
            msg = "修改关注列表失败";
        }else{
            msg = "修改关注列表成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }
}
