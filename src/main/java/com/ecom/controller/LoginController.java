package com.ecom.controller;

import com.ecom.util.md5Util.Util;
import com.ecom.entity.Role;
import com.ecom.entity.User;
import com.ecom.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a7289 on 2016/10/31 0031.
 */
@Controller
@RequestMapping("/lte")
public class LoginController {
    @Resource
    private LoginService loginService;
    @RequestMapping(value = "login")
    public String loginInit(){
        return "html/login";
    }
    @RequestMapping(value = "loginCheck" , method = RequestMethod.POST)
    public @ResponseBody
    Map<String , Object> loginCheck(@RequestBody User user){
        boolean status = false;
        String msg = "";
        Map<String , Object> data = new HashMap<String , Object>();
        String url = "";
        Map jsonResult = new HashMap();

        if (user != null){
            String pass = Util.stringMD5(user.getPassword());
            user.setPassword(pass);
            Role currentUser = loginService.login(user);
            if (currentUser != null){
                if (currentUser.getUserList().get(0).getFlag()==2){
                    msg = "该用户已被禁用";
                }else {
                    data.put("user_id", currentUser.getUserList().get(0).getUserId());
                    data.put("user_name", currentUser.getUserList().get(0).getUserName());
                    data.put("role_tac", currentUser.getRoleTac());
                    msg = "登陆成功";
                    status = true;
                    url = "../lte/level1";
                }
            }else{
                msg = "用户名或密码不正确";
            }
        }else{
            msg = "用户名或密码不能为空";
        }

        jsonResult.put("status",status);
        jsonResult.put("msg",msg);
        jsonResult.put("data",data);
        jsonResult.put("url",url);

        return jsonResult;
    }
}
