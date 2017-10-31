package com.ecom.controller;

import com.ecom.util.threadUtil.ThreadUtil;
import com.ecom.entity.BaseStation;
import com.ecom.service.NodeNameSettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by a7289 on 2016/11/10 0010.
 */
@Controller
@RequestMapping("/lte")
public class NodeNameSettingController {
    @Resource
    private NodeNameSettingService nodeNameSettingService;
    @RequestMapping(value = "modNodeName")
    public String modNodeName(HttpServletRequest request) throws IllegalStateException, IOException {
        boolean errorCode = true;
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    //判断文件夹是否创建，没有创建则创建新文件夹
                    File parentFilePath = new File(ThreadUtil.getPath()+"/upload");
                    if (!parentFilePath.exists()){
                        parentFilePath.mkdirs();
                    }
                    //判断文件是否存在，存在则删除已有文件
                    String path= ThreadUtil.getPath()+"/upload/"+file.getOriginalFilename();
                    File currentFile = new File(path);
                    if(currentFile.exists()){
                        currentFile.delete();
                    }
                    //上传
                    List<BaseStation> baseStationList = new ArrayList<BaseStation>();
                    file.transferTo(currentFile);
                    //判断文件是否为空
                    FileInputStream fin = new FileInputStream(path);
                    int size=fin.available();
                    if(size==0){
                        errorCode = false;
                    }
                    if (errorCode){
                        //逐行读取文件
                        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                        for (String line = br.readLine(); line != null; line = br.readLine()) {
                            //判断文件是否有回车，有则直接进入下一行
                            if (line.equals("") || line.trim().equals("")){
                                continue;
                            }
                            //判断格式是否符合要求
                            String [] newNodeName = line.trim().split(",");
                            if (newNodeName.length == 2){
                                BaseStation baseStation = new BaseStation();
                                baseStation.setEnbName(newNodeName[0]);
                                baseStation.setName(newNodeName[1]);
                                //判断是否有上传数据中对应的nodeName
                                if (nodeNameSettingService.checkNodeName(baseStation) == null){
                                    errorCode = false;
                                    break;
                                }else {
                                    baseStationList.add(baseStation);
                                }
                            }else {
                                errorCode = false;
                                break;
                            }
                        }
                        //文件都是空字符
                        if (baseStationList.size()==0){
                            errorCode = false;
                        }
                        br.close();
                        for (int i = 0; i < baseStationList.size(); i++){
                            nodeNameSettingService.modNodeName(baseStationList.get(i));
                        }
                    }
                }
            }
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("上传处理的时间："+String.valueOf(endTime-startTime)+"ms");

        if(errorCode == true){
            return "redirect:/lte/nodeNameSettingInit?type=1";
        }else {
            return "redirect:/lte/nodeNameSettingInit?type=0";
        }
    }

    @RequestMapping(value = "nodeNameSettingInit")
    public String nodeNameSettingInit(){
        return "html/nodeNameSetting";
    }
}
