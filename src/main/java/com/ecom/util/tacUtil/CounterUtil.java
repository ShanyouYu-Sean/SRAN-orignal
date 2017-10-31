package com.ecom.util.tacUtil;

import com.ecom.entity.BaseQuota;
import com.ecom.entity.QuotaThresholdCell;
import com.ecom.entity.QuotaThresholdNode;
import com.ecom.entity.QuotaThresholdTac;
import com.ecom.util.dataBaseUtil.DBUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by a7289 on 2016/12/29 0029.
 */
public class CounterUtil {
    public static String calculateCell(String filePath) {

        List<BaseQuota> baseQuotaList = CounterUtil.getQuota("quota_threshold_cell");
        try {
            int i = 0;
            String time = "";
            DBUtil dbUtil = new DBUtil();
            dbUtil.execOther("TRUNCATE TABLE base_tac ", new Object[]{});
            dbUtil.execOther("TRUNCATE TABLE base_node ", new Object[]{});
            dbUtil.execOther("TRUNCATE TABLE base_cell ", new Object[]{});
            dbUtil.execOther("TRUNCATE TABLE temp ", new Object[]{});
            dbUtil.closeConnection();



            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            int j = 1;
            int t = 1;
            String parm = "";
            String apps = "";
            String apps1 = "";
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if(i == 0) {

                } else if(i == 1) {
                    time = line;
                } else if (i == 2) {

                }else{	String[] arr = line.split(",");
                    String temp = "";
                    for(int k = 0; k<arr.length; k++) {
                        if(k == 0){
                            temp = "'"+arr[k]+"'";
                        }else{
                            temp = temp + ",'"+arr[k]+"'";
                        }
                    }
                    if(j < 500)
                    {
                        if(parm == "")
                        {

                            parm = "("+temp+")";
                        }else{
                            parm = parm + ",("+temp+")";
                        }
                    }
                    else
                    {
                        parm = parm + ",("+temp+")";
                        String insert_sql = "INSERT INTO temp (cell_name,EUtranCellFDD_pmRrcConnEstabAtt,EUtranCellTDD_pmRrcConnEstabAtt,EUtranCellFDD_pmRrcConnEstabAttReatt,EUtranCellTDD_pmRrcConnEstabAttReatt,EUtranCellFDD_pmRrcConnEstabSucc,EUtranCellTDD_pmRrcConnEstabSucc,EUtranCellFDD_pmS1SigConnEstabAtt,EUtranCellTDD_pmS1SigConnEstabAtt,EUtranCellFDD_pmS1SigConnEstabSucc,EUtranCellTDD_pmS1SigConnEstabSucc,EUtranCellFDD_pmErabEstabAttInit,EUtranCellTDD_pmErabEstabAttInit,EUtranCellFDD_pmErabEstabAttAdded,EUtranCellTDD_pmErabEstabAttAdded,EUtranCellFDD_pmErabEstabSuccInit,EUtranCellTDD_pmErabEstabSuccInit,EUtranCellFDD_pmErabEstabSuccAdded,EUtranCellTDD_pmErabEstabSuccAdded,EUtranCellFDD_pmPagReceived,EUtranCellTDD_pmPagReceived,EUtranCellFDD_pmPagDiscarded,EUtranCellTDD_pmPagDiscarded,EUtranCellFDD_pmErabRelAbnormalEnbAct,EUtranCellTDD_pmErabRelAbnormalEnbAct,EUtranCellFDD_pmErabRelMmeAct,EUtranCellTDD_pmErabRelMmeAct,EUtranCellRelation_pmHoPrepAttLteInterF,EUtranCellRelation_pmHoPrepAttLteIntraF,EUtranCellRelation_pmHoExeSuccLteInterF,EUtranCellRelation_pmHoExeSuccLteIntraF,pmPrbUsedDlFirstTrans,pmPrbUsedDlBcch,pmPrbUsedDlDtch,pmPrbUsedDlPcch,pmPrbUsedDlSrbFirstTrans,pmPrbUsedDlReTrans,pmPrbUsedUlDtch,pmPrbUsedUlSrb,pmPdcpVolDlDrb,pmPdcpVolUlDrb,pmUeThpTimeDl,pmPdcpVolDlDrbLastTTI,pmUeThpTimeUl,pmUeThpVolUl,pmSessionTimeUe,pmSessionTimeDrb,pmActiveUeDlSum,pmActiveUeUlSum,pmErabLevSamp,pmCellDowntimeAuto)VALUES"+parm;
                        DBUtil dbUtil1 = new DBUtil();
                        dbUtil1.execOther(insert_sql, new Object[]{});
                        dbUtil1.closeConnection();
                        j = 0;
                        parm = "";
                    }
                    j++;

                    String cell_name = arr[0];
                    float EUtranCellFDD_pmRrcConnEstabAtt      = Float.parseFloat(arr[1]);
                    float EUtranCellTDD_pmRrcConnEstabAtt      = Float.parseFloat(arr[2]);
                    float EUtranCellFDD_pmRrcConnEstabAttReatt = Float.parseFloat(arr[3]);
                    float EUtranCellTDD_pmRrcConnEstabAttReatt = Float.parseFloat(arr[4]);
                    float EUtranCellFDD_pmRrcConnEstabSucc     = Float.parseFloat(arr[5]);
                    float EUtranCellTDD_pmRrcConnEstabSucc     = Float.parseFloat(arr[6]);

                    float EUtranCellFDD_pmS1SigConnEstabAtt    = Float.parseFloat(arr[7]);
                    float EUtranCellTDD_pmS1SigConnEstabAtt = Float.parseFloat(arr[8]);
                    float EUtranCellFDD_pmS1SigConnEstabSucc = Float.parseFloat(arr[9]);
                    float EUtranCellTDD_pmS1SigConnEstabSucc = Float.parseFloat(arr[10]);
                    float EUtranCellFDD_pmErabEstabAttInit = Float.parseFloat(arr[11]);
                    float EUtranCellTDD_pmErabEstabAttInit = Float.parseFloat(arr[12]);
                    float EUtranCellFDD_pmErabEstabAttAdded = Float.parseFloat(arr[13]);
                    float EUtranCellTDD_pmErabEstabAttAdded = Float.parseFloat(arr[14]);
                    float EUtranCellFDD_pmErabEstabSuccInit = Float.parseFloat(arr[15]);
                    float EUtranCellTDD_pmErabEstabSuccInit = Float.parseFloat(arr[16]);
                    float EUtranCellFDD_pmErabEstabSuccAdded = Float.parseFloat(arr[17]);
                    float EUtranCellTDD_pmErabEstabSuccAdded = Float.parseFloat(arr[18]);
                    float EUtranCellFDD_pmPagReceived = Float.parseFloat(arr[19]);
                    float EUtranCellTDD_pmPagReceived = Float.parseFloat(arr[20]);
                    float EUtranCellFDD_pmPagDiscarded = Float.parseFloat(arr[21]);
                    float EUtranCellTDD_pmPagDiscarded = Float.parseFloat(arr[22]);
                    float EUtranCellFDD_pmErabRelAbnormalEnbAct = Float.parseFloat(arr[23]);
                    float EUtranCellTDD_pmErabRelAbnormalEnbAct = Float.parseFloat(arr[24]);
                    float EUtranCellFDD_pmErabRelMmeAct = Float.parseFloat(arr[25]);
                    float EUtranCellTDD_pmErabRelMmeAct = Float.parseFloat(arr[26]);
                    float EUtranCellRelation_pmHoPrepAttLteInterF = Float.parseFloat(arr[27]);
                    float EUtranCellRelation_pmHoPrepAttLteIntraF = Float.parseFloat(arr[28]);
                    float EUtranCellRelation_pmHoExeSuccLteInterF = Float.parseFloat(arr[29]);
                    float EUtranCellRelation_pmHoExeSuccLteIntraF = Float.parseFloat(arr[30]);
                    float pmPrbUsedDlFirstTrans = Float.parseFloat(arr[31]);
                    float pmPrbUsedDlBcch = Float.parseFloat(arr[32]);
                    float pmPrbUsedDlDtch = Float.parseFloat(arr[33]);
                    float pmPrbUsedDlPcch = Float.parseFloat(arr[34]);
                    float pmPrbUsedDlSrbFirstTrans = Float.parseFloat(arr[35]);
                    float pmPrbUsedDlReTrans = Float.parseFloat(arr[36]);
                    float pmPrbUsedUlDtch = Float.parseFloat(arr[37]);
                    float pmPrbUsedUlSrb = Float.parseFloat(arr[38]);
                    float pmPdcpVolDlDrb = Float.parseFloat(arr[39]);
                    float pmPdcpVolUlDrb = Float.parseFloat(arr[40]);
                    float pmUeThpTimeDl = Float.parseFloat(arr[41]);
                    float pmPdcpVolDlDrbLastTTI = Float.parseFloat(arr[42]);
                    float pmUeThpTimeUl = Float.parseFloat(arr[43]);
                    float pmUeThpVolUl = Float.parseFloat(arr[44]);
                    float pmSessionTimeUe = Float.parseFloat(arr[45]);
                    float pmSessionTimeDrb = Float.parseFloat(arr[46]);
                    float pmActiveUeDlSum = Float.parseFloat(arr[47]);
                    float pmActiveUeUlSum = Float.parseFloat(arr[48]);
                    float pmErabLevSamp = Float.parseFloat(arr[49]);
                    float pmCellDowntimeAuto = Float.parseFloat(arr[50]);

                    float RRC_setup_succ_Rate;
                    if(((EUtranCellFDD_pmRrcConnEstabAtt+EUtranCellTDD_pmRrcConnEstabAtt)-(EUtranCellFDD_pmRrcConnEstabAttReatt+EUtranCellTDD_pmRrcConnEstabAttReatt)) == 0)
                    {
                        RRC_setup_succ_Rate = 0;
                    }
                    else
                    {
                        RRC_setup_succ_Rate = (float)100*((EUtranCellFDD_pmRrcConnEstabSucc+EUtranCellTDD_pmRrcConnEstabSucc)/((EUtranCellFDD_pmRrcConnEstabAtt+EUtranCellTDD_pmRrcConnEstabAtt)-(EUtranCellFDD_pmRrcConnEstabAttReatt+EUtranCellTDD_pmRrcConnEstabAttReatt)));
                    }

                    float S1_Setup_Succ_Rate;
                    if((EUtranCellFDD_pmS1SigConnEstabAtt+EUtranCellTDD_pmS1SigConnEstabAtt) == 0)
                    {
                        S1_Setup_Succ_Rate = 0;
                    }
                    else
                    {
                        S1_Setup_Succ_Rate = (float)100*((EUtranCellFDD_pmS1SigConnEstabSucc+EUtranCellTDD_pmS1SigConnEstabSucc)/(EUtranCellFDD_pmS1SigConnEstabAtt+EUtranCellTDD_pmS1SigConnEstabAtt));;
                    }

                    float ERAB_Setup_Succ_Rate;
                    if(((EUtranCellFDD_pmErabEstabAttInit+EUtranCellTDD_pmErabEstabAttInit)+(EUtranCellFDD_pmErabEstabAttAdded+EUtranCellTDD_pmErabEstabAttAdded)) == 0)
                    {
                        ERAB_Setup_Succ_Rate = 0;
                    }
                    else
                    {
                        ERAB_Setup_Succ_Rate = (float)100*(((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit)+ (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded))/((EUtranCellFDD_pmErabEstabAttInit+EUtranCellTDD_pmErabEstabAttInit)+(EUtranCellFDD_pmErabEstabAttAdded+EUtranCellTDD_pmErabEstabAttAdded)));
                    }

                    float paging_Succ_Rate;
                    if((EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived) == 0)
                    {
                        paging_Succ_Rate = 0;
                    }
                    else
                    {
                        paging_Succ_Rate = (float)100*((EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived)-(EUtranCellFDD_pmPagDiscarded+EUtranCellTDD_pmPagDiscarded))/(EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived);
                    }

                    float Erab_Drop_Rate;
                    if(((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit) + (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded)) == 0)
                    {
                        Erab_Drop_Rate = 0;
                    }
                    else
                    {
                        Erab_Drop_Rate = (float)100*((EUtranCellFDD_pmErabRelAbnormalEnbAct+EUtranCellTDD_pmErabRelAbnormalEnbAct) + (EUtranCellFDD_pmErabRelMmeAct+EUtranCellTDD_pmErabRelMmeAct))/((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit) + (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded));
                    }

                    float Within_System_HO_Succ_Rate;
                    if((EUtranCellRelation_pmHoPrepAttLteInterF+EUtranCellRelation_pmHoPrepAttLteIntraF) == 0)
                    {
                        Within_System_HO_Succ_Rate = 100;
                    }
                    else
                    {
                        Within_System_HO_Succ_Rate = (float)100*(EUtranCellRelation_pmHoExeSuccLteInterF+EUtranCellRelation_pmHoExeSuccLteIntraF)/(EUtranCellRelation_pmHoPrepAttLteInterF+EUtranCellRelation_pmHoPrepAttLteIntraF);
                    }

                    float Cell_Utilization_Rate;
                    if(pmErabLevSamp == 0)
                    {
                        Cell_Utilization_Rate = 0;
                    }
                    else
                    {
                        Cell_Utilization_Rate = (float)100*((1-(pmCellDowntimeAuto)/((pmErabLevSamp)*5)));
                    }

                    float UL_PRB_Occupy_Rate;
                    UL_PRB_Occupy_Rate = (float)(pmPrbUsedUlDtch + pmPrbUsedUlSrb)/(900*1000)/100;

                    float DL_PRB_Occupy_Rate;
                    if(pmPrbUsedDlFirstTrans == 0)
                    {
                        DL_PRB_Occupy_Rate = 0;
                    }
                    else
                    {
                        DL_PRB_Occupy_Rate = (float)(((pmPrbUsedDlBcch+pmPrbUsedDlDtch+pmPrbUsedDlPcch)+(pmPrbUsedDlSrbFirstTrans)*(1+pmPrbUsedDlReTrans/pmPrbUsedDlFirstTrans))/(900*1000)/100);
                    }

                    float Dl_ThroughputRate_Mbps;
                    Dl_ThroughputRate_Mbps = (float)(pmPdcpVolDlDrb)/900/1024;

                    float Ul_ThroughputRate_Mbps;
                    Ul_ThroughputRate_Mbps = (float)(pmPdcpVolUlDrb)/900/1024;

                    float DL_User_Speed_Mbps;
                    if((pmUeThpTimeDl/1000) <= 0)
                    {
                        DL_User_Speed_Mbps = 0;
                    }
                    else
                    {
                        DL_User_Speed_Mbps = (float)((pmPdcpVolDlDrb-pmPdcpVolDlDrbLastTTI)/(pmUeThpTimeDl/1000))/1024;
                    }

                    float UL_User_Speed_Mbps;
                    if((pmUeThpTimeUl/1000) <= 0)
                    {
                        UL_User_Speed_Mbps = 0;
                    }
                    else
                    {
                        UL_User_Speed_Mbps = (float)(pmUeThpVolUl/(pmUeThpTimeUl/1000))/1024;
                    }

                    float UE_SessionTime;
                    UE_SessionTime = pmSessionTimeUe+0;

                    float Drb_pmSessionTime;
                    Drb_pmSessionTime = pmSessionTimeDrb+0;

                    float DL_Active_User_Num;
                    DL_Active_User_Num = (float)pmActiveUeDlSum/900000;

                    float UL_Active_User_Num;
                    UL_Active_User_Num = (float)pmActiveUeUlSum/900000;


//                    List<Integer> statusList = new ArrayList<Integer>();
//                    for (int m = 0; m < baseQuotaList.size(); m++){
//                        BaseQuota baseQuota = baseQuotaList.get(m);
//                        String baseName = baseQuota.getQuotaName();
//                        if (baseName.equals("Session_Setup_Success_Rate")){
////                          compare(baseQuota,baseStatus.getRrcSetupSuccRate());
//                        }else if (baseName.equals("RRC_setup_succ_Rate")){
//                            statusList.add(compare(baseQuota,RRC_setup_succ_Rate));
//                        }else if (baseName.equals("S1_Setup_Succ_Rate")){
//                            statusList.add(compare(baseQuota,S1_Setup_Succ_Rate));
//                        }else if (baseName.equals("ERAB_Setup_Succ_Rate")){
//                            statusList.add(compare(baseQuota,ERAB_Setup_Succ_Rate));
//                        }else if (baseName.equals("paging_Succ_Rate")){
//                            statusList.add(compare(baseQuota,paging_Succ_Rate));
//                        }else if (baseName.equals("Cell_Utilization_Rate")){
//                            statusList.add(compare(baseQuota,Cell_Utilization_Rate));
//                        }else if (baseName.equals("Within_System_HO_Succ_Rate")){
//                            statusList.add(compare(baseQuota,Within_System_HO_Succ_Rate));
//                        }else if (baseName.equals("Erab_Drop_Rate")){
//                            statusList.add(compare(baseQuota,Erab_Drop_Rate));
//                        }else if (baseName.equals("DL_PRB_Occupy_Rate")){
//                            statusList.add(compare(baseQuota,DL_PRB_Occupy_Rate));
//                        }else if (baseName.equals("UL_PRB_Occupy_Rate")){
//                            statusList.add(compare(baseQuota,UL_PRB_Occupy_Rate));
//                        }else if (baseName.equals("Dl_ThroughputRate_Mbps")) {
//                            statusList.add(compare(baseQuota, Dl_ThroughputRate_Mbps));
//                        }else if (baseName.equals("Ul_ThroughputRate_Mbps")){
//                            statusList.add(compare(baseQuota,Ul_ThroughputRate_Mbps));
//                        }else if (baseName.equals("DL_User_Speed_Mbps")){
//                            statusList.add(compare(baseQuota,DL_User_Speed_Mbps));
//                        }else if (baseName.equals("UL_User_Speed_Mbps")){
//                            statusList.add(compare(baseQuota,UL_User_Speed_Mbps));
//                        }else if (baseName.equals("UE_SessionTime")){
//                            statusList.add(compare(baseQuota,UE_SessionTime));
//                        }else if (baseName.equals("Drb_pmSessionTime")){
//                            statusList.add(compare(baseQuota,Drb_pmSessionTime));
//                        }else if (baseName.equals("DL_Active_User_Num")){
//                            statusList.add(compare(baseQuota,DL_Active_User_Num));
//                        }else if (baseName.equals("UL_Active_User_Num")){
//                            statusList.add(compare(baseQuota,UL_Active_User_Num));
//                        }
//                    }
//                    Integer status = Collections.max(statusList);


                    Integer status = modStatus( baseQuotaList,
                            RRC_setup_succ_Rate, S1_Setup_Succ_Rate, ERAB_Setup_Succ_Rate,paging_Succ_Rate, Cell_Utilization_Rate,
                            Within_System_HO_Succ_Rate, Erab_Drop_Rate, DL_PRB_Occupy_Rate, UL_PRB_Occupy_Rate, Dl_ThroughputRate_Mbps,
                            Ul_ThroughputRate_Mbps, DL_User_Speed_Mbps, UL_User_Speed_Mbps, UE_SessionTime, Drb_pmSessionTime,
                            DL_Active_User_Num, UL_Active_User_Num);

                    String str = "'"+time+"', '"+
                            cell_name+"', '"+
                            status+"', '"+
                            RRC_setup_succ_Rate+"', '"+
                            S1_Setup_Succ_Rate+"', '"+
                            ERAB_Setup_Succ_Rate+"', '"+
                            paging_Succ_Rate+"', '"+
                            Erab_Drop_Rate+"', '"+
                            Within_System_HO_Succ_Rate+"', '"+
                            Cell_Utilization_Rate+"', '"+
                            UL_PRB_Occupy_Rate+"', '"+
                            DL_PRB_Occupy_Rate+"', '"+
                            Dl_ThroughputRate_Mbps+"', '"+
                            Ul_ThroughputRate_Mbps+"', '"+
                            DL_User_Speed_Mbps+"', '"+
                            UL_User_Speed_Mbps+"', '"+
                            UE_SessionTime+"', '"+
                            Drb_pmSessionTime+"', '"+
                            DL_Active_User_Num+"', '"+
                            UL_Active_User_Num+"'";

                    String str1 = "'"+cell_name+"', '"+
                            status+"', '"+
                            RRC_setup_succ_Rate+"', '"+
                            S1_Setup_Succ_Rate+"', '"+
                            ERAB_Setup_Succ_Rate+"', '"+
                            paging_Succ_Rate+"', '"+
                            Erab_Drop_Rate+"', '"+
                            Within_System_HO_Succ_Rate+"', '"+
                            Cell_Utilization_Rate+"', '"+
                            UL_PRB_Occupy_Rate+"', '"+
                            DL_PRB_Occupy_Rate+"', '"+
                            Dl_ThroughputRate_Mbps+"', '"+
                            Ul_ThroughputRate_Mbps+"', '"+
                            DL_User_Speed_Mbps+"', '"+
                            UL_User_Speed_Mbps+"', '"+
                            UE_SessionTime+"', '"+
                            Drb_pmSessionTime+"', '"+
                            DL_Active_User_Num+"', '"+
                            UL_Active_User_Num+"'";

                    if(t < 500)
                    {
                        if(apps == "")
                        {

                            apps = "("+str+")";
                        }else{
                            apps = apps + ",("+str+")";
                        }

                        if(apps1 == "")
                        {

                            apps1 = "("+str1+")";
                        }else{
                            apps1 = apps1 + ",("+str1+")";
                        }
                    }
                    else
                    {
                        DBUtil dbUtil2 = new DBUtil();

                        apps = apps + ",("+str+")";
                        String insert_sql_1 = "INSERT INTO `counter` ( `time`, `cell_name`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+apps;
                        dbUtil2.execOther(insert_sql_1, new Object[]{});

                        apps1 = apps1 + ",("+str1+")";
                        String insert_sql_2 = "INSERT INTO `base_cell` (`cell_name`,`status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+apps1;
                        dbUtil2.execOther(insert_sql_2, new Object[]{});

                        t = 0;
                        apps = "";
                        apps1 = "";

                        dbUtil2.closeConnection();
                    }
                    t++;

                }
                i++;
            }

            DBUtil dbUtil3 = new DBUtil();

            String insert_sql = "INSERT INTO temp (cell_name,EUtranCellFDD_pmRrcConnEstabAtt,EUtranCellTDD_pmRrcConnEstabAtt,EUtranCellFDD_pmRrcConnEstabAttReatt,EUtranCellTDD_pmRrcConnEstabAttReatt,EUtranCellFDD_pmRrcConnEstabSucc,EUtranCellTDD_pmRrcConnEstabSucc,EUtranCellFDD_pmS1SigConnEstabAtt,EUtranCellTDD_pmS1SigConnEstabAtt,EUtranCellFDD_pmS1SigConnEstabSucc,EUtranCellTDD_pmS1SigConnEstabSucc,EUtranCellFDD_pmErabEstabAttInit,EUtranCellTDD_pmErabEstabAttInit,EUtranCellFDD_pmErabEstabAttAdded,EUtranCellTDD_pmErabEstabAttAdded,EUtranCellFDD_pmErabEstabSuccInit,EUtranCellTDD_pmErabEstabSuccInit,EUtranCellFDD_pmErabEstabSuccAdded,EUtranCellTDD_pmErabEstabSuccAdded,EUtranCellFDD_pmPagReceived,EUtranCellTDD_pmPagReceived,EUtranCellFDD_pmPagDiscarded,EUtranCellTDD_pmPagDiscarded,EUtranCellFDD_pmErabRelAbnormalEnbAct,EUtranCellTDD_pmErabRelAbnormalEnbAct,EUtranCellFDD_pmErabRelMmeAct,EUtranCellTDD_pmErabRelMmeAct,EUtranCellRelation_pmHoPrepAttLteInterF,EUtranCellRelation_pmHoPrepAttLteIntraF,EUtranCellRelation_pmHoExeSuccLteInterF,EUtranCellRelation_pmHoExeSuccLteIntraF,pmPrbUsedDlFirstTrans,pmPrbUsedDlBcch,pmPrbUsedDlDtch,pmPrbUsedDlPcch,pmPrbUsedDlSrbFirstTrans,pmPrbUsedDlReTrans,pmPrbUsedUlDtch,pmPrbUsedUlSrb,pmPdcpVolDlDrb,pmPdcpVolUlDrb,pmUeThpTimeDl,pmPdcpVolDlDrbLastTTI,pmUeThpTimeUl,pmUeThpVolUl,pmSessionTimeUe,pmSessionTimeDrb,pmActiveUeDlSum,pmActiveUeUlSum,pmErabLevSamp,pmCellDowntimeAuto)VALUES"+parm;
            dbUtil3.execOther(insert_sql, new Object[]{});

            String insert_sql_1 = "INSERT INTO `counter` ( `time`, `cell_name`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+apps;
            dbUtil3.execOther(insert_sql_1, new Object[]{});

            String insert_sql_2 = "INSERT INTO `base_cell` (`cell_name`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+apps1;
            dbUtil3.execOther(insert_sql_2, new Object[]{});

            br.close();

            dbUtil3.closeConnection();

            return time;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void calculateNode(String in_time) {

        List<BaseQuota> baseQuotaList = CounterUtil.getQuota("quota_threshold_node");
        if(in_time.equals(null))
        {
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            in_time = dateFormater.format(date);
        }
        try {

            DBUtil dbUtil = new DBUtil();

            String sql = "SELECT "+
                    "left(b.cell_name, 9) AS node_name, "+
                    "SUM(b.EUtranCellFDD_pmRrcConnEstabAtt) AS EUtranCellFDD_pmRrcConnEstabAtt, "+
                    "SUM(b.EUtranCellFDD_pmRrcConnEstabAttReatt) AS EUtranCellFDD_pmRrcConnEstabAttReatt, "+
                    "SUM(b.EUtranCellFDD_pmRrcConnEstabSucc) AS EUtranCellFDD_pmRrcConnEstabSucc, "+
                    "SUM(b.EUtranCellTDD_pmRrcConnEstabAtt) AS EUtranCellTDD_pmRrcConnEstabAtt, "+
                    "SUM(b.EUtranCellTDD_pmRrcConnEstabAttReatt) AS EUtranCellTDD_pmRrcConnEstabAttReatt, "+
                    "SUM(b.EUtranCellTDD_pmRrcConnEstabSucc) AS EUtranCellTDD_pmRrcConnEstabSucc, "+
                    "SUM(b.EUtranCellFDD_pmS1SigConnEstabAtt) AS EUtranCellFDD_pmS1SigConnEstabAtt, "+
                    "SUM(b.EUtranCellTDD_pmS1SigConnEstabAtt) AS EUtranCellTDD_pmS1SigConnEstabAtt, "+
                    "SUM(b.EUtranCellFDD_pmS1SigConnEstabSucc) AS EUtranCellFDD_pmS1SigConnEstabSucc, "+
                    "SUM(b.EUtranCellTDD_pmS1SigConnEstabSucc) AS EUtranCellTDD_pmS1SigConnEstabSucc, "+
                    "SUM(b.EUtranCellFDD_pmErabEstabAttInit) AS EUtranCellFDD_pmErabEstabAttInit, "+
                    "SUM(b.EUtranCellTDD_pmErabEstabAttInit) AS EUtranCellTDD_pmErabEstabAttInit, "+
                    "SUM(b.EUtranCellFDD_pmErabEstabAttAdded) AS EUtranCellFDD_pmErabEstabAttAdded, "+
                    "SUM(b.EUtranCellTDD_pmErabEstabAttAdded) AS EUtranCellTDD_pmErabEstabAttAdded, "+
                    "SUM(b.EUtranCellFDD_pmErabEstabSuccInit) AS EUtranCellFDD_pmErabEstabSuccInit, "+
                    "SUM(b.EUtranCellTDD_pmErabEstabSuccInit) AS EUtranCellTDD_pmErabEstabSuccInit, "+
                    "SUM(b.EUtranCellFDD_pmErabEstabSuccAdded) AS EUtranCellFDD_pmErabEstabSuccAdded, "+
                    "SUM(b.EUtranCellTDD_pmErabEstabSuccAdded) AS EUtranCellTDD_pmErabEstabSuccAdded, "+
                    "SUM(b.EUtranCellFDD_pmPagReceived) AS EUtranCellFDD_pmPagReceived, "+
                    "SUM(b.EUtranCellTDD_pmPagReceived) AS EUtranCellTDD_pmPagReceived, "+
                    "SUM(b.EUtranCellFDD_pmPagDiscarded) AS EUtranCellFDD_pmPagDiscarded, "+
                    "SUM(b.EUtranCellTDD_pmPagDiscarded) AS EUtranCellTDD_pmPagDiscarded, "+
                    "SUM(b.EUtranCellFDD_pmErabRelAbnormalEnbAct) AS EUtranCellFDD_pmErabRelAbnormalEnbAct, "+
                    "SUM(b.EUtranCellTDD_pmErabRelAbnormalEnbAct) AS EUtranCellTDD_pmErabRelAbnormalEnbAct, "+
                    "SUM(b.EUtranCellFDD_pmErabRelMmeAct) AS EUtranCellFDD_pmErabRelMmeAct, "+
                    "SUM(b.EUtranCellTDD_pmErabRelMmeAct) AS EUtranCellTDD_pmErabRelMmeAct, "+
                    "SUM(b.EUtranCellRelation_pmHoPrepAttLteInterF) AS EUtranCellRelation_pmHoPrepAttLteInterF, "+
                    "SUM(b.EUtranCellRelation_pmHoPrepAttLteIntraF) AS EUtranCellRelation_pmHoPrepAttLteIntraF, "+
                    "SUM(b.EUtranCellRelation_pmHoExeSuccLteInterF) AS EUtranCellRelation_pmHoExeSuccLteInterF, "+
                    "SUM(b.EUtranCellRelation_pmHoExeSuccLteIntraF) AS EUtranCellRelation_pmHoExeSuccLteIntraF, "+
                    "SUM(b.pmPrbUsedDlFirstTrans) AS pmPrbUsedDlFirstTrans, "+
                    "SUM(b.pmPrbUsedDlBcch) AS pmPrbUsedDlBcch, "+
                    "SUM(b.pmPrbUsedDlDtch) AS pmPrbUsedDlDtch, "+
                    "SUM(b.pmPrbUsedDlPcch) AS pmPrbUsedDlPcch, "+
                    "SUM(b.pmPrbUsedDlSrbFirstTrans) AS pmPrbUsedDlSrbFirstTrans, "+
                    "SUM(b.pmPrbUsedDlReTrans) AS pmPrbUsedDlReTrans, "+
                    "SUM(b.pmPrbUsedUlDtch) AS pmPrbUsedUlDtch, "+
                    "SUM(b.pmPrbUsedUlSrb) AS pmPrbUsedUlSrb, "+
                    "SUM(b.pmPdcpVolDlDrb) AS pmPdcpVolDlDrb, "+
                    "SUM(b.pmPdcpVolUlDrb) AS pmPdcpVolUlDrb, "+
                    "SUM(b.pmUeThpTimeDl) AS pmUeThpTimeDl, "+
                    "SUM(b.pmPdcpVolDlDrbLastTTI) AS pmPdcpVolDlDrbLastTTI, "+
                    "SUM(b.pmUeThpTimeUl) AS pmUeThpTimeUl, "+
                    "SUM(b.pmUeThpVolUl) AS pmUeThpVolUl, "+
                    "SUM(b.pmSessionTimeUe) AS pmSessionTimeUe, "+
                    "SUM(b.pmSessionTimeDrb) AS pmSessionTimeDrb, "+
                    "SUM(b.pmActiveUeDlSum) AS pmActiveUeDlSum, "+
                    "SUM(b.pmActiveUeUlSum) AS pmActiveUeUlSum, "+
                    "SUM(b.pmErabLevSamp) AS pmErabLevSamp, "+
                    "SUM(b.pmCellDowntimeAuto) AS pmCellDowntimeAuto "+
                    "FROM temp b "+
                    "GROUP BY left(b.cell_name, 9)";
            ResultSet rs  = dbUtil.execQuery(sql,  new Object[]{});
            int k = 0;
            String param = "";
            String param1 = "";
            while (rs.next()) {
                String node_name = rs.getString("node_name");
                float EUtranCellFDD_pmRrcConnEstabAtt      = Float.parseFloat(rs.getString("EUtranCellFDD_pmRrcConnEstabAtt"));
                float EUtranCellTDD_pmRrcConnEstabAtt      = Float.parseFloat(rs.getString("EUtranCellTDD_pmRrcConnEstabAtt"));
                float EUtranCellFDD_pmRrcConnEstabAttReatt = Float.parseFloat(rs.getString("EUtranCellFDD_pmRrcConnEstabAttReatt"));
                float EUtranCellTDD_pmRrcConnEstabAttReatt = Float.parseFloat(rs.getString("EUtranCellTDD_pmRrcConnEstabAttReatt"));
                float EUtranCellFDD_pmRrcConnEstabSucc     = Float.parseFloat(rs.getString("EUtranCellFDD_pmRrcConnEstabSucc"));
                float EUtranCellTDD_pmRrcConnEstabSucc     = Float.parseFloat(rs.getString("EUtranCellTDD_pmRrcConnEstabSucc"));
                float EUtranCellFDD_pmS1SigConnEstabAtt    = Float.parseFloat(rs.getString("EUtranCellFDD_pmS1SigConnEstabAtt"));
                float EUtranCellTDD_pmS1SigConnEstabAtt = Float.parseFloat(rs.getString("EUtranCellTDD_pmS1SigConnEstabAtt"));
                float EUtranCellFDD_pmS1SigConnEstabSucc = Float.parseFloat(rs.getString("EUtranCellFDD_pmS1SigConnEstabSucc"));
                float EUtranCellTDD_pmS1SigConnEstabSucc = Float.parseFloat(rs.getString("EUtranCellTDD_pmS1SigConnEstabSucc"));
                float EUtranCellFDD_pmErabEstabAttInit = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabAttInit"));
                float EUtranCellTDD_pmErabEstabAttInit = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabAttInit"));
                float EUtranCellFDD_pmErabEstabAttAdded = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabAttAdded"));
                float EUtranCellTDD_pmErabEstabAttAdded = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabAttAdded"));
                float EUtranCellFDD_pmErabEstabSuccInit = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabSuccInit"));
                float EUtranCellTDD_pmErabEstabSuccInit = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabSuccInit"));
                float EUtranCellFDD_pmErabEstabSuccAdded = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabSuccAdded"));
                float EUtranCellTDD_pmErabEstabSuccAdded = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabSuccAdded"));
                float EUtranCellFDD_pmPagReceived = Float.parseFloat(rs.getString("EUtranCellFDD_pmPagReceived"));
                float EUtranCellTDD_pmPagReceived = Float.parseFloat(rs.getString("EUtranCellTDD_pmPagReceived"));
                float EUtranCellFDD_pmPagDiscarded = Float.parseFloat(rs.getString("EUtranCellFDD_pmPagDiscarded"));
                float EUtranCellTDD_pmPagDiscarded = Float.parseFloat(rs.getString("EUtranCellTDD_pmPagDiscarded"));
                float EUtranCellFDD_pmErabRelAbnormalEnbAct = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabRelAbnormalEnbAct"));
                float EUtranCellTDD_pmErabRelAbnormalEnbAct = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabRelAbnormalEnbAct"));
                float EUtranCellFDD_pmErabRelMmeAct = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabRelMmeAct"));
                float EUtranCellTDD_pmErabRelMmeAct = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabRelMmeAct"));
                float EUtranCellRelation_pmHoPrepAttLteInterF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoPrepAttLteInterF"));
                float EUtranCellRelation_pmHoPrepAttLteIntraF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoPrepAttLteIntraF"));
                float EUtranCellRelation_pmHoExeSuccLteInterF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoExeSuccLteInterF"));
                float EUtranCellRelation_pmHoExeSuccLteIntraF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoExeSuccLteIntraF"));
                float pmPrbUsedDlFirstTrans = Float.parseFloat(rs.getString("pmPrbUsedDlFirstTrans"));
                float pmPrbUsedDlBcch = Float.parseFloat(rs.getString("pmPrbUsedDlBcch"));
                float pmPrbUsedDlDtch = Float.parseFloat(rs.getString("pmPrbUsedDlDtch"));
                float pmPrbUsedDlPcch = Float.parseFloat(rs.getString("pmPrbUsedDlPcch"));
                float pmPrbUsedDlSrbFirstTrans = Float.parseFloat(rs.getString("pmPrbUsedDlSrbFirstTrans"));
                float pmPrbUsedDlReTrans = Float.parseFloat(rs.getString("pmPrbUsedDlReTrans"));
                float pmPrbUsedUlDtch = Float.parseFloat(rs.getString("pmPrbUsedUlDtch"));
                float pmPrbUsedUlSrb = Float.parseFloat(rs.getString("pmPrbUsedUlSrb"));
                float pmPdcpVolDlDrb = Float.parseFloat(rs.getString("pmPdcpVolDlDrb"));
                float pmPdcpVolUlDrb = Float.parseFloat(rs.getString("pmPdcpVolUlDrb"));
                float pmUeThpTimeDl = Float.parseFloat(rs.getString("pmUeThpTimeDl"));
                float pmPdcpVolDlDrbLastTTI = Float.parseFloat(rs.getString("pmPdcpVolDlDrbLastTTI"));
                float pmUeThpTimeUl = Float.parseFloat(rs.getString("pmUeThpTimeUl"));
                float pmUeThpVolUl = Float.parseFloat(rs.getString("pmUeThpVolUl"));
                float pmSessionTimeUe = Float.parseFloat(rs.getString("pmSessionTimeUe"));
                float pmSessionTimeDrb = Float.parseFloat(rs.getString("pmSessionTimeDrb"));
                float pmActiveUeDlSum = Float.parseFloat(rs.getString("pmActiveUeDlSum"));
                float pmActiveUeUlSum = Float.parseFloat(rs.getString("pmActiveUeUlSum"));
                float pmErabLevSamp = Float.parseFloat(rs.getString("pmErabLevSamp"));
                float pmCellDowntimeAuto = Float.parseFloat(rs.getString("pmCellDowntimeAuto"));

                float RRC_setup_succ_Rate;
                if(((EUtranCellFDD_pmRrcConnEstabAtt+EUtranCellTDD_pmRrcConnEstabAtt)-(EUtranCellFDD_pmRrcConnEstabAttReatt+EUtranCellTDD_pmRrcConnEstabAttReatt)) == 0)
                {
                    RRC_setup_succ_Rate = 0;
                }
                else
                {
                    RRC_setup_succ_Rate = (float)100*((EUtranCellFDD_pmRrcConnEstabSucc+EUtranCellTDD_pmRrcConnEstabSucc)/((EUtranCellFDD_pmRrcConnEstabAtt+EUtranCellTDD_pmRrcConnEstabAtt)-(EUtranCellFDD_pmRrcConnEstabAttReatt+EUtranCellTDD_pmRrcConnEstabAttReatt)));
                }

                float S1_Setup_Succ_Rate;
                if((EUtranCellFDD_pmS1SigConnEstabAtt+EUtranCellTDD_pmS1SigConnEstabAtt) == 0)
                {
                    S1_Setup_Succ_Rate = 0;
                }
                else
                {
                    S1_Setup_Succ_Rate = (float)100*((EUtranCellFDD_pmS1SigConnEstabSucc+EUtranCellTDD_pmS1SigConnEstabSucc)/(EUtranCellFDD_pmS1SigConnEstabAtt+EUtranCellTDD_pmS1SigConnEstabAtt));;
                }

                float ERAB_Setup_Succ_Rate;
                if(((EUtranCellFDD_pmErabEstabAttInit+EUtranCellTDD_pmErabEstabAttInit)+(EUtranCellFDD_pmErabEstabAttAdded+EUtranCellTDD_pmErabEstabAttAdded)) == 0)
                {
                    ERAB_Setup_Succ_Rate = 0;
                }
                else
                {
                    ERAB_Setup_Succ_Rate = (float)100*(((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit)+ (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded))/((EUtranCellFDD_pmErabEstabAttInit+EUtranCellTDD_pmErabEstabAttInit)+(EUtranCellFDD_pmErabEstabAttAdded+EUtranCellTDD_pmErabEstabAttAdded)));
                }

                float paging_Succ_Rate;
                if((EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived) == 0)
                {
                    paging_Succ_Rate = 0;
                }
                else
                {
                    paging_Succ_Rate = (float)100*((EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived)-(EUtranCellFDD_pmPagDiscarded+EUtranCellTDD_pmPagDiscarded))/(EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived);
                }

                float Erab_Drop_Rate;
                if(((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit) + (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded)) == 0)
                {
                    Erab_Drop_Rate = 0;
                }
                else
                {
                    Erab_Drop_Rate = (float)100*((EUtranCellFDD_pmErabRelAbnormalEnbAct+EUtranCellTDD_pmErabRelAbnormalEnbAct) + (EUtranCellFDD_pmErabRelMmeAct+EUtranCellTDD_pmErabRelMmeAct))/((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit) + (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded));
                }

                float Within_System_HO_Succ_Rate;
                if((EUtranCellRelation_pmHoPrepAttLteInterF+EUtranCellRelation_pmHoPrepAttLteIntraF) == 0)
                {
                    Within_System_HO_Succ_Rate = 100;
                }
                else
                {
                    Within_System_HO_Succ_Rate = (float)100*(EUtranCellRelation_pmHoExeSuccLteInterF+EUtranCellRelation_pmHoExeSuccLteIntraF)/(EUtranCellRelation_pmHoPrepAttLteInterF+EUtranCellRelation_pmHoPrepAttLteIntraF);
                }

                float Cell_Utilization_Rate;
                if(pmErabLevSamp == 0)
                {
                    Cell_Utilization_Rate = 0;
                }
                else
                {
                    Cell_Utilization_Rate = (float)100*((1-(pmCellDowntimeAuto)/((pmErabLevSamp)*5)));
                }

                float UL_PRB_Occupy_Rate;
                UL_PRB_Occupy_Rate = (float)(pmPrbUsedUlDtch + pmPrbUsedUlSrb)/(900*1000)/100;

                float DL_PRB_Occupy_Rate;
                if(pmPrbUsedDlFirstTrans == 0)
                {
                    DL_PRB_Occupy_Rate = 0;
                }
                else
                {
                    DL_PRB_Occupy_Rate = (float)(((pmPrbUsedDlBcch+pmPrbUsedDlDtch+pmPrbUsedDlPcch)+(pmPrbUsedDlSrbFirstTrans)*(1+pmPrbUsedDlReTrans/pmPrbUsedDlFirstTrans))/(900*1000)/100);
                }

                float Dl_ThroughputRate_Mbps;
                Dl_ThroughputRate_Mbps = (float)(pmPdcpVolDlDrb)/900/1024;

                float Ul_ThroughputRate_Mbps;
                Ul_ThroughputRate_Mbps = (float)(pmPdcpVolUlDrb)/900/1024;

                float DL_User_Speed_Mbps;
                if((pmUeThpTimeDl/1000) <= 0)
                {
                    DL_User_Speed_Mbps = 0;
                }
                else
                {
                    DL_User_Speed_Mbps = (float)((pmPdcpVolDlDrb-pmPdcpVolDlDrbLastTTI)/(pmUeThpTimeDl/1000))/1024;
                }

                float UL_User_Speed_Mbps;
                if((pmUeThpTimeUl/1000) <= 0)
                {
                    UL_User_Speed_Mbps = 0;
                }
                else
                {
                    UL_User_Speed_Mbps = (float)(pmUeThpVolUl/(pmUeThpTimeUl/1000))/1024;
                }

                float UE_SessionTime;
                UE_SessionTime = pmSessionTimeUe+0;

                float Drb_pmSessionTime;
                Drb_pmSessionTime = pmSessionTimeDrb+0;

                float DL_Active_User_Num;
                DL_Active_User_Num = (float)pmActiveUeDlSum/900000;

                float UL_Active_User_Num;
                UL_Active_User_Num = (float)pmActiveUeUlSum/900000;




//                List<Integer> statusList = new ArrayList<Integer>();
//                for (int m = 0; m < baseQuotaList.size(); m++){
//                    BaseQuota baseQuota = baseQuotaList.get(m);
//                    String baseName = baseQuota.getQuotaName();
//                    if (baseName.equals("Session_Setup_Success_Rate")){
////                          compare(baseQuota,baseStatus.getRrcSetupSuccRate());
//                    }else if (baseName.equals("RRC_setup_succ_Rate")){
//                        statusList.add(compare(baseQuota,RRC_setup_succ_Rate));
//                    }else if (baseName.equals("S1_Setup_Succ_Rate")){
//                        statusList.add(compare(baseQuota,S1_Setup_Succ_Rate));
//                    }else if (baseName.equals("ERAB_Setup_Succ_Rate")){
//                        statusList.add(compare(baseQuota,ERAB_Setup_Succ_Rate));
//                    }else if (baseName.equals("paging_Succ_Rate")){
//                        statusList.add(compare(baseQuota,paging_Succ_Rate));
//                    }else if (baseName.equals("Cell_Utilization_Rate")){
//                        statusList.add(compare(baseQuota,Cell_Utilization_Rate));
//                    }else if (baseName.equals("Within_System_HO_Succ_Rate")){
//                        statusList.add(compare(baseQuota,Within_System_HO_Succ_Rate));
//                    }else if (baseName.equals("Erab_Drop_Rate")){
//                        statusList.add(compare(baseQuota,Erab_Drop_Rate));
//                    }else if (baseName.equals("DL_PRB_Occupy_Rate")){
//                        statusList.add(compare(baseQuota,DL_PRB_Occupy_Rate));
//                    }else if (baseName.equals("UL_PRB_Occupy_Rate")){
//                        statusList.add(compare(baseQuota,UL_PRB_Occupy_Rate));
//                    }else if (baseName.equals("Dl_ThroughputRate_Mbps")) {
//                        statusList.add(compare(baseQuota, Dl_ThroughputRate_Mbps));
//                    }else if (baseName.equals("Ul_ThroughputRate_Mbps")){
//                        statusList.add(compare(baseQuota,Ul_ThroughputRate_Mbps));
//                    }else if (baseName.equals("DL_User_Speed_Mbps")){
//                        statusList.add(compare(baseQuota,DL_User_Speed_Mbps));
//                    }else if (baseName.equals("UL_User_Speed_Mbps")){
//                        statusList.add(compare(baseQuota,UL_User_Speed_Mbps));
//                    }else if (baseName.equals("UE_SessionTime")){
//                        statusList.add(compare(baseQuota,UE_SessionTime));
//                    }else if (baseName.equals("Drb_pmSessionTime")){
//                        statusList.add(compare(baseQuota,Drb_pmSessionTime));
//                    }else if (baseName.equals("DL_Active_User_Num")){
//                        statusList.add(compare(baseQuota,DL_Active_User_Num));
//                    }else if (baseName.equals("UL_Active_User_Num")){
//                        statusList.add(compare(baseQuota,UL_Active_User_Num));
//                    }
//                }
//                Integer status = Collections.max(statusList);

                Integer status = modStatus( baseQuotaList,
                        RRC_setup_succ_Rate, S1_Setup_Succ_Rate, ERAB_Setup_Succ_Rate,paging_Succ_Rate, Cell_Utilization_Rate,
                        Within_System_HO_Succ_Rate, Erab_Drop_Rate, DL_PRB_Occupy_Rate, UL_PRB_Occupy_Rate, Dl_ThroughputRate_Mbps,
                        Ul_ThroughputRate_Mbps, DL_User_Speed_Mbps, UL_User_Speed_Mbps, UE_SessionTime, Drb_pmSessionTime,
                        DL_Active_User_Num, UL_Active_User_Num);



                String temp = "'"+node_name+"', '"+
                        status+"', '"+
                        RRC_setup_succ_Rate+"', '"+
                        S1_Setup_Succ_Rate+"', '"+
                        ERAB_Setup_Succ_Rate+"', '"+
                        paging_Succ_Rate+"', '"+
                        Erab_Drop_Rate+"', '"+
                        Within_System_HO_Succ_Rate+"', '"+
                        Cell_Utilization_Rate+"', '"+
                        UL_PRB_Occupy_Rate+"', '"+
                        DL_PRB_Occupy_Rate+"', '"+
                        Dl_ThroughputRate_Mbps+"', '"+
                        Ul_ThroughputRate_Mbps+"', '"+
                        DL_User_Speed_Mbps+"', '"+
                        UL_User_Speed_Mbps+"', '"+
                        UE_SessionTime+"', '"+
                        Drb_pmSessionTime+"', '"+
                        DL_Active_User_Num+"', '"+
                        UL_Active_User_Num+"' ";

                String temp1 = "'"+in_time+"', '"+
                        node_name+"', '"+
                        status+"', '"+
                        RRC_setup_succ_Rate+"', '"+
                        S1_Setup_Succ_Rate+"', '"+
                        ERAB_Setup_Succ_Rate+"', '"+
                        paging_Succ_Rate+"', '"+
                        Erab_Drop_Rate+"', '"+
                        Within_System_HO_Succ_Rate+"', '"+
                        Cell_Utilization_Rate+"', '"+
                        UL_PRB_Occupy_Rate+"', '"+
                        DL_PRB_Occupy_Rate+"', '"+
                        Dl_ThroughputRate_Mbps+"', '"+
                        Ul_ThroughputRate_Mbps+"', '"+
                        DL_User_Speed_Mbps+"', '"+
                        UL_User_Speed_Mbps+"', '"+
                        UE_SessionTime+"', '"+
                        Drb_pmSessionTime+"', '"+
                        DL_Active_User_Num+"', '"+
                        UL_Active_User_Num+"' ";

                if(k < 500)
                {
                    if(param == "")
                    {
                        param = "("+temp+")";
                    }else{
                        param = param + ",("+temp+")";
                    }

                    if(param1 == "")
                    {
                        param1 = "("+temp1+")";
                    }else{
                        param1 = param1 + ",("+temp1+")";
                    }
                }
                else
                {
                    DBUtil dbUtil1 = new DBUtil();

                    param = param + ",("+temp+")";
                    String insert_sql = "INSERT INTO `base_node` (`node_name`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param;
                    dbUtil1.execOther(insert_sql, new Object[]{});

                    param1 = param1 + ",("+temp1+")";
                    String insert_sql_1 = "INSERT INTO `node` (`time`, `node_name`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param1;
                    dbUtil1.execOther(insert_sql_1, new Object[]{});

                    k = 0;
                    param = "";
                    param1 = "";

                    dbUtil1.closeConnection();
                }
                k++;
            }
            DBUtil dbUtil2 = new DBUtil();

            String insert_sql = "INSERT INTO `base_node` (`node_name`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param;
            dbUtil2.execOther(insert_sql, new Object[]{});

            String insert_sql_1 = "INSERT INTO `node` (`time`, `node_name`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param1;
            dbUtil2.execOther(insert_sql_1, new Object[]{});

            dbUtil2.closeConnection();

            dbUtil.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void calculateTac(String in_time) {

        List<BaseQuota> baseQuotaList = CounterUtil.getQuota("quota_threshold_tac");

        if(in_time.equals(null))
        {
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            in_time = dateFormater.format(date);
        }
        try {
            DBUtil dbUtil = new DBUtil();

            String sql = "SELECT b.tac, SUM(b.EUtranCellFDD_pmRrcConnEstabAtt) AS EUtranCellFDD_pmRrcConnEstabAtt, SUM(b.EUtranCellFDD_pmRrcConnEstabAttReatt) AS EUtranCellFDD_pmRrcConnEstabAttReatt, SUM(b.EUtranCellFDD_pmRrcConnEstabSucc) AS EUtranCellFDD_pmRrcConnEstabSucc, SUM(b.EUtranCellTDD_pmRrcConnEstabAtt) AS EUtranCellTDD_pmRrcConnEstabAtt, SUM(b.EUtranCellTDD_pmRrcConnEstabAttReatt) AS EUtranCellTDD_pmRrcConnEstabAttReatt, SUM(b.EUtranCellTDD_pmRrcConnEstabSucc) AS EUtranCellTDD_pmRrcConnEstabSucc, SUM(b.EUtranCellFDD_pmS1SigConnEstabAtt) AS EUtranCellFDD_pmS1SigConnEstabAtt, SUM(b.EUtranCellTDD_pmS1SigConnEstabAtt) AS EUtranCellTDD_pmS1SigConnEstabAtt, SUM(b.EUtranCellFDD_pmS1SigConnEstabSucc) AS EUtranCellFDD_pmS1SigConnEstabSucc, SUM(b.EUtranCellTDD_pmS1SigConnEstabSucc) AS EUtranCellTDD_pmS1SigConnEstabSucc, SUM(b.EUtranCellFDD_pmErabEstabAttInit) AS EUtranCellFDD_pmErabEstabAttInit, SUM(b.EUtranCellTDD_pmErabEstabAttInit) AS EUtranCellTDD_pmErabEstabAttInit, SUM(b.EUtranCellFDD_pmErabEstabAttAdded) AS EUtranCellFDD_pmErabEstabAttAdded, SUM(b.EUtranCellTDD_pmErabEstabAttAdded) AS EUtranCellTDD_pmErabEstabAttAdded, SUM(b.EUtranCellFDD_pmErabEstabSuccInit) AS EUtranCellFDD_pmErabEstabSuccInit, SUM(b.EUtranCellTDD_pmErabEstabSuccInit) AS EUtranCellTDD_pmErabEstabSuccInit, SUM(b.EUtranCellFDD_pmErabEstabSuccAdded) AS EUtranCellFDD_pmErabEstabSuccAdded, SUM(b.EUtranCellTDD_pmErabEstabSuccAdded) AS EUtranCellTDD_pmErabEstabSuccAdded, SUM(b.EUtranCellFDD_pmPagReceived) AS EUtranCellFDD_pmPagReceived, SUM(b.EUtranCellTDD_pmPagReceived) AS EUtranCellTDD_pmPagReceived, SUM(b.EUtranCellFDD_pmPagDiscarded) AS EUtranCellFDD_pmPagDiscarded, SUM(b.EUtranCellTDD_pmPagDiscarded) AS EUtranCellTDD_pmPagDiscarded, SUM(b.EUtranCellFDD_pmErabRelAbnormalEnbAct) AS EUtranCellFDD_pmErabRelAbnormalEnbAct, SUM(b.EUtranCellTDD_pmErabRelAbnormalEnbAct) AS EUtranCellTDD_pmErabRelAbnormalEnbAct, SUM(b.EUtranCellFDD_pmErabRelMmeAct) AS EUtranCellFDD_pmErabRelMmeAct, SUM(b.EUtranCellTDD_pmErabRelMmeAct) AS EUtranCellTDD_pmErabRelMmeAct, SUM(b.EUtranCellRelation_pmHoPrepAttLteInterF) AS EUtranCellRelation_pmHoPrepAttLteInterF, SUM(b.EUtranCellRelation_pmHoPrepAttLteIntraF) AS EUtranCellRelation_pmHoPrepAttLteIntraF, SUM(b.EUtranCellRelation_pmHoExeSuccLteInterF) AS EUtranCellRelation_pmHoExeSuccLteInterF, SUM(b.EUtranCellRelation_pmHoExeSuccLteIntraF) AS EUtranCellRelation_pmHoExeSuccLteIntraF, SUM(b.pmPrbUsedDlFirstTrans) AS pmPrbUsedDlFirstTrans, SUM(b.pmPrbUsedDlBcch) AS pmPrbUsedDlBcch, SUM(b.pmPrbUsedDlDtch) AS pmPrbUsedDlDtch, SUM(b.pmPrbUsedDlPcch) AS pmPrbUsedDlPcch, SUM(b.pmPrbUsedDlSrbFirstTrans) AS pmPrbUsedDlSrbFirstTrans, SUM(b.pmPrbUsedDlReTrans) AS pmPrbUsedDlReTrans, SUM(b.pmPrbUsedUlDtch) AS pmPrbUsedUlDtch, SUM(b.pmPrbUsedUlSrb) AS pmPrbUsedUlSrb, SUM(b.pmPdcpVolDlDrb) AS pmPdcpVolDlDrb, SUM(b.pmPdcpVolUlDrb) AS pmPdcpVolUlDrb, SUM(b.pmUeThpTimeDl) AS pmUeThpTimeDl, SUM(b.pmPdcpVolDlDrbLastTTI) AS pmPdcpVolDlDrbLastTTI, SUM(b.pmUeThpTimeUl) AS pmUeThpTimeUl, SUM(b.pmUeThpVolUl) AS pmUeThpVolUl, SUM(b.pmSessionTimeUe) AS pmSessionTimeUe, SUM(b.pmSessionTimeDrb) AS pmSessionTimeDrb, SUM(b.pmActiveUeDlSum) AS pmActiveUeDlSum, SUM(b.pmActiveUeUlSum) AS pmActiveUeUlSum, SUM(b.pmErabLevSamp) AS pmErabLevSamp, SUM(b.pmCellDowntimeAuto) AS pmCellDowntimeAuto FROM "+
                    "( "+
                    "SELECT a.tac,b.* FROM base_station a "+
                    "LEFT JOIN "+
                    "(SELECT left(cell_name, 9) AS enb_name, SUM(b.EUtranCellFDD_pmRrcConnEstabAtt) AS EUtranCellFDD_pmRrcConnEstabAtt, SUM(b.EUtranCellFDD_pmRrcConnEstabAttReatt) AS EUtranCellFDD_pmRrcConnEstabAttReatt, SUM(b.EUtranCellFDD_pmRrcConnEstabSucc) AS EUtranCellFDD_pmRrcConnEstabSucc, SUM(b.EUtranCellTDD_pmRrcConnEstabAtt) AS EUtranCellTDD_pmRrcConnEstabAtt, SUM(b.EUtranCellTDD_pmRrcConnEstabAttReatt) AS EUtranCellTDD_pmRrcConnEstabAttReatt, SUM(b.EUtranCellTDD_pmRrcConnEstabSucc) AS EUtranCellTDD_pmRrcConnEstabSucc, SUM(b.EUtranCellFDD_pmS1SigConnEstabAtt) AS EUtranCellFDD_pmS1SigConnEstabAtt, SUM(b.EUtranCellTDD_pmS1SigConnEstabAtt) AS EUtranCellTDD_pmS1SigConnEstabAtt, SUM(b.EUtranCellFDD_pmS1SigConnEstabSucc) AS EUtranCellFDD_pmS1SigConnEstabSucc, SUM(b.EUtranCellTDD_pmS1SigConnEstabSucc) AS EUtranCellTDD_pmS1SigConnEstabSucc, SUM(b.EUtranCellFDD_pmErabEstabAttInit) AS EUtranCellFDD_pmErabEstabAttInit, SUM(b.EUtranCellTDD_pmErabEstabAttInit) AS EUtranCellTDD_pmErabEstabAttInit, SUM(b.EUtranCellFDD_pmErabEstabAttAdded) AS EUtranCellFDD_pmErabEstabAttAdded, SUM(b.EUtranCellTDD_pmErabEstabAttAdded) AS EUtranCellTDD_pmErabEstabAttAdded, SUM(b.EUtranCellFDD_pmErabEstabSuccInit) AS EUtranCellFDD_pmErabEstabSuccInit, SUM(b.EUtranCellTDD_pmErabEstabSuccInit) AS EUtranCellTDD_pmErabEstabSuccInit, SUM(b.EUtranCellFDD_pmErabEstabSuccAdded) AS EUtranCellFDD_pmErabEstabSuccAdded, SUM(b.EUtranCellTDD_pmErabEstabSuccAdded) AS EUtranCellTDD_pmErabEstabSuccAdded, SUM(b.EUtranCellFDD_pmPagReceived) AS EUtranCellFDD_pmPagReceived, SUM(b.EUtranCellTDD_pmPagReceived) AS EUtranCellTDD_pmPagReceived, SUM(b.EUtranCellFDD_pmPagDiscarded) AS EUtranCellFDD_pmPagDiscarded, SUM(b.EUtranCellTDD_pmPagDiscarded) AS EUtranCellTDD_pmPagDiscarded, SUM(b.EUtranCellFDD_pmErabRelAbnormalEnbAct) AS EUtranCellFDD_pmErabRelAbnormalEnbAct, SUM(b.EUtranCellTDD_pmErabRelAbnormalEnbAct) AS EUtranCellTDD_pmErabRelAbnormalEnbAct, SUM(b.EUtranCellFDD_pmErabRelMmeAct) AS EUtranCellFDD_pmErabRelMmeAct, SUM(b.EUtranCellTDD_pmErabRelMmeAct) AS EUtranCellTDD_pmErabRelMmeAct, SUM(b.EUtranCellRelation_pmHoPrepAttLteInterF) AS EUtranCellRelation_pmHoPrepAttLteInterF, SUM(b.EUtranCellRelation_pmHoPrepAttLteIntraF) AS EUtranCellRelation_pmHoPrepAttLteIntraF, SUM(b.EUtranCellRelation_pmHoExeSuccLteInterF) AS EUtranCellRelation_pmHoExeSuccLteInterF, SUM(b.EUtranCellRelation_pmHoExeSuccLteIntraF) AS EUtranCellRelation_pmHoExeSuccLteIntraF, SUM(b.pmPrbUsedDlFirstTrans) AS pmPrbUsedDlFirstTrans, SUM(b.pmPrbUsedDlBcch) AS pmPrbUsedDlBcch, SUM(b.pmPrbUsedDlDtch) AS pmPrbUsedDlDtch, SUM(b.pmPrbUsedDlPcch) AS pmPrbUsedDlPcch, SUM(b.pmPrbUsedDlSrbFirstTrans) AS pmPrbUsedDlSrbFirstTrans, SUM(b.pmPrbUsedDlReTrans) AS pmPrbUsedDlReTrans, SUM(b.pmPrbUsedUlDtch) AS pmPrbUsedUlDtch, SUM(b.pmPrbUsedUlSrb) AS pmPrbUsedUlSrb, SUM(b.pmPdcpVolDlDrb) AS pmPdcpVolDlDrb, SUM(b.pmPdcpVolUlDrb) AS pmPdcpVolUlDrb, SUM(b.pmUeThpTimeDl) AS pmUeThpTimeDl, SUM(b.pmPdcpVolDlDrbLastTTI) AS pmPdcpVolDlDrbLastTTI, SUM(b.pmUeThpTimeUl) AS pmUeThpTimeUl, SUM(b.pmUeThpVolUl) AS pmUeThpVolUl, SUM(b.pmSessionTimeUe) AS pmSessionTimeUe, SUM(b.pmSessionTimeDrb) AS pmSessionTimeDrb, SUM(b.pmActiveUeDlSum) AS pmActiveUeDlSum, SUM(b.pmActiveUeUlSum) AS pmActiveUeUlSum, SUM(b.pmErabLevSamp) AS pmErabLevSamp, SUM(b.pmCellDowntimeAuto) AS pmCellDowntimeAuto FROM temp b GROUP BY left(b.cell_name, 9)) b ON a.enb_name = b.enb_name "+
                    "WHERE b.enb_name IS NOT NULL "+
                    ") b GROUP BY b.tac";

            ResultSet rs  = dbUtil.execQuery(sql,  new Object[]{});
            int k = 0;
            String param = "";
            String param1 = "";
            while (rs.next()) {
                String tac = rs.getString("tac");
                float EUtranCellFDD_pmRrcConnEstabAtt      = Float.parseFloat(rs.getString("EUtranCellFDD_pmRrcConnEstabAtt"));
                float EUtranCellTDD_pmRrcConnEstabAtt      = Float.parseFloat(rs.getString("EUtranCellTDD_pmRrcConnEstabAtt"));
                float EUtranCellFDD_pmRrcConnEstabAttReatt = Float.parseFloat(rs.getString("EUtranCellFDD_pmRrcConnEstabAttReatt"));
                float EUtranCellTDD_pmRrcConnEstabAttReatt = Float.parseFloat(rs.getString("EUtranCellTDD_pmRrcConnEstabAttReatt"));
                float EUtranCellFDD_pmRrcConnEstabSucc     = Float.parseFloat(rs.getString("EUtranCellFDD_pmRrcConnEstabSucc"));
                float EUtranCellTDD_pmRrcConnEstabSucc     = Float.parseFloat(rs.getString("EUtranCellTDD_pmRrcConnEstabSucc"));
                float EUtranCellFDD_pmS1SigConnEstabAtt    = Float.parseFloat(rs.getString("EUtranCellFDD_pmS1SigConnEstabAtt"));
                float EUtranCellTDD_pmS1SigConnEstabAtt = Float.parseFloat(rs.getString("EUtranCellTDD_pmS1SigConnEstabAtt"));
                float EUtranCellFDD_pmS1SigConnEstabSucc = Float.parseFloat(rs.getString("EUtranCellFDD_pmS1SigConnEstabSucc"));
                float EUtranCellTDD_pmS1SigConnEstabSucc = Float.parseFloat(rs.getString("EUtranCellTDD_pmS1SigConnEstabSucc"));
                float EUtranCellFDD_pmErabEstabAttInit = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabAttInit"));
                float EUtranCellTDD_pmErabEstabAttInit = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabAttInit"));
                float EUtranCellFDD_pmErabEstabAttAdded = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabAttAdded"));
                float EUtranCellTDD_pmErabEstabAttAdded = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabAttAdded"));
                float EUtranCellFDD_pmErabEstabSuccInit = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabSuccInit"));
                float EUtranCellTDD_pmErabEstabSuccInit = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabSuccInit"));
                float EUtranCellFDD_pmErabEstabSuccAdded = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabEstabSuccAdded"));
                float EUtranCellTDD_pmErabEstabSuccAdded = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabEstabSuccAdded"));
                float EUtranCellFDD_pmPagReceived = Float.parseFloat(rs.getString("EUtranCellFDD_pmPagReceived"));
                float EUtranCellTDD_pmPagReceived = Float.parseFloat(rs.getString("EUtranCellTDD_pmPagReceived"));
                float EUtranCellFDD_pmPagDiscarded = Float.parseFloat(rs.getString("EUtranCellFDD_pmPagDiscarded"));
                float EUtranCellTDD_pmPagDiscarded = Float.parseFloat(rs.getString("EUtranCellTDD_pmPagDiscarded"));
                float EUtranCellFDD_pmErabRelAbnormalEnbAct = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabRelAbnormalEnbAct"));
                float EUtranCellTDD_pmErabRelAbnormalEnbAct = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabRelAbnormalEnbAct"));
                float EUtranCellFDD_pmErabRelMmeAct = Float.parseFloat(rs.getString("EUtranCellFDD_pmErabRelMmeAct"));
                float EUtranCellTDD_pmErabRelMmeAct = Float.parseFloat(rs.getString("EUtranCellTDD_pmErabRelMmeAct"));
                float EUtranCellRelation_pmHoPrepAttLteInterF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoPrepAttLteInterF"));
                float EUtranCellRelation_pmHoPrepAttLteIntraF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoPrepAttLteIntraF"));
                float EUtranCellRelation_pmHoExeSuccLteInterF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoExeSuccLteInterF"));
                float EUtranCellRelation_pmHoExeSuccLteIntraF = Float.parseFloat(rs.getString("EUtranCellRelation_pmHoExeSuccLteIntraF"));
                float pmPrbUsedDlFirstTrans = Float.parseFloat(rs.getString("pmPrbUsedDlFirstTrans"));
                float pmPrbUsedDlBcch = Float.parseFloat(rs.getString("pmPrbUsedDlBcch"));
                float pmPrbUsedDlDtch = Float.parseFloat(rs.getString("pmPrbUsedDlDtch"));
                float pmPrbUsedDlPcch = Float.parseFloat(rs.getString("pmPrbUsedDlPcch"));
                float pmPrbUsedDlSrbFirstTrans = Float.parseFloat(rs.getString("pmPrbUsedDlSrbFirstTrans"));
                float pmPrbUsedDlReTrans = Float.parseFloat(rs.getString("pmPrbUsedDlReTrans"));
                float pmPrbUsedUlDtch = Float.parseFloat(rs.getString("pmPrbUsedUlDtch"));
                float pmPrbUsedUlSrb = Float.parseFloat(rs.getString("pmPrbUsedUlSrb"));
                float pmPdcpVolDlDrb = Float.parseFloat(rs.getString("pmPdcpVolDlDrb"));
                float pmPdcpVolUlDrb = Float.parseFloat(rs.getString("pmPdcpVolUlDrb"));
                float pmUeThpTimeDl = Float.parseFloat(rs.getString("pmUeThpTimeDl"));
                float pmPdcpVolDlDrbLastTTI = Float.parseFloat(rs.getString("pmPdcpVolDlDrbLastTTI"));
                float pmUeThpTimeUl = Float.parseFloat(rs.getString("pmUeThpTimeUl"));
                float pmUeThpVolUl = Float.parseFloat(rs.getString("pmUeThpVolUl"));
                float pmSessionTimeUe = Float.parseFloat(rs.getString("pmSessionTimeUe"));
                float pmSessionTimeDrb = Float.parseFloat(rs.getString("pmSessionTimeDrb"));
                float pmActiveUeDlSum = Float.parseFloat(rs.getString("pmActiveUeDlSum"));
                float pmActiveUeUlSum = Float.parseFloat(rs.getString("pmActiveUeUlSum"));
                float pmErabLevSamp = Float.parseFloat(rs.getString("pmErabLevSamp"));
                float pmCellDowntimeAuto = Float.parseFloat(rs.getString("pmCellDowntimeAuto"));

                float RRC_setup_succ_Rate;
                if(((EUtranCellFDD_pmRrcConnEstabAtt+EUtranCellTDD_pmRrcConnEstabAtt)-(EUtranCellFDD_pmRrcConnEstabAttReatt+EUtranCellTDD_pmRrcConnEstabAttReatt)) == 0)
                {
                    RRC_setup_succ_Rate = 0;
                }
                else
                {
                    RRC_setup_succ_Rate = (float)100*((EUtranCellFDD_pmRrcConnEstabSucc+EUtranCellTDD_pmRrcConnEstabSucc)/((EUtranCellFDD_pmRrcConnEstabAtt+EUtranCellTDD_pmRrcConnEstabAtt)-(EUtranCellFDD_pmRrcConnEstabAttReatt+EUtranCellTDD_pmRrcConnEstabAttReatt)));
                }

                float S1_Setup_Succ_Rate;
                if((EUtranCellFDD_pmS1SigConnEstabAtt+EUtranCellTDD_pmS1SigConnEstabAtt) == 0)
                {
                    S1_Setup_Succ_Rate = 0;
                }
                else
                {
                    S1_Setup_Succ_Rate = (float)100*((EUtranCellFDD_pmS1SigConnEstabSucc+EUtranCellTDD_pmS1SigConnEstabSucc)/(EUtranCellFDD_pmS1SigConnEstabAtt+EUtranCellTDD_pmS1SigConnEstabAtt));;
                }

                float ERAB_Setup_Succ_Rate;
                if(((EUtranCellFDD_pmErabEstabAttInit+EUtranCellTDD_pmErabEstabAttInit)+(EUtranCellFDD_pmErabEstabAttAdded+EUtranCellTDD_pmErabEstabAttAdded)) == 0)
                {
                    ERAB_Setup_Succ_Rate = 0;
                }
                else
                {
                    ERAB_Setup_Succ_Rate = (float)100*(((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit)+ (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded))/((EUtranCellFDD_pmErabEstabAttInit+EUtranCellTDD_pmErabEstabAttInit)+(EUtranCellFDD_pmErabEstabAttAdded+EUtranCellTDD_pmErabEstabAttAdded)));
                }

                float paging_Succ_Rate;
                if((EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived) == 0)
                {
                    paging_Succ_Rate = 0;
                }
                else
                {
                    paging_Succ_Rate = (float)100*((EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived)-(EUtranCellFDD_pmPagDiscarded+EUtranCellTDD_pmPagDiscarded))/(EUtranCellFDD_pmPagReceived+EUtranCellTDD_pmPagReceived);
                }

                float Erab_Drop_Rate;
                if(((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit) + (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded)) == 0)
                {
                    Erab_Drop_Rate = 0;
                }
                else
                {
                    Erab_Drop_Rate = (float)100*((EUtranCellFDD_pmErabRelAbnormalEnbAct+EUtranCellTDD_pmErabRelAbnormalEnbAct) + (EUtranCellFDD_pmErabRelMmeAct+EUtranCellTDD_pmErabRelMmeAct))/((EUtranCellFDD_pmErabEstabSuccInit+EUtranCellTDD_pmErabEstabSuccInit) + (EUtranCellFDD_pmErabEstabSuccAdded+EUtranCellTDD_pmErabEstabSuccAdded));
                }

                float Within_System_HO_Succ_Rate;
                if((EUtranCellRelation_pmHoPrepAttLteInterF+EUtranCellRelation_pmHoPrepAttLteIntraF) == 0)
                {
                    Within_System_HO_Succ_Rate = 100;
                }
                else
                {
                    Within_System_HO_Succ_Rate = (float)100*(EUtranCellRelation_pmHoExeSuccLteInterF+EUtranCellRelation_pmHoExeSuccLteIntraF)/(EUtranCellRelation_pmHoPrepAttLteInterF+EUtranCellRelation_pmHoPrepAttLteIntraF);
                }

                float Cell_Utilization_Rate;
                if(pmErabLevSamp == 0)
                {
                    Cell_Utilization_Rate = 0;
                }
                else
                {
                    Cell_Utilization_Rate = (float)100*((1-(pmCellDowntimeAuto)/((pmErabLevSamp)*5)));
                }

                float UL_PRB_Occupy_Rate;
                UL_PRB_Occupy_Rate = (float)(pmPrbUsedUlDtch + pmPrbUsedUlSrb)/(900*1000)/100;

                float DL_PRB_Occupy_Rate;
                if(pmPrbUsedDlFirstTrans == 0)
                {
                    DL_PRB_Occupy_Rate = 0;
                }
                else
                {
                    DL_PRB_Occupy_Rate = (float)(((pmPrbUsedDlBcch+pmPrbUsedDlDtch+pmPrbUsedDlPcch)+(pmPrbUsedDlSrbFirstTrans)*(1+pmPrbUsedDlReTrans/pmPrbUsedDlFirstTrans))/(900*1000)/100);
                }

                float Dl_ThroughputRate_Mbps;
                Dl_ThroughputRate_Mbps = (float)(pmPdcpVolDlDrb)/900/1024;

                float Ul_ThroughputRate_Mbps;
                Ul_ThroughputRate_Mbps = (float)(pmPdcpVolUlDrb)/900/1024;

                float DL_User_Speed_Mbps;
                if((pmUeThpTimeDl/1000) <= 0)
                {
                    DL_User_Speed_Mbps = 0;
                }
                else
                {
                    DL_User_Speed_Mbps = (float)((pmPdcpVolDlDrb-pmPdcpVolDlDrbLastTTI)/(pmUeThpTimeDl/1000))/1024;
                }

                float UL_User_Speed_Mbps;
                if((pmUeThpTimeUl/1000) <= 0)
                {
                    UL_User_Speed_Mbps = 0;
                }
                else
                {
                    UL_User_Speed_Mbps = (float)(pmUeThpVolUl/(pmUeThpTimeUl/1000))/1024;
                }

                float UE_SessionTime;
                UE_SessionTime = pmSessionTimeUe+0;

                float Drb_pmSessionTime;
                Drb_pmSessionTime = pmSessionTimeDrb+0;

                float DL_Active_User_Num;
                DL_Active_User_Num = (float)pmActiveUeDlSum/900000;

                float UL_Active_User_Num;
                UL_Active_User_Num = (float)pmActiveUeUlSum/900000;



//                List<Integer> statusList = new ArrayList<Integer>();
//                for (int m = 0; m < baseQuotaList.size(); m++){
//                    BaseQuota baseQuota = baseQuotaList.get(m);
//                    String baseName = baseQuota.getQuotaName();
//                    if (baseName.equals("Session_Setup_Success_Rate")){
////                          compare(baseQuota,baseStatus.getRrcSetupSuccRate());
//                    }else if (baseName.equals("RRC_setup_succ_Rate")){
//                        statusList.add(compare(baseQuota,RRC_setup_succ_Rate));
//                    }else if (baseName.equals("S1_Setup_Succ_Rate")){
//                        statusList.add(compare(baseQuota,S1_Setup_Succ_Rate));
//                    }else if (baseName.equals("ERAB_Setup_Succ_Rate")){
//                        statusList.add(compare(baseQuota,ERAB_Setup_Succ_Rate));
//                    }else if (baseName.equals("paging_Succ_Rate")){
//                        statusList.add(compare(baseQuota,paging_Succ_Rate));
//                    }else if (baseName.equals("Cell_Utilization_Rate")){
//                        statusList.add(compare(baseQuota,Cell_Utilization_Rate));
//                    }else if (baseName.equals("Within_System_HO_Succ_Rate")){
//                        statusList.add(compare(baseQuota,Within_System_HO_Succ_Rate));
//                    }else if (baseName.equals("Erab_Drop_Rate")){
//                        statusList.add(compare(baseQuota,Erab_Drop_Rate));
//                    }else if (baseName.equals("DL_PRB_Occupy_Rate")){
//                        statusList.add(compare(baseQuota,DL_PRB_Occupy_Rate));
//                    }else if (baseName.equals("UL_PRB_Occupy_Rate")){
//                        statusList.add(compare(baseQuota,UL_PRB_Occupy_Rate));
//                    }else if (baseName.equals("Dl_ThroughputRate_Mbps")) {
//                        statusList.add(compare(baseQuota, Dl_ThroughputRate_Mbps));
//                    }else if (baseName.equals("Ul_ThroughputRate_Mbps")){
//                        statusList.add(compare(baseQuota,Ul_ThroughputRate_Mbps));
//                    }else if (baseName.equals("DL_User_Speed_Mbps")){
//                        statusList.add(compare(baseQuota,DL_User_Speed_Mbps));
//                    }else if (baseName.equals("UL_User_Speed_Mbps")){
//                        statusList.add(compare(baseQuota,UL_User_Speed_Mbps));
//                    }else if (baseName.equals("UE_SessionTime")){
//                        statusList.add(compare(baseQuota,UE_SessionTime));
//                    }else if (baseName.equals("Drb_pmSessionTime")){
//                        statusList.add(compare(baseQuota,Drb_pmSessionTime));
//                    }else if (baseName.equals("DL_Active_User_Num")){
//                        statusList.add(compare(baseQuota,DL_Active_User_Num));
//                    }else if (baseName.equals("UL_Active_User_Num")){
//                        statusList.add(compare(baseQuota,UL_Active_User_Num));
//                    }
//                }
//                Integer status = Collections.max(statusList);

                Integer status = modStatus( baseQuotaList,
                        RRC_setup_succ_Rate, S1_Setup_Succ_Rate, ERAB_Setup_Succ_Rate,paging_Succ_Rate, Cell_Utilization_Rate,
                        Within_System_HO_Succ_Rate, Erab_Drop_Rate, DL_PRB_Occupy_Rate, UL_PRB_Occupy_Rate, Dl_ThroughputRate_Mbps,
                        Ul_ThroughputRate_Mbps, DL_User_Speed_Mbps, UL_User_Speed_Mbps, UE_SessionTime, Drb_pmSessionTime,
                        DL_Active_User_Num, UL_Active_User_Num);


                String temp = "'"+tac+"', '"+
                        status+"', '"+
                        RRC_setup_succ_Rate+"', '"+
                        S1_Setup_Succ_Rate+"', '"+
                        ERAB_Setup_Succ_Rate+"', '"+
                        paging_Succ_Rate+"', '"+
                        Erab_Drop_Rate+"', '"+
                        Within_System_HO_Succ_Rate+"', '"+
                        Cell_Utilization_Rate+"', '"+
                        UL_PRB_Occupy_Rate+"', '"+
                        DL_PRB_Occupy_Rate+"', '"+
                        Dl_ThroughputRate_Mbps+"', '"+
                        Ul_ThroughputRate_Mbps+"', '"+
                        DL_User_Speed_Mbps+"', '"+
                        UL_User_Speed_Mbps+"', '"+
                        UE_SessionTime+"', '"+
                        Drb_pmSessionTime+"', '"+
                        DL_Active_User_Num+"', '"+
                        UL_Active_User_Num+"' ";

                String temp1 = "'"+in_time+"', '"+
                        tac+"', '"+
                        status+"', '"+
                        RRC_setup_succ_Rate+"', '"+
                        S1_Setup_Succ_Rate+"', '"+
                        ERAB_Setup_Succ_Rate+"', '"+
                        paging_Succ_Rate+"', '"+
                        Erab_Drop_Rate+"', '"+
                        Within_System_HO_Succ_Rate+"', '"+
                        Cell_Utilization_Rate+"', '"+
                        UL_PRB_Occupy_Rate+"', '"+
                        DL_PRB_Occupy_Rate+"', '"+
                        Dl_ThroughputRate_Mbps+"', '"+
                        Ul_ThroughputRate_Mbps+"', '"+
                        DL_User_Speed_Mbps+"', '"+
                        UL_User_Speed_Mbps+"', '"+
                        UE_SessionTime+"', '"+
                        Drb_pmSessionTime+"', '"+
                        DL_Active_User_Num+"', '"+
                        UL_Active_User_Num+"' ";

                if(k < 500)
                {
                    if(param == "")
                    {
                        param = "("+temp+")";
                    }else{
                        param = param + ",("+temp+")";
                    }

                    if(param1 == "")
                    {
                        param1 = "("+temp1+")";
                    }else{
                        param1 = param1 + ",("+temp1+")";
                    }
                }
                else
                {
                    DBUtil dbUtil1 = new DBUtil();

                    param = param + ",("+temp+")";
                    String insert_sql = "INSERT INTO `base_tac` (`tac`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param;
                    dbUtil1.execOther(insert_sql, new Object[]{});

                    param1 = param1 + ",("+temp1+")";
                    String insert_sql_1 = "INSERT INTO `tac` (`time`, `tac`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param1;
                    dbUtil1.execOther(insert_sql_1, new Object[]{});

                    k = 0;
                    param = "";
                    param1 = "";

                    dbUtil1.closeConnection();
                }
                k++;
            }
            DBUtil dbUtil2 = new DBUtil();

            String insert_sql = "INSERT INTO `base_tac` (`tac`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param;
            dbUtil2.execOther(insert_sql, new Object[]{});

            String insert_sql_1 = "INSERT INTO `tac` (`time`, `tac`, `status`, `RRC_setup_succ_Rate`,`S1_Setup_Succ_Rate`,`ERAB_Setup_Succ_Rate`,`paging_Succ_Rate`,`Erab_Drop_Rate`,`Within_System_HO_Succ_Rate`,`Cell_Utilization_Rate`,`UL_PRB_Occupy_Rate`,`DL_PRB_Occupy_Rate`,`Dl_ThroughputRate_Mbps`,`Ul_ThroughputRate_Mbps`,`DL_User_Speed_Mbps`,`UL_User_Speed_Mbps`,`UE_SessionTime`,`Drb_pmSessionTime`,`DL_Active_User_Num`,`UL_Active_User_Num`) VALUES "+param1;
            dbUtil2.execOther(insert_sql_1, new Object[]{});

            dbUtil2.closeConnection();

            dbUtil.closeConnection();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<BaseQuota> getQuota(String quotaName)
    {
        DBUtil dbUtil = new DBUtil();
        try {

            ResultSet rs= dbUtil.execQuery("SELECT * FROM "+quotaName, new Object[]{});
            List<BaseQuota> baseQuotaList = new ArrayList<BaseQuota>();
            while (rs.next()){
                if (quotaName.equals("quota_threshold_cell")){
                    QuotaThresholdCell quotaThresholdCell = new QuotaThresholdCell();
                    quotaThresholdCell.setQuotaName(rs.getString("quota_name"));
                    quotaThresholdCell.setQuotaType(rs.getInt("quota_type"));
                    quotaThresholdCell.setQuotaUnit(rs.getInt("quota_unit"));
                    quotaThresholdCell.setThreshold1(rs.getString("threshold_1"));
                    quotaThresholdCell.setThreshold2(rs.getString("threshold_2"));
                    quotaThresholdCell.setThreshold3(rs.getString("threshold_3"));
                    baseQuotaList.add(quotaThresholdCell);
                }else if (quotaName.equals("quota_threshold_node")){
                    QuotaThresholdNode quotaThresholdNode = new QuotaThresholdNode();
                    quotaThresholdNode.setQuotaName(rs.getString("quota_name"));
                    quotaThresholdNode.setQuotaType(rs.getInt("quota_type"));
                    quotaThresholdNode.setQuotaUnit(rs.getInt("quota_unit"));
                    quotaThresholdNode.setThreshold1(rs.getString("threshold_1"));
                    quotaThresholdNode.setThreshold2(rs.getString("threshold_2"));
                    quotaThresholdNode.setThreshold3(rs.getString("threshold_3"));
                    baseQuotaList.add(quotaThresholdNode);
                }else if (quotaName.equals("quota_threshold_tac")){
                    QuotaThresholdTac quotaThresholdTac = new QuotaThresholdTac();
                    quotaThresholdTac.setQuotaName(rs.getString("quota_name"));
                    quotaThresholdTac.setQuotaType(rs.getInt("quota_type"));
                    quotaThresholdTac.setQuotaUnit(rs.getInt("quota_unit"));
                    quotaThresholdTac.setThreshold1(rs.getString("threshold_1"));
                    quotaThresholdTac.setThreshold2(rs.getString("threshold_2"));
                    quotaThresholdTac.setThreshold3(rs.getString("threshold_3"));
                    baseQuotaList.add(quotaThresholdTac);
                }
            }
            return baseQuotaList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            dbUtil.closeConnection();
        }
    }

    public static Integer compare(BaseQuota baseQuota,float baseName){
        Integer status = 0;
        if (baseQuota.getQuotaUnit() == 0){
            if (baseQuota.getQuotaType() == 0){
                if(baseName <= Float.valueOf(baseQuota.getThreshold3())) {
                    status = 3;
                }else if(baseName <= Float.valueOf(baseQuota.getThreshold2())) {
                    status = 2;
                }else if(baseName <= Float.valueOf(baseQuota.getThreshold1())) {
                    status = 1;
                }else{
                    status = 0;
                }
            }else {
                if(baseName >= Float.valueOf(baseQuota.getThreshold3())) {
                    status = 3;
                }else if(baseName >= Float.valueOf(baseQuota.getThreshold2())) {
                    status = 2;
                }else if(baseName >= Float.valueOf(baseQuota.getThreshold1())) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
        }else {
            if (baseQuota.getQuotaType() == 0){
                if((int)baseName <= (int)((float)Float.valueOf(baseQuota.getThreshold3()))) {
                    status = 3;
                }else if((int)baseName <= (int)((float)Float.valueOf(baseQuota.getThreshold2()))) {
                    status = 2;
                }else if((int)baseName <= (int)((float)Float.valueOf(baseQuota.getThreshold1()))) {
                    status = 1;
                }else{
                    status = 0;
                }
            }else {
                if((int)baseName >= (int)((float)Float.valueOf(baseQuota.getThreshold3()))) {
                    status = 3;
                }else if((int)baseName >= (int)((float)Float.valueOf(baseQuota.getThreshold2()))) {
                    status = 2;
                }else if((int)baseName >= (int)((float)Float.valueOf(baseQuota.getThreshold1()))) {
                    status = 1;
                }else{
                    status = 0;
                }
            }
        }
        return status;
    }

    public static Integer modStatus( List<BaseQuota> baseQuotaList,
                                                  float RRC_setup_succ_Rate,float S1_Setup_Succ_Rate,float ERAB_Setup_Succ_Rate, float paging_Succ_Rate,float Cell_Utilization_Rate,
                                                  float Within_System_HO_Succ_Rate,float Erab_Drop_Rate,float DL_PRB_Occupy_Rate, float UL_PRB_Occupy_Rate,float Dl_ThroughputRate_Mbps,
                                                  float Ul_ThroughputRate_Mbps,float DL_User_Speed_Mbps,float UL_User_Speed_Mbps, float UE_SessionTime,float Drb_pmSessionTime,
                                                  float DL_Active_User_Num,float UL_Active_User_Num) {
        List<Integer> statusList = new ArrayList<Integer>();
        for (int m = 0; m < baseQuotaList.size(); m++){
            BaseQuota baseQuota = baseQuotaList.get(m);
            String baseName = baseQuota.getQuotaName();
            if (baseName.equals("Session_Setup_Success_Rate")){
//                          compare(baseQuota,baseStatus.getRrcSetupSuccRate());
            }else if (baseName.equals("RRC_setup_succ_Rate")){
                statusList.add(compare(baseQuota,RRC_setup_succ_Rate));
            }else if (baseName.equals("S1_Setup_Succ_Rate")){
                statusList.add(compare(baseQuota,S1_Setup_Succ_Rate));
            }else if (baseName.equals("ERAB_Setup_Succ_Rate")){
                statusList.add(compare(baseQuota,ERAB_Setup_Succ_Rate));
            }else if (baseName.equals("paging_Succ_Rate")){
                statusList.add(compare(baseQuota,paging_Succ_Rate));
            }else if (baseName.equals("Cell_Utilization_Rate")){
                statusList.add(compare(baseQuota,Cell_Utilization_Rate));
            }else if (baseName.equals("Within_System_HO_Succ_Rate")){
                statusList.add(compare(baseQuota,Within_System_HO_Succ_Rate));
            }else if (baseName.equals("Erab_Drop_Rate")){
                statusList.add(compare(baseQuota,Erab_Drop_Rate));
            }else if (baseName.equals("DL_PRB_Occupy_Rate")){
                statusList.add(compare(baseQuota,DL_PRB_Occupy_Rate));
            }else if (baseName.equals("UL_PRB_Occupy_Rate")){
                statusList.add(compare(baseQuota,UL_PRB_Occupy_Rate));
            }else if (baseName.equals("Dl_ThroughputRate_Mbps")) {
                statusList.add(compare(baseQuota, Dl_ThroughputRate_Mbps));
            }else if (baseName.equals("Ul_ThroughputRate_Mbps")){
                statusList.add(compare(baseQuota,Ul_ThroughputRate_Mbps));
            }else if (baseName.equals("DL_User_Speed_Mbps")){
                statusList.add(compare(baseQuota,DL_User_Speed_Mbps));
            }else if (baseName.equals("UL_User_Speed_Mbps")){
                statusList.add(compare(baseQuota,UL_User_Speed_Mbps));
            }else if (baseName.equals("UE_SessionTime")){
                statusList.add(compare(baseQuota,UE_SessionTime));
            }else if (baseName.equals("Drb_pmSessionTime")){
                statusList.add(compare(baseQuota,Drb_pmSessionTime));
            }else if (baseName.equals("DL_Active_User_Num")){
                statusList.add(compare(baseQuota,DL_Active_User_Num));
            }else if (baseName.equals("UL_Active_User_Num")){
                statusList.add(compare(baseQuota,UL_Active_User_Num));
            }
        }
        Integer status = Collections.max(statusList);
        return status;
    }
}
