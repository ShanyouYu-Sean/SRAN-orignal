package com.ecom.util.threadUtil;

import com.ecom.util.dataBaseUtil.DBUtil;
import com.ecom.util.rncUtil.XmlUtil;
import com.ecom.util.tacUtil.AlarmUtil;
import com.ecom.util.tacUtil.CounterUtil;
import com.ecom.util.tacUtil.NodeInfoUtil;
import com.ecom.util.webSocketUtil.ScanWebSocket;

import java.io.*;
import java.util.concurrent.*;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
public class ThreadUtil {
    public static String getPath() {
        String path = ThreadUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try{
            path = java.net.URLDecoder.decode(path, "UTF-8");//转换处理中文及空格
        }catch (UnsupportedEncodingException e){
            return null;
        }
        return new File(path).getParent();
    }

    public static void scanCounterFile() {
        try {
            String path     = getPath();
            File file = new File((new StringBuilder(String.valueOf(path))).append("/counter/").toString());
            File[] tempList = file.listFiles();
            if (tempList.length == 0){
                System.out.println("没有新的counter数据导入");
            }else {
                for (int i = 0; i < tempList.length; i++) {
                    if (tempList[i].isFile()) {
                        System.out.println("有新的counter数据导入");
                        long startTime = System.currentTimeMillis();

                        String in_time = CounterUtil.calculateCell(tempList[i].toString());
                        tempList[i].delete();
                        long cellTime = System.currentTimeMillis();
                        System.out.println("处理cell完毕，共用"+(cellTime-startTime)+"毫秒");

                        CounterUtil.calculateNode(in_time);
                        long nodeTime = System.currentTimeMillis();
                        System.out.println("处理node完毕，共用"+(nodeTime-cellTime)+"毫秒");

                        CounterUtil.calculateTac(in_time);
                        long tacTime = System.currentTimeMillis();
                        System.out.println("处理tac完毕，共用"+(tacTime-nodeTime)+"毫秒");

                        System.out.println("数据导入成功");
                        for(ScanWebSocket item: ScanWebSocket.getWebSocketSet()){
                                item.sendMessage("刷新");
                                continue;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void scanEnodebFile(){
            try {
                String path = getPath();
                File file = new File((new StringBuilder(String.valueOf(path))).append("/node_info/").toString());
                File[] tempList = file.listFiles();
                if (tempList.length == 0) {
                    System.out.println("没有新的基础数据导入");
                } else {
                    System.out.println("有新的基础数据导入");
                    long startTime = System.currentTimeMillis();

                    NodeInfoUtil.updataNodeInfo(tempList);

                    long nodeInfoTime = System.currentTimeMillis();
                    System.out.println("处理基础数据完毕，共用"+(nodeInfoTime-startTime)+"毫秒");

                    for(ScanWebSocket item: ScanWebSocket.getWebSocketSet()){
                            item.sendMessage("刷新");
                            continue;
                    }
                }
            }
            catch(Exception e)
                {
                    e.printStackTrace();
                }
    }

    public static void sacnAlarmFile() {
        try
        {
            String path = getPath();
            File file = new File((new StringBuilder(String.valueOf(path))).append("/alarm/").toString());
            File[] tempList = file.listFiles();

            if (tempList.length == 0) {
                System.out.println("没有新的告警数据导入");
            } else {
                System.out.println("有新的告警数据导入");
                long startTime = System.currentTimeMillis();

                AlarmUtil.updataAlarmList(tempList);

                long alarmTime = System.currentTimeMillis();
                System.out.println("处理告警数据完毕，共用"+(alarmTime-startTime)+"毫秒");

                for(ScanWebSocket item: ScanWebSocket.getWebSocketSet()){
                        item.sendMessage("刷新");
                        continue;
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void scan3gFile() {
        try {
            String path     = getPath();
            File file = new File((new StringBuilder(String.valueOf(path))).append("/3g/").toString());
            File[] tempList = file.listFiles();
            if (tempList == null || tempList.length == 0){
                System.out.println("没有新的3g数据导入");
            }else {
                System.out.println("有新的3g数据导入");

                ThreadPoolExecutor threadPool = new ThreadPoolExecutor(40, 50, 2, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(50));

                DBUtil dbUtil = new DBUtil();
                dbUtil.execOther("TRUNCATE TABLE base_rnc_3g ", new Object[]{});
                dbUtil.execOther("TRUNCATE TABLE base_node_3g ", new Object[]{});
                dbUtil.execOther("TRUNCATE TABLE base_cell_3g ", new Object[]{});
                dbUtil.execOther("TRUNCATE TABLE temp_3g ", new Object[]{});
                dbUtil.closeConnection();

                long startTime = System.currentTimeMillis();
                CountDownLatch latch=new CountDownLatch(tempList.length);
                System.out.println(tempList.length);
                for (int i = 0; i < tempList.length; i++) {
                    if (tempList[i].isFile()) {
                        String filePath = tempList[i].toString();
                        threadPool.execute(new ScanXmlRootAndCalculateCell3gThread(filePath,latch));
                    }
                }
                latch.await();
                for (File f:tempList) {
                    f.delete();
                }
                long cell3gTime = System.currentTimeMillis();
                System.out.println("处理cell3g完毕，共用" + (cell3gTime - startTime) + "毫秒");

                XmlUtil.calculateNode3g();
                long node3gTime = System.currentTimeMillis();
                System.out.println("处理node3g数据完毕，共用"+(node3gTime-cell3gTime)+"毫秒");
                XmlUtil.calculateRnc3g();
                long rnc3gTime = System.currentTimeMillis();
                System.out.println("处理rnc3g数据完毕，共用"+(rnc3gTime-node3gTime)+"毫秒");
                for(ScanWebSocket item: ScanWebSocket.getWebSocketSet()){
                    item.sendMessage("刷新");
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
