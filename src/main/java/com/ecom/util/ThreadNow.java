package com.ecom.util;

import com.ecom.util.threadUtil.ThreadUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
@Component
@Scope("prototype")
public class ThreadNow extends Thread  {
    public volatile boolean shutdownRequested  = false;
    public final int SUSPEND = 0;
    public final int RUNNING = 1;
    public volatile int status = 1;
    public void run() {
        while (!shutdownRequested) {
            if (status == SUSPEND)
            {
                try
                {
                    // 若线程挂起则阻塞自己
                    wait();
                }
                catch (InterruptedException e)
                {
                    System.out.println("线程异常终止...");
                }
            }
            else {
                try {
                    ThreadUtil.scan3gFile();
                    ThreadUtil.scanCounterFile();
                    ThreadUtil.scanEnodebFile();
                    ThreadUtil.sacnAlarmFile();
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    System.out.println("err=>" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 停止
     */
    public final void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }
    /**
     * 恢复
     */
    public synchronized void myResume()
    {
        // 修改状态
        status = RUNNING;
        // 唤醒
        notifyAll();
    }

    /**
     * 挂起
     */
    public void mySuspend()
    {
        // 修改状态
        status = SUSPEND;
    }
}
