package com.ecom.controller;

import com.ecom.util.md5Util.Util;
import com.ecom.entity.Role;
import com.ecom.entity.User;
import com.ecom.service.UserSettingService;
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
public class UserSettingController {

    @Resource
    private UserSettingService userSettingService;

    @RequestMapping(value = "add_user",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> addUser(@RequestBody User user){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        String pass = Util.stringMD5(user.getPassword());
        user.setPassword(pass);
        Integer userId = userSettingService.addUser(user);
        if (userId == null){
            msg = "添加用户失败";
        }else{
            msg = "添加用户成功";
            status = true;
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }

    @RequestMapping(value = "mod_user",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> modUser(@RequestBody User user){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        User userOld = userSettingService.getPassById(user);
        if (!user.getPassword().equals(userOld.getPassword())){
            String pass = Util.stringMD5(user.getPassword());
            user.setPassword(pass);
        }
        Integer row = userSettingService.modUser(user);
        if (row == null){
            msg = "修改用户失败";
        }else{
            msg = "修改用户成功";
            status = true;
        }


        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }

    @RequestMapping(value = "get_user",method = RequestMethod.GET)
    public @ResponseBody
    Map<String , Object> getUser(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<User> userList = userSettingService.getUser();
        if(userList == null || userList.size()==0){
            msg = "获取用户列表失败";
        }else{
            msg = "获取用户列表成功";
            status = true;
            for (int i = 0; i < userList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("user_id",userList.get(i).getUserId());
                data.put("user_name",userList.get(i).getUserName());
                data.put("login_name",userList.get(i).getLoginName());
                data.put("flag",userList.get(i).getFlag());
                data.put("password",userList.get(i).getPassword());
                if (userList.get(i).getRole()!=null){
                    data.put("role_id",userList.get(i).getRoleId());
                    data.put("role_name",userList.get(i).getRole().getRoleName());
                }else {
                    data.put("role_id","");
                    data.put("role_name","");
                }

                dataList.add(data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "get_role",method = RequestMethod.GET)
    public @ResponseBody
    Map<String , Object> getRole(){
        boolean status = false;
        String msg = "";
        List<Map<String , Object>> dataList =  new ArrayList<Map<String , Object>>();
//        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        List<Role> roleList = userSettingService.getRole();
        if(roleList == null || roleList.size()==0){
            msg = "获取角色列表失败";
        }else{
            msg = "获取角色列表成功";
            status = true;
            for (int i = 0; i < roleList.size(); i++){
                Map<String , Object> data = new HashMap<String , Object>();
                data.put("role_id",roleList.get(i).getRoleId());
                data.put("role_name",roleList.get(i).getRoleName());
                data.put("role_tac",roleList.get(i).getRoleTac());
                data.put("role_node",roleList.get(i).getRoleNode());
                data.put("role_cell",roleList.get(i).getRoleCell());
                dataList.add(data);
            }
        }
        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",dataList);
        jsonResult.put("url",url);
        return jsonResult;
    }

    @RequestMapping(value = "del_user",method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> delUser(@RequestBody User user){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        Integer row = userSettingService.delUser(user);
        if (row == null){
            msg = "删除用户失败";
        }else{
            msg = "删除用户成功";
            status = true;
        }


        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }
}
