package com.ecom.util.tacUtil;

import com.ecom.util.dataBaseUtil.DBUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;

/**
 * Created by a7289 on 2016/12/29 0029.
 */
public class NodeInfoUtil {
    public static synchronized void updataNodeInfo(File[] tempList){
        try {
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    File enodeb = new File(tempList[i].toString());
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[i].toString())));
                    for (String line = br.readLine(); line != null; line = br.readLine()) {
                        String arr[] = line.split(" ");
                        if (arr.length == 9) {
                            String node_name = "";
                            String tac = "0";
                            String latitude = "0";
                            String longitude = "0";
                            String swversion = "0";
                            String ip = "0";
                            String license_user = "0";
                            String mme = "0";
                            String enb_id = "0";
                            if (arr[0].split(":").length == 2)
                                node_name = arr[0].split(":")[1];
                            if (arr[1].split(":").length == 2)
                                tac = arr[1].split(":")[1];
                            if (arr[2].split(":").length == 2)
                                longitude = arr[2].split(":")[1];
                            if (arr[3].split(":").length == 2)
                                latitude = arr[3].split(":")[1];
                            if (arr[4].split(":").length == 2)
                                swversion = arr[4].split(":")[1];
                            if (arr[5].split(":").length == 2)
                                ip = arr[5].split(":")[1];
                            if (arr[6].split(":").length == 2)
                                license_user = arr[6].split(":")[1];
                            if (arr[7].split(":").length == 2)
                                mme = arr[7].split(":")[1];
                            if (arr[8].split(":").length == 2)
                                enb_id = arr[8].split(":")[1];
                            if (latitude.length() >= 3) {
                                StringBuffer stringBuffer = new StringBuffer(latitude);
                                latitude = stringBuffer.insert(2, ".").toString();
                            }
                            if (longitude.length() >= 4) {
                                StringBuffer stringBuffer = new StringBuffer(longitude);
                                longitude = stringBuffer.insert(3, ".").toString();
                            }
                            DBUtil dbUtil = new DBUtil();
                            String sql = (new StringBuilder("SELECT no FROM base_station WHERE enb_name = '")).append(node_name).append("'").toString();
                            ResultSet rs = dbUtil.execQuery(sql, new Object[0]);
                            if (rs.next()) {
                                DBUtil dbUtil1 = new DBUtil();
                                String update_sql = (new StringBuilder("UPDATE base_station SET enb_id='")).append(enb_id).append("', longitude='").append(longitude).append("', latitude='").append(latitude).append("', tac='").append(tac).append("', swversion='").append(swversion).append("', ip='").append(ip).append("', mme='").append(mme).append("', license_user='").append(license_user).append("' WHERE enb_name='").append(node_name).append("' ").toString();
                                dbUtil1.execOther(update_sql, new Object[0]);
                                dbUtil1.closeConnection();
                            } else {
                                DBUtil dbUtil2 = new DBUtil();
                                int no = 0;
                                String select_sql = "SELECT no FROM base_station ORDER BY no DESC LIMIT 1";
                                ResultSet select_rs = dbUtil2.execQuery(select_sql, new Object[0]);
                                if (select_rs.next())
                                    no = Integer.parseInt(select_rs.getString("no")) + 1;
                                String insert_sql = (new StringBuilder("INSERT INTO base_station (no, enb_id, enb_name, longitude, latitude, tac, swversion, ip, mme, license_user) VALUES ('")).append(no).append("','").append(enb_id).append("','").append(node_name).append("','").append(longitude).append("','").append(latitude).append("','").append(tac).append("','").append(swversion).append("','").append(ip).append("','").append(mme).append("','").append(license_user).append("')").toString();
                                dbUtil2.execOther(insert_sql, new Object[0]);
                                dbUtil2.closeConnection();
                            }
                            dbUtil.closeConnection();
                        }
                    }
                    br.close();
                    enodeb.delete();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
