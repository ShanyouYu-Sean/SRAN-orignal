package com.ecom.util.tacUtil;

import com.ecom.util.dataBaseUtil.DBUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by a7289 on 2016/12/29 0029.
 */
public class AlarmUtil {

    public static synchronized void updataAlarmList(File[] tempList){
        if (tempList.length > 0)
        {
            DBUtil dbUtil = new DBUtil();
            dbUtil.execOther("TRUNCATE TABLE base_alarm ", new Object[0]);
            dbUtil.closeConnection();
        }
        try {
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    File alarm = new File(tempList[i].toString());
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i].toString())));
                    int k = 0;
                    String param = "";
                    for (String line = br.readLine(); line != null; line = br.readLine()) {
                        if (line.startsWith("|"))
                            line = line.substring(1, line.length());
                        if (line.endsWith("|"))
                            line = line.substring(0, line.length() - 1);
                        String arr[] = line.split("\\|");
                        if (arr.length == 5) {
                            String temp = "";
                            for (int j = 0; j < arr.length; j++)
                                if (j == 0)
                                    temp = (new StringBuilder("'")).append(arr[j].trim()).append("'").toString();
                                else
                                    temp = (new StringBuilder(String.valueOf(temp))).append(",'").append(arr[j].trim()).append("'").toString();

                            if (k < 500) {
                                if (param == "")
                                    param = (new StringBuilder("(")).append(temp).append(")").toString();
                                else
                                    param = (new StringBuilder(String.valueOf(param))).append(",(").append(temp).append(")").toString();
                            } else {
                                param = (new StringBuilder(String.valueOf(param))).append(",(").append(temp).append(")").toString();
                                String insert_sql = (new StringBuilder("INSERT INTO base_alarm (node_name, mo, alarm_time, alarm_name, alarm_type) VALUES ")).append(param).toString();
                                DBUtil dbUtil1 = new DBUtil();
                                dbUtil1.execOther(insert_sql, new Object[0]);
                                dbUtil1.closeConnection();
                                k = 0;
                                param = "";
                            }
                            k++;
                        }
                    }

                    String insert_sql = (new StringBuilder("INSERT INTO base_alarm (node_name, mo, alarm_time, alarm_name, alarm_type) VALUES ")).append(param).toString();
                    DBUtil dbUtil2 = new DBUtil();
                    dbUtil2.execOther(insert_sql, new Object[0]);
                    dbUtil2.closeConnection();
                    br.close();
                    alarm.delete();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
