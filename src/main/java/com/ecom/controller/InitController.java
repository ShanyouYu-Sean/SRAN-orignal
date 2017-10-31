package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by a7289 on 2016/11/1 0001.
 */
@Controller
public class InitController {
    @RequestMapping("/")
    public String LoginInit(){
        return "html/index";
    }

}
