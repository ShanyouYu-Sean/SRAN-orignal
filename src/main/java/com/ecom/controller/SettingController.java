package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by a7289 on 2016/11/8 0008.
 */
@Controller
@RequestMapping("/lte")
public class SettingController {
    @RequestMapping(value = "setting")
    public String settingInit(){
        return "html/setting";
    }
}
