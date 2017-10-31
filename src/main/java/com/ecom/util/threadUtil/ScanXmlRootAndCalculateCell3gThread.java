package com.ecom.util.threadUtil;

import com.ecom.util.rncUtil.XmlEntity;
import com.ecom.util.rncUtil.XmlUtil;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by a7289 on 2017/1/20 0020.
 */
public class ScanXmlRootAndCalculateCell3gThread extends Thread {
    private String filePath;
    private CountDownLatch latch;
    private boolean complete = false;
    public ScanXmlRootAndCalculateCell3gThread(String filePath, CountDownLatch latch){
        this.filePath = filePath;
        this.latch = latch;
    }
    public void run() {
        while (!complete){
            try {
//                System.out.println("开始"+filePath);
                XmlUtil.getXmlRootAndCalculateCell3g(filePath);
//                System.out.println("结束"+filePath);
                complete = true;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        }
    }
}
