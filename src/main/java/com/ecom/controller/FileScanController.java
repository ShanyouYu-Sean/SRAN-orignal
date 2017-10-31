package com.ecom.controller;

import com.ecom.util.ThreadNow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by a7289 on 2016/11/10 0010.
 */
@Controller
@RequestMapping("/lte")
public class FileScanController {
    @Resource
    private ThreadNow threadNow;
    @RequestMapping(value = "FileScanInit")
    public void fileScanInit(){
            if(threadNow.isAlive()&&threadNow.status == threadNow.SUSPEND){
                synchronized (this) {
                    System.out.println("----文件扫描程序恢复----");
                    threadNow.myResume();
                }
            }else {
                if (!threadNow.isAlive()) {
                    synchronized (this) {
                        System.out.println("----文件扫描程序启动成功----");
                        threadNow.start();
                    }
                }
            }
    }
    @RequestMapping(value = "FileScanSuspend")
    public void fileScanSuspend() throws InterruptedException {
        if (threadNow.isAlive()){
            synchronized(this) {
                System.out.println("----文件扫描程序被挂起----");
                threadNow.mySuspend();
            }
        }
    }
    @RequestMapping(value = "FileScanDestroy")
    public void fileScanDestroy() throws InterruptedException {
        if (threadNow.isAlive()){
            synchronized(this) {
                System.out.println("----文件扫描程序销毁----");
                threadNow.shutdownRequest();
                threadNow.join();
            }
        }
    }
}
