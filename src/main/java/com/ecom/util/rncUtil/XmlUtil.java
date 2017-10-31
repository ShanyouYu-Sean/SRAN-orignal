package com.ecom.util.rncUtil;

import com.ecom.entity.*;
import com.ecom.util.dataBaseUtil.DBUtil;
import com.ecom.util.threadUtil.ThreadUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by a7289 on 2017/1/12 0012.
 */
public class XmlUtil {


    /**
     * 从指定节点Element node开始,递归遍历其所有子节点
     */
    public static List<Map<String,XmlEntity>> analyzeXml(Element root) throws ParseException {

         Map<String,Integer> keyIndex = new HashMap<String, Integer>();
         boolean pmNoIuSigEstablishSuccessCs = false;
         boolean pmNoIuSigEstablishAttemptCs = false;
         boolean pmNoIuSigEstablishSuccessPs = false;
         boolean pmNoIuSigEstablishAttemptPs = false;
         boolean pmTotNoRrcConnectReqSuccess = false;
         boolean pmTotNoRrcConnectReq = false;
         boolean pmNoLoadSharingRrcConn = false;
         boolean pmTotNoRrcConnectReqPsSucc = false;
         boolean pmTotNoRrcConnectReqPs = false;
         boolean pmNoLoadSharingRrcConnPs = false;
         boolean pmTotNoRrcConnectReqCsSucc = false;
         boolean pmTotNoRrcConnectReqCs = false;
         boolean pmNoLoadSharingRrcConnCs = false;
         boolean pmNoRabEstablishSuccessSpeech = false;
         boolean pmNoRabEstablishAttemptSpeech = false;
         boolean pmNoRabEstablishSuccessPacketInteractive = false;
         boolean pmNoRabEstablishAttemptPacketInteractive = false;
         boolean pmNoTimesRlAddToActSet = false;
         boolean pmNoTimesCellFailAddToActSet = false;
         boolean pmNoNormalRabReleaseSpeech = false;
         boolean pmNoSystemRabReleaseSpeech = false;
         boolean pmNoSystemRabReleasePacket = false;
         boolean pmNoSystemRabReleasePacketUra = false;
         boolean pmChSwitchAttemptFachUra = false;
         boolean pmChSwitchSuccFachUra = false;
         boolean pmChSwitchAttemptDchUra = false;
         boolean pmChSwitchAttemptHsUra = false;
         boolean pmChSwitchSuccHsUra = false;
         boolean pmNoNormalRabReleasePacket = false;
         boolean pmNoNormalRabReleasePacketUra = false;
         boolean pmChSwitchSuccDchUra = false;
         Map<String,XmlEntity> cellMap = new HashMap<String, XmlEntity>();
         Map<String,XmlEntity> iurLinkMap = new HashMap<String, XmlEntity>();
         Map<String,XmlEntity> rncMap = new HashMap<String, XmlEntity>();
         String rncId = "";
         String time = "";

        List<Element> mfhList = root.elements("mfh");// 所有一级子节点的list
        for (Element mfh : mfhList) {// 遍历所有一级子节点
            List<Element> snList = mfh.elements("sn");
            for (Element sn : snList){
                String[] snArray = sn.getTextTrim().split(",");
                String[] rncArray = snArray[2].split("=");
                rncId = rncArray[1];
            }
            List<Element> cbtList = mfh.elements("cbt");
            for (Element cbt : cbtList){
                String timeStr =  cbt.getTextTrim().substring(0,14);
                DateFormat dft1 = new SimpleDateFormat("yyyyMMddHHmmss");
                DateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date timeDate = dft1.parse(timeStr);
                time = dft2.format(timeDate);
            }
        }
        List<Element> mdList = root.elements("md");
        for (Element md : mdList){
            List<Element> miList = md.elements("mi");
            for (Element mi : miList){
                List<Element> mtList = mi.elements("mt");
                for (int i = 0; i < mtList.size(); i++) {
                    Element e = mtList.get(i);
                    if (e.getTextTrim().equals("pmNoIuSigEstablishSuccessCs")) {
                            keyIndex.put("pmNoIuSigEstablishSuccessCs", i);
                            pmNoIuSigEstablishSuccessCs = true;
                        }
                        if (e.getTextTrim().equals("pmNoIuSigEstablishAttemptCs")) {
                            keyIndex.put("pmNoIuSigEstablishAttemptCs", i);
                            pmNoIuSigEstablishAttemptCs = true;
                        }
                        if (e.getTextTrim().equals("pmNoIuSigEstablishSuccessPs")) {
                            keyIndex.put("pmNoIuSigEstablishSuccessPs", i);
                            pmNoIuSigEstablishSuccessPs = true;
                        }
                        if (e.getTextTrim().equals("pmNoIuSigEstablishAttemptPs")) {
                            keyIndex.put("pmNoIuSigEstablishAttemptPs", i);
                            pmNoIuSigEstablishAttemptPs = true;
                        }
                        if (e.getTextTrim().equals("pmTotNoRrcConnectReqSuccess")) {
                            keyIndex.put("pmTotNoRrcConnectReqSuccess", i);
                            pmTotNoRrcConnectReqSuccess = true;
                        }
                        if (e.getTextTrim().equals("pmTotNoRrcConnectReq")) {
                            keyIndex.put("pmTotNoRrcConnectReq", i);
                            pmTotNoRrcConnectReq = true;
                        }
                        if (e.getTextTrim().equals("pmNoLoadSharingRrcConn")) {
                            keyIndex.put("pmNoLoadSharingRrcConn", i);
                            pmNoLoadSharingRrcConn = true;
                        }
                        if (e.getTextTrim().equals("pmTotNoRrcConnectReqPsSucc")) {
                            keyIndex.put("pmTotNoRrcConnectReqPsSucc", i);
                            pmTotNoRrcConnectReqPsSucc = true;
                        }
                        if (e.getTextTrim().equals("pmTotNoRrcConnectReqPs")) {
                            keyIndex.put("pmTotNoRrcConnectReqPs", i);
                            pmTotNoRrcConnectReqPs = true;
                        }
                        if (e.getTextTrim().equals("pmNoLoadSharingRrcConnPs")) {
                            keyIndex.put("pmNoLoadSharingRrcConnPs", i);
                            pmNoLoadSharingRrcConnPs = true;
                        }
                        if (e.getTextTrim().equals("pmTotNoRrcConnectReqCsSucc")) {
                            keyIndex.put("pmTotNoRrcConnectReqCsSucc", i);
                            pmTotNoRrcConnectReqCsSucc = true;
                        }
                        if (e.getTextTrim().equals("pmTotNoRrcConnectReqCs")) {
                            keyIndex.put("pmTotNoRrcConnectReqCs", i);
                            pmTotNoRrcConnectReqCs = true;
                        }
                        if (e.getTextTrim().equals("pmNoLoadSharingRrcConnCs")) {
                            keyIndex.put("pmNoLoadSharingRrcConnCs", i);
                            pmNoLoadSharingRrcConnCs = true;
                        }
                        if (e.getTextTrim().equals("pmNoRabEstablishSuccessSpeech")) {
                            keyIndex.put("pmNoRabEstablishSuccessSpeech", i);
                            pmNoRabEstablishSuccessSpeech = true;
                        }
                        if (e.getTextTrim().equals("pmNoRabEstablishAttemptSpeech")) {
                            keyIndex.put("pmNoRabEstablishAttemptSpeech", i);
                            pmNoRabEstablishAttemptSpeech = true;
                        }
                        if (e.getTextTrim().equals("pmNoRabEstablishSuccessPacketInteractive")) {
                            keyIndex.put("pmNoRabEstablishSuccessPacketInteractive", i);
                            pmNoRabEstablishSuccessPacketInteractive = true;
                        }
                        if (e.getTextTrim().equals("pmNoRabEstablishAttemptPacketInteractive")) {
                            keyIndex.put("pmNoRabEstablishAttemptPacketInteractive", i);
                            pmNoRabEstablishAttemptPacketInteractive = true;
                        }
                        if (e.getTextTrim().equals("pmNoTimesRlAddToActSet")) {
                            keyIndex.put("pmNoTimesRlAddToActSet", i);
                            pmNoTimesRlAddToActSet = true;
                        }
                        if (e.getTextTrim().equals("pmNoTimesCellFailAddToActSet")) {
                            keyIndex.put("pmNoTimesCellFailAddToActSet", i);
                            pmNoTimesCellFailAddToActSet = true;
                        }
                        if (e.getTextTrim().equals("pmNoSystemRabReleaseSpeech")) {
                            keyIndex.put("pmNoSystemRabReleaseSpeech", i);
                            pmNoSystemRabReleaseSpeech = true;
                        }
                        if (e.getTextTrim().equals("pmNoNormalRabReleaseSpeech")) {
                            keyIndex.put("pmNoNormalRabReleaseSpeech", i);
                            pmNoNormalRabReleaseSpeech = true;
                        }
                        if (e.getTextTrim().equals("pmNoSystemRabReleasePacket")) {
                            keyIndex.put("pmNoSystemRabReleasePacket", i);
                            pmNoSystemRabReleasePacket = true;
                        }
                        if (e.getTextTrim().equals("pmNoSystemRabReleasePacketUra")) {
                            keyIndex.put("pmNoSystemRabReleasePacketUra", i);
                            pmNoSystemRabReleasePacketUra = true;
                        }
                        if (e.getTextTrim().equals("pmChSwitchAttemptFachUra")) {
                            keyIndex.put("pmChSwitchAttemptFachUra", i);
                            pmChSwitchAttemptFachUra = true;
                        }
                        if (e.getTextTrim().equals("pmChSwitchSuccFachUra")) {
                            keyIndex.put("pmChSwitchSuccFachUra", i);
                            pmChSwitchSuccFachUra = true;
                        }
                        if (e.getTextTrim().equals("pmChSwitchAttemptDchUra")) {
                            keyIndex.put("pmChSwitchAttemptDchUra", i);
                            pmChSwitchAttemptDchUra = true;
                        }
                        if (e.getTextTrim().equals("pmChSwitchSuccDchUra")) {
                            keyIndex.put("pmChSwitchSuccDchUra", i);
                            pmChSwitchSuccDchUra = true;
                        }
                        if (e.getTextTrim().equals("pmChSwitchAttemptHsUra")) {
                            keyIndex.put("pmChSwitchAttemptHsUra", i);
                            pmChSwitchAttemptHsUra = true;
                        }
                        if (e.getTextTrim().equals("pmChSwitchSuccHsUra")) {
                            keyIndex.put("pmChSwitchSuccHsUra", i);
                            pmChSwitchSuccHsUra = true;
                        }
                        if (e.getTextTrim().equals("pmNoNormalRabReleasePacket")) {
                            keyIndex.put("pmNoNormalRabReleasePacket", i);
                            pmNoNormalRabReleasePacket = true;
                        }
                        if (e.getTextTrim().equals("pmNoNormalRabReleasePacketUra")) {
                            keyIndex.put("pmNoNormalRabReleasePacketUra", i);
                            pmNoNormalRabReleasePacketUra = true;
                        }
                }
                if (pmNoIuSigEstablishSuccessCs || pmNoIuSigEstablishAttemptCs || pmNoIuSigEstablishSuccessPs || pmNoIuSigEstablishAttemptPs ||
                        pmTotNoRrcConnectReqSuccess || pmTotNoRrcConnectReq || pmNoLoadSharingRrcConn || pmTotNoRrcConnectReqPsSucc ||
                        pmTotNoRrcConnectReqPs || pmNoLoadSharingRrcConnPs || pmTotNoRrcConnectReqCsSucc || pmTotNoRrcConnectReqCs || pmNoLoadSharingRrcConnCs ||
                        pmNoRabEstablishSuccessSpeech || pmNoRabEstablishAttemptSpeech || pmNoRabEstablishSuccessPacketInteractive || pmNoRabEstablishAttemptPacketInteractive ||
                        pmNoTimesRlAddToActSet || pmNoTimesCellFailAddToActSet || pmNoSystemRabReleaseSpeech || pmNoNormalRabReleaseSpeech || pmNoSystemRabReleasePacket ||
                        pmNoSystemRabReleasePacketUra || pmChSwitchAttemptFachUra || pmChSwitchSuccFachUra || pmChSwitchAttemptDchUra || pmChSwitchSuccDchUra ||
                        pmChSwitchAttemptHsUra || pmChSwitchSuccHsUra || pmNoNormalRabReleasePacket || pmNoNormalRabReleasePacketUra ) {
                    List<Element> mvList = mi.elements("mv");{
                        for (Element mv : mvList) {
                            List<Element> moidList = mv.elements("moid");
                            List<Element> rList = mv.elements("r");
                            for (int i = 0; i < moidList.size(); i++) {
                                Element moid = moidList.get(i);
                                    String cellId = "";
                                    String iurLink = "";
                                    String[] moidArray = moid.getTextTrim().split(",");
                                    if (moidArray.length == 2){
                                        XmlEntity rnc = new XmlEntity();
                                        rnc.setRncId(rncId);
                                        rnc.setTime(time);
                                        if (pmNoIuSigEstablishSuccessCs) {
                                            rnc.setPmNoIuSigEstablishSuccessCs(rList.get(keyIndex.get("pmNoIuSigEstablishSuccessCs")).getTextTrim());
                                        }
                                        if (pmNoIuSigEstablishAttemptCs) {
                                            rnc.setPmNoIuSigEstablishAttemptCs(rList.get(keyIndex.get("pmNoIuSigEstablishAttemptCs")).getTextTrim());
                                        }
                                        if (pmNoIuSigEstablishSuccessPs) {
                                            rnc.setPmNoIuSigEstablishSuccessPs(rList.get(keyIndex.get("pmNoIuSigEstablishSuccessPs")).getTextTrim());
                                        }
                                        if (pmNoIuSigEstablishAttemptPs) {
                                            rnc.setPmNoIuSigEstablishAttemptPs(rList.get(keyIndex.get("pmNoIuSigEstablishAttemptPs")).getTextTrim());
                                        }
                                        rncMap.put(rncId,rnc);
                                    }else if (moidArray.length == 3){
                                        String[] cellArray = moidArray[2].split("=");
                                        if (cellArray[0].equals("UtranCell")){
                                            cellId = cellArray[1];
                                            setCounter(cellMap, cellId, rList,keyIndex, rncId , time , pmTotNoRrcConnectReqSuccess ,  pmTotNoRrcConnectReq ,
                                                    pmNoLoadSharingRrcConn , pmTotNoRrcConnectReqPsSucc , pmTotNoRrcConnectReqPs ,  pmNoLoadSharingRrcConnPs ,
                                                    pmTotNoRrcConnectReqCsSucc , pmTotNoRrcConnectReqCs , pmNoLoadSharingRrcConnCs ,  pmNoRabEstablishSuccessSpeech ,
                                                    pmNoRabEstablishAttemptSpeech , pmNoRabEstablishSuccessPacketInteractive ,  pmNoRabEstablishAttemptPacketInteractive ,
                                                    pmNoTimesRlAddToActSet , pmNoTimesCellFailAddToActSet ,  pmNoSystemRabReleaseSpeech , pmNoNormalRabReleaseSpeech ,
                                                    pmNoSystemRabReleasePacket ,  pmNoSystemRabReleasePacketUra , pmChSwitchAttemptFachUra , pmChSwitchSuccFachUra ,
                                                    pmChSwitchAttemptDchUra ,  pmChSwitchSuccDchUra , pmChSwitchAttemptHsUra  , pmChSwitchSuccHsUra  ,
                                                    pmNoNormalRabReleasePacket  , pmNoNormalRabReleasePacketUra );
                                        }else if (cellArray[0].equals("IurLink")){
                                            iurLink = cellArray[1];
                                            setCounter(iurLinkMap, iurLink, rList,keyIndex, rncId , time , pmTotNoRrcConnectReqSuccess ,  pmTotNoRrcConnectReq ,
                                                    pmNoLoadSharingRrcConn , pmTotNoRrcConnectReqPsSucc , pmTotNoRrcConnectReqPs ,  pmNoLoadSharingRrcConnPs ,
                                                    pmTotNoRrcConnectReqCsSucc , pmTotNoRrcConnectReqCs , pmNoLoadSharingRrcConnCs ,  pmNoRabEstablishSuccessSpeech ,
                                                    pmNoRabEstablishAttemptSpeech , pmNoRabEstablishSuccessPacketInteractive ,  pmNoRabEstablishAttemptPacketInteractive ,
                                                    pmNoTimesRlAddToActSet , pmNoTimesCellFailAddToActSet ,  pmNoSystemRabReleaseSpeech , pmNoNormalRabReleaseSpeech ,
                                                    pmNoSystemRabReleasePacket ,  pmNoSystemRabReleasePacketUra , pmChSwitchAttemptFachUra , pmChSwitchSuccFachUra ,
                                                    pmChSwitchAttemptDchUra ,  pmChSwitchSuccDchUra , pmChSwitchAttemptHsUra  , pmChSwitchSuccHsUra  ,
                                                    pmNoNormalRabReleasePacket  , pmNoNormalRabReleasePacketUra );
                                        }
                                    }
                            }
                        }
                    }
                }
            }
            pmNoIuSigEstablishSuccessCs = false;
            pmNoIuSigEstablishAttemptCs = false;
            pmNoIuSigEstablishSuccessPs = false;
            pmNoIuSigEstablishAttemptPs = false;
            pmTotNoRrcConnectReqSuccess = false;
            pmTotNoRrcConnectReq = false;
            pmNoLoadSharingRrcConn = false;
            pmTotNoRrcConnectReqPsSucc = false;
            pmTotNoRrcConnectReqPs = false;
            pmNoLoadSharingRrcConnPs = false;
            pmTotNoRrcConnectReqCsSucc = false;
            pmTotNoRrcConnectReqCs = false;
            pmNoLoadSharingRrcConnCs = false;
            pmNoRabEstablishSuccessSpeech = false;
            pmNoRabEstablishAttemptSpeech = false;
            pmNoRabEstablishSuccessPacketInteractive = false;
            pmNoRabEstablishAttemptPacketInteractive = false;
            pmNoTimesRlAddToActSet = false;
            pmNoTimesCellFailAddToActSet = false;
            pmNoSystemRabReleaseSpeech = false;
            pmNoNormalRabReleaseSpeech = false;
            pmNoSystemRabReleasePacket = false;
            pmNoSystemRabReleasePacketUra = false;
            pmChSwitchAttemptFachUra = false;
            pmChSwitchSuccFachUra = false;
            pmChSwitchAttemptDchUra = false;
            pmChSwitchSuccDchUra = false;
            pmChSwitchAttemptHsUra = false;
            pmChSwitchSuccHsUra = false;
            pmNoNormalRabReleasePacket = false;
            pmNoNormalRabReleasePacketUra = false;
        }
        List<Map<String,XmlEntity>> xmlMapList = new ArrayList<Map<String, XmlEntity>>();
        xmlMapList.add(cellMap);
        xmlMapList.add(iurLinkMap);
        xmlMapList.add(rncMap);
        return xmlMapList;
    }

    public static void setCounter(Map<String,XmlEntity> map, String id, List<Element> rList,Map<String,Integer> keyIndex,String rncId ,String time ,
                                  boolean pmTotNoRrcConnectReqSuccess , boolean pmTotNoRrcConnectReq ,boolean pmNoLoadSharingRrcConn ,boolean pmTotNoRrcConnectReqPsSucc ,
                                  boolean pmTotNoRrcConnectReqPs , boolean pmNoLoadSharingRrcConnPs ,boolean pmTotNoRrcConnectReqCsSucc ,boolean pmTotNoRrcConnectReqCs ,
                                  boolean pmNoLoadSharingRrcConnCs , boolean pmNoRabEstablishSuccessSpeech , boolean pmNoRabEstablishAttemptSpeech ,
                                  boolean pmNoRabEstablishSuccessPacketInteractive , boolean pmNoRabEstablishAttemptPacketInteractive ,boolean pmNoTimesRlAddToActSet ,
                                  boolean pmNoTimesCellFailAddToActSet , boolean pmNoSystemRabReleaseSpeech ,boolean pmNoNormalRabReleaseSpeech ,
                                  boolean pmNoSystemRabReleasePacket , boolean pmNoSystemRabReleasePacketUra ,boolean pmChSwitchAttemptFachUra ,
                                  boolean pmChSwitchSuccFachUra , boolean pmChSwitchAttemptDchUra , boolean pmChSwitchSuccDchUra ,boolean pmChSwitchAttemptHsUra  ,
                                  boolean pmChSwitchSuccHsUra  , boolean pmNoNormalRabReleasePacket  ,boolean pmNoNormalRabReleasePacketUra ){
        if (pmTotNoRrcConnectReqSuccess) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmTotNoRrcConnectReqSuccess(rList.get(keyIndex.get("pmTotNoRrcConnectReqSuccess")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmTotNoRrcConnectReqSuccess(rList.get(keyIndex.get("pmTotNoRrcConnectReqSuccess")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmTotNoRrcConnectReq) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmTotNoRrcConnectReq(rList.get(keyIndex.get("pmTotNoRrcConnectReq")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmTotNoRrcConnectReq(rList.get(keyIndex.get("pmTotNoRrcConnectReq")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoLoadSharingRrcConn) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoLoadSharingRrcConn(rList.get(keyIndex.get("pmNoLoadSharingRrcConn")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoLoadSharingRrcConn(rList.get(keyIndex.get("pmNoLoadSharingRrcConn")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmTotNoRrcConnectReqPsSucc) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmTotNoRrcConnectReqPsSucc(rList.get(keyIndex.get("pmTotNoRrcConnectReqPsSucc")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmTotNoRrcConnectReqPsSucc(rList.get(keyIndex.get("pmTotNoRrcConnectReqPsSucc")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmTotNoRrcConnectReqPs) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmTotNoRrcConnectReqPs(rList.get(keyIndex.get("pmTotNoRrcConnectReqPs")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmTotNoRrcConnectReqPs(rList.get(keyIndex.get("pmTotNoRrcConnectReqPs")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoLoadSharingRrcConnPs) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoLoadSharingRrcConnPs(rList.get(keyIndex.get("pmNoLoadSharingRrcConnPs")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoLoadSharingRrcConnPs(rList.get(keyIndex.get("pmNoLoadSharingRrcConnPs")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmTotNoRrcConnectReqCsSucc) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmTotNoRrcConnectReqCsSucc(rList.get(keyIndex.get("pmTotNoRrcConnectReqCsSucc")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmTotNoRrcConnectReqCsSucc(rList.get(keyIndex.get("pmTotNoRrcConnectReqCsSucc")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmTotNoRrcConnectReqCs) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmTotNoRrcConnectReqCs(rList.get(keyIndex.get("pmTotNoRrcConnectReqCs")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmTotNoRrcConnectReqCs(rList.get(keyIndex.get("pmTotNoRrcConnectReqCs")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoLoadSharingRrcConnCs) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoLoadSharingRrcConnCs(rList.get(keyIndex.get("pmNoLoadSharingRrcConnCs")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoLoadSharingRrcConnCs(rList.get(keyIndex.get("pmNoLoadSharingRrcConnCs")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoRabEstablishSuccessSpeech) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoRabEstablishSuccessSpeech(rList.get(keyIndex.get("pmNoRabEstablishSuccessSpeech")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoRabEstablishSuccessSpeech(rList.get(keyIndex.get("pmNoRabEstablishSuccessSpeech")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoRabEstablishAttemptSpeech) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoRabEstablishAttemptSpeech(rList.get(keyIndex.get("pmNoRabEstablishAttemptSpeech")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoRabEstablishAttemptSpeech(rList.get(keyIndex.get("pmNoRabEstablishAttemptSpeech")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoRabEstablishSuccessPacketInteractive) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoRabEstablishSuccessPacketInteractive(rList.get(keyIndex.get("pmNoRabEstablishSuccessPacketInteractive")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoRabEstablishSuccessPacketInteractive(rList.get(keyIndex.get("pmNoRabEstablishSuccessPacketInteractive")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoRabEstablishAttemptPacketInteractive) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoRabEstablishAttemptPacketInteractive(rList.get(keyIndex.get("pmNoRabEstablishAttemptPacketInteractive")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoRabEstablishAttemptPacketInteractive(rList.get(keyIndex.get("pmNoRabEstablishAttemptPacketInteractive")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoTimesRlAddToActSet) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoTimesRlAddToActSet(rList.get(keyIndex.get("pmNoTimesRlAddToActSet")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoTimesRlAddToActSet(rList.get(keyIndex.get("pmNoTimesRlAddToActSet")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoTimesCellFailAddToActSet) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoTimesCellFailAddToActSet(rList.get(keyIndex.get("pmNoTimesCellFailAddToActSet")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoTimesCellFailAddToActSet(rList.get(keyIndex.get("pmNoTimesCellFailAddToActSet")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoSystemRabReleaseSpeech) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoSystemRabReleaseSpeech(rList.get(keyIndex.get("pmNoSystemRabReleaseSpeech")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoSystemRabReleaseSpeech(rList.get(keyIndex.get("pmNoSystemRabReleaseSpeech")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoNormalRabReleaseSpeech) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoNormalRabReleaseSpeech(rList.get(keyIndex.get("pmNoNormalRabReleaseSpeech")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoNormalRabReleaseSpeech(rList.get(keyIndex.get("pmNoNormalRabReleaseSpeech")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoSystemRabReleasePacket) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoSystemRabReleasePacket(rList.get(keyIndex.get("pmNoSystemRabReleasePacket")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoSystemRabReleasePacket(rList.get(keyIndex.get("pmNoSystemRabReleasePacket")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoSystemRabReleasePacketUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoSystemRabReleasePacketUra(rList.get(keyIndex.get("pmNoSystemRabReleasePacketUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoSystemRabReleasePacketUra(rList.get(keyIndex.get("pmNoSystemRabReleasePacketUra")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmChSwitchAttemptFachUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmChSwitchAttemptFachUra(rList.get(keyIndex.get("pmChSwitchAttemptFachUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmChSwitchAttemptFachUra(rList.get(keyIndex.get("pmChSwitchAttemptFachUra")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmChSwitchSuccFachUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmChSwitchSuccFachUra(rList.get(keyIndex.get("pmChSwitchSuccFachUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmChSwitchSuccFachUra(rList.get(keyIndex.get("pmChSwitchSuccFachUra")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmChSwitchAttemptDchUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmChSwitchAttemptDchUra(rList.get(keyIndex.get("pmChSwitchAttemptDchUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmChSwitchAttemptDchUra(rList.get(keyIndex.get("pmChSwitchAttemptDchUra")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmChSwitchSuccDchUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmChSwitchSuccDchUra(rList.get(keyIndex.get("pmChSwitchSuccDchUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmChSwitchSuccDchUra(rList.get(keyIndex.get("pmChSwitchSuccDchUra")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmChSwitchAttemptHsUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmChSwitchAttemptHsUra(rList.get(keyIndex.get("pmChSwitchAttemptHsUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmChSwitchAttemptHsUra(rList.get(keyIndex.get("pmChSwitchAttemptHsUra")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmChSwitchSuccHsUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmChSwitchSuccHsUra(rList.get(keyIndex.get("pmChSwitchSuccHsUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmChSwitchSuccHsUra(rList.get(keyIndex.get("pmChSwitchSuccHsUra")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoNormalRabReleasePacket) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoNormalRabReleasePacket(rList.get(keyIndex.get("pmNoNormalRabReleasePacket")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoNormalRabReleasePacket(rList.get(keyIndex.get("pmNoNormalRabReleasePacket")).getTextTrim());
                map.put(id,cell);
            }
        }
        if (pmNoNormalRabReleasePacketUra) {
            if (map.get(id)==null){
                XmlEntity cell = new XmlEntity();
                cell.setRncId(rncId);
                cell.setCellId(id);
                cell.setTime(time);
                cell.setPmNoNormalRabReleasePacketUra(rList.get(keyIndex.get("pmNoNormalRabReleasePacketUra")).getTextTrim());
                map.put(id,cell);
            }else {
                XmlEntity cell = map.get(id);
                cell.setPmNoNormalRabReleasePacketUra(rList.get(keyIndex.get("pmNoNormalRabReleasePacketUra")).getTextTrim());
                map.put(id,cell);
            }
        }
    }

    /**
     * 获取文件的xml对象，然后获取对应的根节点root, 将解析后的map列表传给calculateCell3g方法，并计算cell
     */
    public static synchronized void getXmlRootAndCalculateCell3g(String filePath) throws Exception {
        File xmlFile = new File(filePath);
        SAXReader sax = new SAXReader();// 创建一个SAXReader对象
        sax.setEntityResolver(new IgnoreDTDEntityResolver()); // ignore dtd
        Document document = sax.read(xmlFile);// 获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root = document.getRootElement();// 获取根节点
        List<Map<String,XmlEntity>> xmlMapList = analyzeXml(root);// 从根节点开始遍历所有节点
//        calculateCell3g(xmlMapList);
    }

    public static void calculateCell3g(List<Map<String,XmlEntity>> xmlMapList){
        Map<String,XmlEntity> cellMap = xmlMapList.get(0);
        Map<String,XmlEntity> iurLinkMap = xmlMapList.get(1);
        Map<String,XmlEntity> rncMap = xmlMapList.get(2);
        List<BaseQuota> baseQuotaList = XmlUtil.getQuota("quota_threshold_cell_3g");
        try {

            int x = 1;
            String parmRnc = "";
            int y = 1;
            String parmIurLink = "";
            int z = 1;
            String parmCell = "";
            String parmBaseCell = "";
            String parmBackUpCell = "";
            for (XmlEntity rncTemp : rncMap.values()) {
                if (x < 500){
                    if (parmRnc == ""){
                        parmRnc = rncTemp.getRncValue();
                    }else {
                        parmRnc = parmRnc + "," + rncTemp.getRncValue();
                    }
                }else {
                    parmRnc = parmRnc + "," + rncTemp.getRncValue();
                    String insert_sql = "INSERT INTO temp_3g (rnc_name,time,pm_No_Iu_Sig_Establish_Success_Cs,pm_No_Iu_Sig_Establish_Attempt_Cs,pm_No_Iu_Sig_Establish_Success_Ps,pm_No_Iu_Sig_Establish_Attempt_Ps) VALUES" + parmRnc;
                    DBUtil dbUtil1 = new DBUtil();
                    dbUtil1.execOther(insert_sql, new Object[]{});
                    dbUtil1.closeConnection();
                    x = 0;
                    parmRnc = "";
                }
                x++;
            }
            for (XmlEntity inrLinkTemp : iurLinkMap.values()){
                if (y < 500){
                    if (parmIurLink == ""){
                        parmIurLink = inrLinkTemp.getIurLinkValue();
                    }else {
                        parmIurLink = parmIurLink + "," + inrLinkTemp.getIurLinkValue();
                    }
                }else {
                    parmIurLink = parmIurLink + "," + inrLinkTemp.getIurLinkValue();
                    String insert_sql = "INSERT INTO temp_3g (rnc_name,iur_link_name,time,pm_No_System_Rab_Release_Speech,pm_No_Normal_Rab_Release_Speech,pm_No_System_Rab_Release_Packet,pm_No_Normal_Rab_Release_Packet) VALUES" + parmIurLink;
                    DBUtil dbUtil1 = new DBUtil();
                    dbUtil1.execOther(insert_sql, new Object[]{});
                    dbUtil1.closeConnection();
                    y = 0;
                    parmIurLink = "";
                }
                y++;
            }
            for (XmlEntity cellTemp : cellMap.values()){

                String cell_name = cellTemp.getCellId();
                String time = cellTemp.getTime();
                float  pm_Tot_No_Rrc_Connect_Req_Success = Float.parseFloat(cellTemp.getPmTotNoRrcConnectReqSuccess());
                float  pm_Tot_No_Rrc_Connect_Req = Float.parseFloat(cellTemp.getPmTotNoRrcConnectReq());
                float  pm_No_Load_Sharing_Rrc_Conn = Float.parseFloat(cellTemp.getPmNoLoadSharingRrcConn());
                float  pm_Tot_No_Rrc_Connect_Req_Ps_Succ = Float.parseFloat(cellTemp.getPmTotNoRrcConnectReqPsSucc());
                float  pm_Tot_No_Rrc_Connect_Req_Ps = Float.parseFloat(cellTemp.getPmTotNoRrcConnectReqPs());
                float  pm_No_Load_Sharing_Rrc_Conn_Ps = Float.parseFloat(cellTemp.getPmNoLoadSharingRrcConnPs());
                float  pm_Tot_No_Rrc_Connect_Req_Cs_Succ = Float.parseFloat(cellTemp.getPmTotNoRrcConnectReqCsSucc());
                float  pm_Tot_No_Rrc_Connect_Req_Cs = Float.parseFloat(cellTemp.getPmTotNoRrcConnectReqCs());
                float  pm_No_Load_Sharing_Rrc_Conn_Cs = Float.parseFloat(cellTemp.getPmNoLoadSharingRrcConnCs());
                float  pm_No_Rab_Establish_Success_Speech = Float.parseFloat(cellTemp.getPmNoRabEstablishSuccessSpeech());
                float  pm_No_Rab_Establish_Attempt_Speech = Float.parseFloat(cellTemp.getPmNoRabEstablishAttemptSpeech());
                float  pm_No_Rab_Establish_Success_Packet_Interactive = Float.parseFloat(cellTemp.getPmNoRabEstablishSuccessPacketInteractive());
                float  pm_No_Rab_Establish_Attempt_Packet_Interactive = Float.parseFloat(cellTemp.getPmNoRabEstablishAttemptPacketInteractive());
                float  pm_No_Times_Rl_Add_To_Act_Set = Float.parseFloat(cellTemp.getPmNoTimesRlAddToActSet());
                float  pm_No_Times_Cell_Fail_Add_To_Act_Set = Float.parseFloat(cellTemp.getPmNoTimesCellFailAddToActSet());
                float  pm_No_System_Rab_Release_Speech = Float.parseFloat(cellTemp.getPmNoSystemRabReleaseSpeech());
                float  pm_No_Normal_Rab_Release_Speech = Float.parseFloat(cellTemp.getPmNoNormalRabReleaseSpeech());
                float  pm_No_System_Rab_Release_Packet = Float.parseFloat(cellTemp.getPmNoSystemRabReleasePacket());
                float  pm_No_System_Rab_Release_Packet_Ura = Float.parseFloat(cellTemp.getPmNoSystemRabReleasePacketUra());
                float  pm_Ch_Switch_Attempt_Fach_Ura = Float.parseFloat(cellTemp.getPmChSwitchAttemptFachUra());
                float  pm_Ch_Switch_Succ_Fach_Ura = Float.parseFloat(cellTemp.getPmChSwitchSuccFachUra());
                float  pm_Ch_Switch_Attempt_Dch_Ura = Float.parseFloat(cellTemp.getPmChSwitchAttemptDchUra());
                float  pm_Ch_Switch_Succ_Dch_Ura = Float.parseFloat(cellTemp.getPmChSwitchSuccDchUra());
                float  pm_Ch_Switch_Attempt_Hs_Ura = Float.parseFloat(cellTemp.getPmChSwitchAttemptHsUra());
                float  pm_Ch_Switch_Succ_Hs_Ura = Float.parseFloat(cellTemp.getPmChSwitchSuccHsUra());
                float  pm_No_Normal_Rab_Release_Packet = Float.parseFloat(cellTemp.getPmNoNormalRabReleasePacket());
                float  pm_No_Normal_Rab_Release_Packet_Ura = Float.parseFloat(cellTemp.getPmNoNormalRabReleasePacketUra());
                float  pmNoRejRrcConnSpFlowCtrl = 0;//目前这个counter是缺失的

                float  Rrc_Suc;
                if ((pm_Tot_No_Rrc_Connect_Req   - pm_No_Load_Sharing_Rrc_Conn + pmNoRejRrcConnSpFlowCtrl) == 0){
                    Rrc_Suc = 0;
                }else {
                    Rrc_Suc = (100 * pm_Tot_No_Rrc_Connect_Req_Success / (pm_Tot_No_Rrc_Connect_Req   - pm_No_Load_Sharing_Rrc_Conn + pmNoRejRrcConnSpFlowCtrl));
                }
                float PS_Rrc_Suc_Rate;
                if ((pm_Tot_No_Rrc_Connect_Req_Ps - pm_No_Load_Sharing_Rrc_Conn_Ps) == 0){
                    PS_Rrc_Suc_Rate = 0;
                }else {
                    PS_Rrc_Suc_Rate  = (100 * pm_Tot_No_Rrc_Connect_Req_Ps_Succ  / (pm_Tot_No_Rrc_Connect_Req_Ps - pm_No_Load_Sharing_Rrc_Conn_Ps));
                }
                float Spch_Rrc_Suc_Rate;
                if ((pm_Tot_No_Rrc_Connect_Req_Cs - pm_No_Load_Sharing_Rrc_Conn_Cs) == 0){
                    Spch_Rrc_Suc_Rate = 0;
                }else {
                    Spch_Rrc_Suc_Rate = (100 * pm_Tot_No_Rrc_Connect_Req_Cs_Succ  / (pm_Tot_No_Rrc_Connect_Req_Cs - pm_No_Load_Sharing_Rrc_Conn_Cs));
                }
                float Spch_Rab_Suc;
                if (pm_No_Rab_Establish_Attempt_Speech == 0){
                    Spch_Rab_Suc = 0;
                }else {
                    Spch_Rab_Suc = (100 * pm_No_Rab_Establish_Success_Speech / pm_No_Rab_Establish_Attempt_Speech);
                }
                float PS_Rab_Succ;
                if (pm_No_Rab_Establish_Attempt_Packet_Interactive == 0){
                    PS_Rab_Succ = 0;
                }else {
                    PS_Rab_Succ = (100 * pm_No_Rab_Establish_Success_Packet_Interactive / pm_No_Rab_Establish_Attempt_Packet_Interactive);
                }
                float SHO_Success;
                if ((pm_No_Times_Rl_Add_To_Act_Set + pm_No_Times_Cell_Fail_Add_To_Act_Set) == 0){
                    SHO_Success = 0;
                }else {
                    SHO_Success = (100 * pm_No_Times_Rl_Add_To_Act_Set / (pm_No_Times_Rl_Add_To_Act_Set + pm_No_Times_Cell_Fail_Add_To_Act_Set));
                }
                float Spch_Drop;
                if ((pm_No_Normal_Rab_Release_Speech + pm_No_System_Rab_Release_Speech) == 0){
                    Spch_Drop = 0;
                }else {
                    Spch_Drop = (100 * pm_No_System_Rab_Release_Speech / (pm_No_Normal_Rab_Release_Speech + pm_No_System_Rab_Release_Speech));
                }
                float PS_Drop;
                if ((pm_No_Normal_Rab_Release_Packet - pm_No_Normal_Rab_Release_Packet_Ura + pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura + pm_Ch_Switch_Succ_Fach_Ura + pm_Ch_Switch_Succ_Dch_Ura + pm_Ch_Switch_Succ_Hs_Ura) == 0){
                    PS_Drop = 0;
                }else {
                    PS_Drop = (100 * (pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura - pm_Ch_Switch_Attempt_Fach_Ura + pm_Ch_Switch_Succ_Fach_Ura - pm_Ch_Switch_Attempt_Dch_Ura + pm_Ch_Switch_Succ_Dch_Ura - pm_Ch_Switch_Attempt_Hs_Ura + pm_Ch_Switch_Succ_Hs_Ura) / (pm_No_Normal_Rab_Release_Packet - pm_No_Normal_Rab_Release_Packet_Ura + pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura + pm_Ch_Switch_Succ_Fach_Ura + pm_Ch_Switch_Succ_Dch_Ura + pm_Ch_Switch_Succ_Hs_Ura));
                }
                Integer status = modStatus(baseQuotaList, 0, 0, Rrc_Suc,  PS_Rrc_Suc_Rate, Spch_Rrc_Suc_Rate, Spch_Rab_Suc, PS_Rab_Succ, SHO_Success,  Spch_Drop, PS_Drop);
                String strBackUpCell = "( '"+status+"', '"+
                        time+"', '"+
                        cell_name+"', '"+
                        Rrc_Suc+"', '"+
                        PS_Rrc_Suc_Rate+"', '"+
                        Spch_Rrc_Suc_Rate+"', '"+
                        Spch_Rab_Suc+"', '"+
                        PS_Rab_Succ+"', '"+
                        SHO_Success+"', '"+
                        Spch_Drop+"', '"+
                        PS_Drop+"' )";

                String strBaseCell = "( '"+status+"', '"+
                        cell_name+"', '"+
                        Rrc_Suc+"', '"+
                        PS_Rrc_Suc_Rate+"', '"+
                        Spch_Rrc_Suc_Rate+"', '"+
                        Spch_Rab_Suc+"', '"+
                        PS_Rab_Succ+"', '"+
                        SHO_Success+"', '"+
                        Spch_Drop+"', '"+
                        PS_Drop+"' )";

                if (z < 500){
                    if (parmCell == ""){
                        parmCell = cellTemp.getCellValue();
                    }else {
                        parmCell = parmCell + "," + cellTemp.getCellValue();
                    }
                    if (parmBaseCell == ""){
                        parmBaseCell = strBaseCell;
                    }else {
                        parmBaseCell = parmBaseCell + "," + strBaseCell;
                    }
                    if (parmBackUpCell == ""){
                        parmBackUpCell = strBackUpCell;
                    }else {
                        parmBackUpCell = parmBackUpCell + "," + strBackUpCell;
                    }
                }else {
                    parmCell = parmCell + "," + cellTemp.getCellValue();
                    parmBaseCell = parmBaseCell + "," + strBaseCell;
                    parmBackUpCell = parmBackUpCell + "," + strBackUpCell;
                    String insert_sql1 = "INSERT INTO temp_3g (rnc_name,cell_name,time,pm_Tot_No_Rrc_Connect_Req_Success,pm_Tot_No_Rrc_Connect_Req,pm_No_Load_Sharing_Rrc_Conn,pm_Tot_No_Rrc_Connect_Req_Ps_Succ,pm_Tot_No_Rrc_Connect_Req_Ps,pm_No_Load_Sharing_Rrc_Conn_Ps,pm_Tot_No_Rrc_Connect_Req_Cs_Succ,pm_Tot_No_Rrc_Connect_Req_Cs,pm_No_Load_Sharing_Rrc_Conn_Cs,pm_No_Rab_Establish_Success_Speech,pm_No_Rab_Establish_Attempt_Speech,pm_No_Rab_Establish_Success_Packet_Interactive,pm_No_Rab_Establish_Attempt_Packet_Interactive,pm_No_Times_Rl_Add_To_Act_Set,pm_No_Times_Cell_Fail_Add_To_Act_Set,pm_No_System_Rab_Release_Speech,pm_No_Normal_Rab_Release_Speech,pm_No_System_Rab_Release_Packet,pm_No_System_Rab_Release_Packet_Ura,pm_Ch_Switch_Attempt_Fach_Ura,pm_Ch_Switch_Succ_Fach_Ura,pm_Ch_Switch_Attempt_Dch_Ura,pm_Ch_Switch_Succ_Dch_Ura,pm_Ch_Switch_Attempt_Hs_Ura,pm_Ch_Switch_Succ_Hs_Ura,pm_No_Normal_Rab_Release_Packet,pm_No_Normal_Rab_Release_Packet_Ura) VALUES" + parmCell;
                    String insert_sql2 = "INSERT INTO base_cell_3g (status,cell_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBaseCell;
                    String insert_sql3 = "INSERT INTO counter_3g (status,time,cell_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBackUpCell;
                    DBUtil dbUtil1 = new DBUtil();
                    dbUtil1.execOther(insert_sql1, new Object[]{});
                    dbUtil1.execOther(insert_sql2, new Object[]{});
                    dbUtil1.execOther(insert_sql3, new Object[]{});
                    dbUtil1.closeConnection();
                    z = 0;
                    parmCell = "";
                    parmBaseCell = "";
                    parmBackUpCell = "";
                }
                z++;
            }
            String insert_sql1 = "INSERT INTO temp_3g (rnc_name,cell_name,time,pm_Tot_No_Rrc_Connect_Req_Success,pm_Tot_No_Rrc_Connect_Req,pm_No_Load_Sharing_Rrc_Conn,pm_Tot_No_Rrc_Connect_Req_Ps_Succ,pm_Tot_No_Rrc_Connect_Req_Ps,pm_No_Load_Sharing_Rrc_Conn_Ps,pm_Tot_No_Rrc_Connect_Req_Cs_Succ,pm_Tot_No_Rrc_Connect_Req_Cs,pm_No_Load_Sharing_Rrc_Conn_Cs,pm_No_Rab_Establish_Success_Speech,pm_No_Rab_Establish_Attempt_Speech,pm_No_Rab_Establish_Success_Packet_Interactive,pm_No_Rab_Establish_Attempt_Packet_Interactive,pm_No_Times_Rl_Add_To_Act_Set,pm_No_Times_Cell_Fail_Add_To_Act_Set,pm_No_System_Rab_Release_Speech,pm_No_Normal_Rab_Release_Speech,pm_No_System_Rab_Release_Packet,pm_No_System_Rab_Release_Packet_Ura,pm_Ch_Switch_Attempt_Fach_Ura,pm_Ch_Switch_Succ_Fach_Ura,pm_Ch_Switch_Attempt_Dch_Ura,pm_Ch_Switch_Succ_Dch_Ura,pm_Ch_Switch_Attempt_Hs_Ura,pm_Ch_Switch_Succ_Hs_Ura,pm_No_Normal_Rab_Release_Packet,pm_No_Normal_Rab_Release_Packet_Ura) VALUES" + parmCell;
            String insert_sql2 = "INSERT INTO base_cell_3g (status,cell_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBaseCell;
            String insert_sql3 = "INSERT INTO counter_3g (status,time,cell_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBackUpCell;
            String insert_sql4 = "INSERT INTO temp_3g (rnc_name,time,pm_No_Iu_Sig_Establish_Success_Cs,pm_No_Iu_Sig_Establish_Attempt_Cs,pm_No_Iu_Sig_Establish_Success_Ps,pm_No_Iu_Sig_Establish_Attempt_Ps) VALUES" + parmRnc;
            String insert_sql5 = "INSERT INTO temp_3g (rnc_name,iur_link_name,time,pm_No_System_Rab_Release_Speech,pm_No_Normal_Rab_Release_Speech,pm_No_System_Rab_Release_Packet,pm_No_Normal_Rab_Release_Packet) VALUES" + parmIurLink;
            DBUtil dbUtil1 = new DBUtil();
            dbUtil1.execOther(insert_sql1, new Object[]{});
            dbUtil1.execOther(insert_sql2, new Object[]{});
            dbUtil1.execOther(insert_sql3, new Object[]{});
            dbUtil1.execOther(insert_sql4, new Object[]{});
            dbUtil1.execOther(insert_sql5, new Object[]{});
            dbUtil1.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void calculateNode3g(){
        List<BaseQuota> baseQuotaList = XmlUtil.getQuota("quota_threshold_node_3g");
        try {
            DBUtil dbUtil = new DBUtil();

            String sql = "SELECT " +
                    "b.time AS time," +
                    "LEFT (b.cell_name, 8) AS node_name," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Success" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Success," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req" +
                    ") AS pm_Tot_No_Rrc_Connect_Req," +
                    "SUM(" +
                    "b.pm_No_Load_Sharing_Rrc_Conn" +
                    ") AS pm_No_Load_Sharing_Rrc_Conn," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Ps_Succ" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Ps_Succ," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Ps" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Ps," +
                    "SUM(" +
                    "b.pm_No_Load_Sharing_Rrc_Conn_Ps" +
                    ") AS pm_No_Load_Sharing_Rrc_Conn_Ps," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Cs_Succ" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Cs_Succ," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Cs" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Cs," +
                    "SUM(" +
                    "b.pm_No_Load_Sharing_Rrc_Conn_Cs" +
                    ") AS pm_No_Load_Sharing_Rrc_Conn_Cs," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Success_Speech" +
                    ") AS pm_No_Rab_Establish_Success_Speech," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Attempt_Speech" +
                    ") AS pm_No_Rab_Establish_Attempt_Speech," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Success_Packet_Interactive" +
                    ") AS pm_No_Rab_Establish_Success_Packet_Interactive," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Attempt_Packet_Interactive" +
                    ") AS pm_No_Rab_Establish_Attempt_Packet_Interactive," +
                    "SUM(" +
                    "b.pm_No_Times_Rl_Add_To_Act_Set" +
                    ") AS pm_No_Times_Rl_Add_To_Act_Set," +
                    "SUM(" +
                    "b.pm_No_Times_Cell_Fail_Add_To_Act_Set" +
                    ") AS pm_No_Times_Cell_Fail_Add_To_Act_Set," +
                    "SUM(" +
                    "b.pm_No_System_Rab_Release_Speech" +
                    ") AS pm_No_System_Rab_Release_Speech," +
                    "SUM(" +
                    "b.pm_No_Normal_Rab_Release_Speech" +
                    ") AS pm_No_Normal_Rab_Release_Speech," +
                    "SUM(" +
                    "b.pm_No_System_Rab_Release_Packet" +
                    ") AS pm_No_System_Rab_Release_Packet," +
                    "SUM(" +
                    "b.pm_No_System_Rab_Release_Packet_Ura" +
                    ") AS pm_No_System_Rab_Release_Packet_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Attempt_Fach_Ura" +
                    ") AS pm_Ch_Switch_Attempt_Fach_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Succ_Fach_Ura" +
                    ") AS pm_Ch_Switch_Succ_Fach_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Attempt_Dch_Ura" +
                    ") AS pm_Ch_Switch_Attempt_Dch_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Succ_Dch_Ura" +
                    ") AS pm_Ch_Switch_Succ_Dch_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Attempt_Hs_Ura" +
                    ") AS pm_Ch_Switch_Attempt_Hs_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Succ_Hs_Ura" +
                    ") AS pm_Ch_Switch_Succ_Hs_Ura," +
                    "SUM(" +
                    "b.pm_No_Normal_Rab_Release_Packet" +
                    ") AS pm_No_Normal_Rab_Release_Packet," +
                    "SUM(" +
                    "b.pm_No_Normal_Rab_Release_Packet_Ura" +
                    ") AS pm_No_Normal_Rab_Release_Packet_Ura" +
                    " FROM" +
                    " temp_3g b" +
                    " WHERE" +
                    " b.cell_name IS NOT NULL" +
                    " GROUP BY" +
                    " b.time,LEFT (b.cell_name, 8)";
            ResultSet rs  = dbUtil.execQuery(sql,  new Object[]{});
            int k = 1;
            String parmBaseNode = "";
            String parmBackUpNode = "";
            while (rs.next()) {
                String time = rs.getString("time");
                String node_name = rs.getString("node_name");
                float pm_Tot_No_Rrc_Connect_Req_Success = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Success"));
                float pm_Tot_No_Rrc_Connect_Req = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req"));
                float pm_No_Load_Sharing_Rrc_Conn = Float.parseFloat(rs.getString("pm_No_Load_Sharing_Rrc_Conn"));
                float pm_Tot_No_Rrc_Connect_Req_Ps_Succ = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Ps_Succ"));
                float pm_Tot_No_Rrc_Connect_Req_Ps = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Ps"));
                float pm_No_Load_Sharing_Rrc_Conn_Ps = Float.parseFloat(rs.getString("pm_No_Load_Sharing_Rrc_Conn_Ps"));
                float pm_Tot_No_Rrc_Connect_Req_Cs_Succ = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Cs_Succ"));
                float pm_Tot_No_Rrc_Connect_Req_Cs = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Cs"));
                float pm_No_Load_Sharing_Rrc_Conn_Cs = Float.parseFloat(rs.getString("pm_No_Load_Sharing_Rrc_Conn_Cs"));
                float pm_No_Rab_Establish_Success_Speech = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Success_Speech"));
                float pm_No_Rab_Establish_Attempt_Speech = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Attempt_Speech"));
                float pm_No_Rab_Establish_Success_Packet_Interactive = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Success_Packet_Interactive"));
                float pm_No_Rab_Establish_Attempt_Packet_Interactive = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Attempt_Packet_Interactive"));
                float pm_No_Times_Rl_Add_To_Act_Set = Float.parseFloat(rs.getString("pm_No_Times_Rl_Add_To_Act_Set"));
                float pm_No_Times_Cell_Fail_Add_To_Act_Set = Float.parseFloat(rs.getString("pm_No_Times_Cell_Fail_Add_To_Act_Set"));
                float pm_No_System_Rab_Release_Speech = Float.parseFloat(rs.getString("pm_No_System_Rab_Release_Speech"));
                float pm_No_Normal_Rab_Release_Speech = Float.parseFloat(rs.getString("pm_No_Normal_Rab_Release_Speech"));
                float pm_No_System_Rab_Release_Packet = Float.parseFloat(rs.getString("pm_No_System_Rab_Release_Packet"));
                float pm_No_System_Rab_Release_Packet_Ura = Float.parseFloat(rs.getString("pm_No_System_Rab_Release_Packet_Ura"));
                float pm_Ch_Switch_Attempt_Fach_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Attempt_Fach_Ura"));
                float pm_Ch_Switch_Succ_Fach_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Succ_Fach_Ura"));
                float pm_Ch_Switch_Attempt_Dch_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Attempt_Dch_Ura"));
                float pm_Ch_Switch_Succ_Dch_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Succ_Dch_Ura"));
                float pm_Ch_Switch_Attempt_Hs_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Attempt_Hs_Ura"));
                float pm_Ch_Switch_Succ_Hs_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Succ_Hs_Ura"));
                float pm_No_Normal_Rab_Release_Packet = Float.parseFloat(rs.getString("pm_No_Normal_Rab_Release_Packet"));
                float pm_No_Normal_Rab_Release_Packet_Ura = Float.parseFloat(rs.getString("pm_No_Normal_Rab_Release_Packet_Ura"));
                float  pmNoRejRrcConnSpFlowCtrl = 0;//目前这个counter是缺失的

                float Rrc_Suc;
                if ((pm_Tot_No_Rrc_Connect_Req - pm_No_Load_Sharing_Rrc_Conn + pmNoRejRrcConnSpFlowCtrl) == 0) {
                    Rrc_Suc = 0;
                } else {
                    Rrc_Suc = (100 * pm_Tot_No_Rrc_Connect_Req_Success / (pm_Tot_No_Rrc_Connect_Req - pm_No_Load_Sharing_Rrc_Conn + pmNoRejRrcConnSpFlowCtrl));
                }
                float PS_Rrc_Suc_Rate;
                if ((pm_Tot_No_Rrc_Connect_Req_Ps - pm_No_Load_Sharing_Rrc_Conn_Ps) == 0) {
                    PS_Rrc_Suc_Rate = 0;
                } else {
                    PS_Rrc_Suc_Rate = (100 * pm_Tot_No_Rrc_Connect_Req_Ps_Succ / (pm_Tot_No_Rrc_Connect_Req_Ps - pm_No_Load_Sharing_Rrc_Conn_Ps));
                }
                float Spch_Rrc_Suc_Rate;
                if ((pm_Tot_No_Rrc_Connect_Req_Cs - pm_No_Load_Sharing_Rrc_Conn_Cs) == 0) {
                    Spch_Rrc_Suc_Rate = 0;
                } else {
                    Spch_Rrc_Suc_Rate = (100 * pm_Tot_No_Rrc_Connect_Req_Cs_Succ / (pm_Tot_No_Rrc_Connect_Req_Cs - pm_No_Load_Sharing_Rrc_Conn_Cs));
                }
                float Spch_Rab_Suc;
                if (pm_No_Rab_Establish_Attempt_Speech == 0) {
                    Spch_Rab_Suc = 0;
                } else {
                    Spch_Rab_Suc = (100 * pm_No_Rab_Establish_Success_Speech / pm_No_Rab_Establish_Attempt_Speech);
                }
                float PS_Rab_Succ;
                if (pm_No_Rab_Establish_Attempt_Packet_Interactive == 0) {
                    PS_Rab_Succ = 0;
                } else {
                    PS_Rab_Succ = (100 * pm_No_Rab_Establish_Success_Packet_Interactive / pm_No_Rab_Establish_Attempt_Packet_Interactive);
                }
                float SHO_Success;
                if ((pm_No_Times_Rl_Add_To_Act_Set + pm_No_Times_Cell_Fail_Add_To_Act_Set) == 0) {
                    SHO_Success = 0;
                } else {
                    SHO_Success = (100 * pm_No_Times_Rl_Add_To_Act_Set / (pm_No_Times_Rl_Add_To_Act_Set + pm_No_Times_Cell_Fail_Add_To_Act_Set));
                }
                float Spch_Drop;
                if ((pm_No_Normal_Rab_Release_Speech + pm_No_System_Rab_Release_Speech) == 0) {
                    Spch_Drop = 0;
                } else {
                    Spch_Drop = (100 * pm_No_System_Rab_Release_Speech / (pm_No_Normal_Rab_Release_Speech + pm_No_System_Rab_Release_Speech));
                }
                float PS_Drop;
                if ((pm_No_Normal_Rab_Release_Packet - pm_No_Normal_Rab_Release_Packet_Ura + pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura + pm_Ch_Switch_Succ_Fach_Ura + pm_Ch_Switch_Succ_Dch_Ura + pm_Ch_Switch_Succ_Hs_Ura) == 0) {
                    PS_Drop = 0;
                } else {
                    PS_Drop = (100 * (pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura - pm_Ch_Switch_Attempt_Fach_Ura + pm_Ch_Switch_Succ_Fach_Ura - pm_Ch_Switch_Attempt_Dch_Ura + pm_Ch_Switch_Succ_Dch_Ura - pm_Ch_Switch_Attempt_Hs_Ura + pm_Ch_Switch_Succ_Hs_Ura) / (pm_No_Normal_Rab_Release_Packet - pm_No_Normal_Rab_Release_Packet_Ura + pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura + pm_Ch_Switch_Succ_Fach_Ura + pm_Ch_Switch_Succ_Dch_Ura + pm_Ch_Switch_Succ_Hs_Ura));
                }
                Integer status = modStatus(baseQuotaList, 0, 0, Rrc_Suc, PS_Rrc_Suc_Rate, Spch_Rrc_Suc_Rate, Spch_Rab_Suc, PS_Rab_Succ, SHO_Success, Spch_Drop, PS_Drop);
                String strBackUpNode = "( '" + status + "', '" +
                        time + "', '" +
                        node_name + "', '" +
                        Rrc_Suc + "', '" +
                        PS_Rrc_Suc_Rate + "', '" +
                        Spch_Rrc_Suc_Rate + "', '" +
                        Spch_Rab_Suc + "', '" +
                        PS_Rab_Succ + "', '" +
                        SHO_Success + "', '" +
                        Spch_Drop + "', '" +
                        PS_Drop + "' )";

                String strBaseNode = "( '" + status + "', '" +
                        node_name + "', '" +
                        Rrc_Suc + "', '" +
                        PS_Rrc_Suc_Rate + "', '" +
                        Spch_Rrc_Suc_Rate + "', '" +
                        Spch_Rab_Suc + "', '" +
                        PS_Rab_Succ + "', '" +
                        SHO_Success + "', '" +
                        Spch_Drop + "', '" +
                        PS_Drop + "' )";
                if (k < 500){
                    if (parmBaseNode == ""){
                        parmBaseNode = strBaseNode;
                    }else {
                        parmBaseNode = parmBaseNode + "," + strBaseNode;
                    }
                    if (parmBackUpNode == ""){
                        parmBackUpNode = strBackUpNode;
                    }else {
                        parmBackUpNode = parmBackUpNode + "," + strBackUpNode;
                    }
                }else {
                    parmBaseNode = parmBaseNode + "," + strBaseNode;
                    parmBackUpNode = parmBackUpNode + "," + strBackUpNode;
                    String insert_sql1 = "INSERT INTO base_node_3g (status,node_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBaseNode;
                    String insert_sql2 = "INSERT INTO node_3g (status,time,node_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBackUpNode;
                    DBUtil dbUtil1 = new DBUtil();
                    dbUtil1.execOther(insert_sql1, new Object[]{});
                    dbUtil1.execOther(insert_sql2, new Object[]{});
                    dbUtil1.closeConnection();
                    k = 0;
                    parmBaseNode = "";
                    parmBackUpNode = "";
                }
                k++;
            }
            String insert_sql1 = "INSERT INTO base_node_3g (status,node_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBaseNode;
            String insert_sql2 = "INSERT INTO node_3g (status,time,node_name,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBackUpNode;
            DBUtil dbUtil1 = new DBUtil();
            dbUtil1.execOther(insert_sql1, new Object[]{});
            dbUtil1.execOther(insert_sql2, new Object[]{});
            dbUtil1.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void calculateRnc3g(){
        List<BaseQuota> baseQuotaList = XmlUtil.getQuota("quota_threshold_rnc_3g");
        try {
            DBUtil dbUtil = new DBUtil();

            String sql = "SELECT " +
                    "b.time AS time," +
                    "b.rnc_name AS rnc_name," +
                    "SUM(" +
                    "b.pm_No_Iu_Sig_Establish_Success_Cs" +
                    ") AS pm_No_Iu_Sig_Establish_Success_Cs," +
                    "SUM(" +
                    "b.pm_No_Iu_Sig_Establish_Attempt_Cs" +
                    ") AS pm_No_Iu_Sig_Establish_Attempt_Cs," +
                    "SUM(" +
                    "b.pm_No_Iu_Sig_Establish_Success_Ps" +
                    ") AS pm_No_Iu_Sig_Establish_Success_Ps," +
                    "SUM(" +
                    "b.pm_No_Iu_Sig_Establish_Attempt_Ps" +
                    ") AS pm_No_Iu_Sig_Establish_Attempt_Ps,"+
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Success" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Success," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req" +
                    ") AS pm_Tot_No_Rrc_Connect_Req," +
                    "SUM(" +
                    "b.pm_No_Load_Sharing_Rrc_Conn" +
                    ") AS pm_No_Load_Sharing_Rrc_Conn," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Ps_Succ" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Ps_Succ," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Ps" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Ps," +
                    "SUM(" +
                    "b.pm_No_Load_Sharing_Rrc_Conn_Ps" +
                    ") AS pm_No_Load_Sharing_Rrc_Conn_Ps," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Cs_Succ" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Cs_Succ," +
                    "SUM(" +
                    "b.pm_Tot_No_Rrc_Connect_Req_Cs" +
                    ") AS pm_Tot_No_Rrc_Connect_Req_Cs," +
                    "SUM(" +
                    "b.pm_No_Load_Sharing_Rrc_Conn_Cs" +
                    ") AS pm_No_Load_Sharing_Rrc_Conn_Cs," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Success_Speech" +
                    ") AS pm_No_Rab_Establish_Success_Speech," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Attempt_Speech" +
                    ") AS pm_No_Rab_Establish_Attempt_Speech," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Success_Packet_Interactive" +
                    ") AS pm_No_Rab_Establish_Success_Packet_Interactive," +
                    "SUM(" +
                    "b.pm_No_Rab_Establish_Attempt_Packet_Interactive" +
                    ") AS pm_No_Rab_Establish_Attempt_Packet_Interactive," +
                    "SUM(" +
                    "b.pm_No_Times_Rl_Add_To_Act_Set" +
                    ") AS pm_No_Times_Rl_Add_To_Act_Set," +
                    "SUM(" +
                    "b.pm_No_Times_Cell_Fail_Add_To_Act_Set" +
                    ") AS pm_No_Times_Cell_Fail_Add_To_Act_Set," +
                    "SUM(" +
                    "b.pm_No_System_Rab_Release_Speech" +
                    ") AS pm_No_System_Rab_Release_Speech," +
                    "SUM(" +
                    "b.pm_No_Normal_Rab_Release_Speech" +
                    ") AS pm_No_Normal_Rab_Release_Speech," +
                    "SUM(" +
                    "b.pm_No_System_Rab_Release_Packet" +
                    ") AS pm_No_System_Rab_Release_Packet," +
                    "SUM(" +
                    "b.pm_No_System_Rab_Release_Packet_Ura" +
                    ") AS pm_No_System_Rab_Release_Packet_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Attempt_Fach_Ura" +
                    ") AS pm_Ch_Switch_Attempt_Fach_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Succ_Fach_Ura" +
                    ") AS pm_Ch_Switch_Succ_Fach_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Attempt_Dch_Ura" +
                    ") AS pm_Ch_Switch_Attempt_Dch_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Succ_Dch_Ura" +
                    ") AS pm_Ch_Switch_Succ_Dch_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Attempt_Hs_Ura" +
                    ") AS pm_Ch_Switch_Attempt_Hs_Ura," +
                    "SUM(" +
                    "b.pm_Ch_Switch_Succ_Hs_Ura" +
                    ") AS pm_Ch_Switch_Succ_Hs_Ura," +
                    "SUM(" +
                    "b.pm_No_Normal_Rab_Release_Packet" +
                    ") AS pm_No_Normal_Rab_Release_Packet," +
                    "SUM(" +
                    "b.pm_No_Normal_Rab_Release_Packet_Ura" +
                    ") AS pm_No_Normal_Rab_Release_Packet_Ura" +
                    " FROM" +
                    " temp_3g b" +
                    " WHERE" +
                    " b.rnc_name IS NOT NULL" +
                    " GROUP BY" +
                    " b.time,b.rnc_name";
            ResultSet rs  = dbUtil.execQuery(sql,  new Object[]{});
            int k = 1;
            String parmBaseRnc = "";
            String parmBackUpRnc = "";
            while (rs.next()) {
                String time = rs.getString("time");
                String rnc_name = rs.getString("rnc_name");
                float pm_No_Iu_Sig_Establish_Success_Cs = Float.parseFloat(rs.getString("pm_No_Iu_Sig_Establish_Success_Cs"));
                float pm_No_Iu_Sig_Establish_Attempt_Cs = Float.parseFloat(rs.getString("pm_No_Iu_Sig_Establish_Attempt_Cs"));
                float pm_No_Iu_Sig_Establish_Success_Ps = Float.parseFloat(rs.getString("pm_No_Iu_Sig_Establish_Success_Ps"));
                float pm_No_Iu_Sig_Establish_Attempt_Ps = Float.parseFloat(rs.getString("pm_No_Iu_Sig_Establish_Attempt_Ps"));
                float pm_Tot_No_Rrc_Connect_Req_Success = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Success"));
                float pm_Tot_No_Rrc_Connect_Req = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req"));
                float pm_No_Load_Sharing_Rrc_Conn = Float.parseFloat(rs.getString("pm_No_Load_Sharing_Rrc_Conn"));
                float pm_Tot_No_Rrc_Connect_Req_Ps_Succ = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Ps_Succ"));
                float pm_Tot_No_Rrc_Connect_Req_Ps = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Ps"));
                float pm_No_Load_Sharing_Rrc_Conn_Ps = Float.parseFloat(rs.getString("pm_No_Load_Sharing_Rrc_Conn_Ps"));
                float pm_Tot_No_Rrc_Connect_Req_Cs_Succ = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Cs_Succ"));
                float pm_Tot_No_Rrc_Connect_Req_Cs = Float.parseFloat(rs.getString("pm_Tot_No_Rrc_Connect_Req_Cs"));
                float pm_No_Load_Sharing_Rrc_Conn_Cs = Float.parseFloat(rs.getString("pm_No_Load_Sharing_Rrc_Conn_Cs"));
                float pm_No_Rab_Establish_Success_Speech = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Success_Speech"));
                float pm_No_Rab_Establish_Attempt_Speech = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Attempt_Speech"));
                float pm_No_Rab_Establish_Success_Packet_Interactive = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Success_Packet_Interactive"));
                float pm_No_Rab_Establish_Attempt_Packet_Interactive = Float.parseFloat(rs.getString("pm_No_Rab_Establish_Attempt_Packet_Interactive"));
                float pm_No_Times_Rl_Add_To_Act_Set = Float.parseFloat(rs.getString("pm_No_Times_Rl_Add_To_Act_Set"));
                float pm_No_Times_Cell_Fail_Add_To_Act_Set = Float.parseFloat(rs.getString("pm_No_Times_Cell_Fail_Add_To_Act_Set"));
                float pm_No_System_Rab_Release_Speech = Float.parseFloat(rs.getString("pm_No_System_Rab_Release_Speech"));
                float pm_No_Normal_Rab_Release_Speech = Float.parseFloat(rs.getString("pm_No_Normal_Rab_Release_Speech"));
                float pm_No_System_Rab_Release_Packet = Float.parseFloat(rs.getString("pm_No_System_Rab_Release_Packet"));
                float pm_No_System_Rab_Release_Packet_Ura = Float.parseFloat(rs.getString("pm_No_System_Rab_Release_Packet_Ura"));
                float pm_Ch_Switch_Attempt_Fach_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Attempt_Fach_Ura"));
                float pm_Ch_Switch_Succ_Fach_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Succ_Fach_Ura"));
                float pm_Ch_Switch_Attempt_Dch_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Attempt_Dch_Ura"));
                float pm_Ch_Switch_Succ_Dch_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Succ_Dch_Ura"));
                float pm_Ch_Switch_Attempt_Hs_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Attempt_Hs_Ura"));
                float pm_Ch_Switch_Succ_Hs_Ura = Float.parseFloat(rs.getString("pm_Ch_Switch_Succ_Hs_Ura"));
                float pm_No_Normal_Rab_Release_Packet = Float.parseFloat(rs.getString("pm_No_Normal_Rab_Release_Packet"));
                float pm_No_Normal_Rab_Release_Packet_Ura = Float.parseFloat(rs.getString("pm_No_Normal_Rab_Release_Packet_Ura"));
                float  pmNoRejRrcConnSpFlowCtrl = 0;//目前这个counter是缺失的

                float CS_Iu_Sig_Suc;
                if (pm_No_Iu_Sig_Establish_Attempt_Cs == 0){
                    CS_Iu_Sig_Suc = 0;
                }else {
                    CS_Iu_Sig_Suc = (100 * pm_No_Iu_Sig_Establish_Success_Cs / pm_No_Iu_Sig_Establish_Attempt_Cs);
                }
                float PS_Iu_Sig_Suc;
                if (pm_No_Iu_Sig_Establish_Attempt_Ps == 0){
                    PS_Iu_Sig_Suc = 0;
                }else {
                    PS_Iu_Sig_Suc = (100 * pm_No_Iu_Sig_Establish_Success_Ps / pm_No_Iu_Sig_Establish_Attempt_Ps);
                }
                float Rrc_Suc;
                if ((pm_Tot_No_Rrc_Connect_Req - pm_No_Load_Sharing_Rrc_Conn + pmNoRejRrcConnSpFlowCtrl) == 0) {
                    Rrc_Suc = 0;
                } else {
                    Rrc_Suc = (100 * pm_Tot_No_Rrc_Connect_Req_Success / (pm_Tot_No_Rrc_Connect_Req - pm_No_Load_Sharing_Rrc_Conn + pmNoRejRrcConnSpFlowCtrl));
                }
                float PS_Rrc_Suc_Rate;
                if ((pm_Tot_No_Rrc_Connect_Req_Ps - pm_No_Load_Sharing_Rrc_Conn_Ps) == 0) {
                    PS_Rrc_Suc_Rate = 0;
                } else {
                    PS_Rrc_Suc_Rate = (100 * pm_Tot_No_Rrc_Connect_Req_Ps_Succ / (pm_Tot_No_Rrc_Connect_Req_Ps - pm_No_Load_Sharing_Rrc_Conn_Ps));
                }
                float Spch_Rrc_Suc_Rate;
                if ((pm_Tot_No_Rrc_Connect_Req_Cs - pm_No_Load_Sharing_Rrc_Conn_Cs) == 0) {
                    Spch_Rrc_Suc_Rate = 0;
                } else {
                    Spch_Rrc_Suc_Rate = (100 * pm_Tot_No_Rrc_Connect_Req_Cs_Succ / (pm_Tot_No_Rrc_Connect_Req_Cs - pm_No_Load_Sharing_Rrc_Conn_Cs));
                }
                float Spch_Rab_Suc;
                if (pm_No_Rab_Establish_Attempt_Speech == 0) {
                    Spch_Rab_Suc = 0;
                } else {
                    Spch_Rab_Suc = (100 * pm_No_Rab_Establish_Success_Speech / pm_No_Rab_Establish_Attempt_Speech);
                }
                float PS_Rab_Succ;
                if (pm_No_Rab_Establish_Attempt_Packet_Interactive == 0) {
                    PS_Rab_Succ = 0;
                } else {
                    PS_Rab_Succ = (100 * pm_No_Rab_Establish_Success_Packet_Interactive / pm_No_Rab_Establish_Attempt_Packet_Interactive);
                }
                float SHO_Success;
                if ((pm_No_Times_Rl_Add_To_Act_Set + pm_No_Times_Cell_Fail_Add_To_Act_Set) == 0) {
                    SHO_Success = 0;
                } else {
                    SHO_Success = (100 * pm_No_Times_Rl_Add_To_Act_Set / (pm_No_Times_Rl_Add_To_Act_Set + pm_No_Times_Cell_Fail_Add_To_Act_Set));
                }
                float Spch_Drop;
                if ((pm_No_Normal_Rab_Release_Speech + pm_No_System_Rab_Release_Speech) == 0) {
                    Spch_Drop = 0;
                } else {
                    Spch_Drop = (100 * pm_No_System_Rab_Release_Speech / (pm_No_Normal_Rab_Release_Speech + pm_No_System_Rab_Release_Speech));
                }
                float PS_Drop;
                if ((pm_No_Normal_Rab_Release_Packet - pm_No_Normal_Rab_Release_Packet_Ura + pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura + pm_Ch_Switch_Succ_Fach_Ura + pm_Ch_Switch_Succ_Dch_Ura + pm_Ch_Switch_Succ_Hs_Ura) == 0) {
                    PS_Drop = 0;
                } else {
                    PS_Drop = (100 * (pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura - pm_Ch_Switch_Attempt_Fach_Ura + pm_Ch_Switch_Succ_Fach_Ura - pm_Ch_Switch_Attempt_Dch_Ura + pm_Ch_Switch_Succ_Dch_Ura - pm_Ch_Switch_Attempt_Hs_Ura + pm_Ch_Switch_Succ_Hs_Ura) / (pm_No_Normal_Rab_Release_Packet - pm_No_Normal_Rab_Release_Packet_Ura + pm_No_System_Rab_Release_Packet - pm_No_System_Rab_Release_Packet_Ura + pm_Ch_Switch_Succ_Fach_Ura + pm_Ch_Switch_Succ_Dch_Ura + pm_Ch_Switch_Succ_Hs_Ura));
                }
                Integer status = modStatus(baseQuotaList, CS_Iu_Sig_Suc, PS_Iu_Sig_Suc, Rrc_Suc, PS_Rrc_Suc_Rate, Spch_Rrc_Suc_Rate, Spch_Rab_Suc, PS_Rab_Succ, SHO_Success, Spch_Drop, PS_Drop);
                String strBackUpRnc = "( '" + status + "', '" +
                        time + "', '" +
                        rnc_name + "', '" +
                        CS_Iu_Sig_Suc + "', '" +
                        PS_Iu_Sig_Suc + "', '" +
                        Rrc_Suc + "', '" +
                        PS_Rrc_Suc_Rate + "', '" +
                        Spch_Rrc_Suc_Rate + "', '" +
                        Spch_Rab_Suc + "', '" +
                        PS_Rab_Succ + "', '" +
                        SHO_Success + "', '" +
                        Spch_Drop + "', '" +
                        PS_Drop + "' )";

                String strBaseRnc = "( '" + status + "', '" +
                        rnc_name + "', '" +
                        CS_Iu_Sig_Suc + "', '" +
                        PS_Iu_Sig_Suc + "', '" +
                        Rrc_Suc + "', '" +
                        PS_Rrc_Suc_Rate + "', '" +
                        Spch_Rrc_Suc_Rate + "', '" +
                        Spch_Rab_Suc + "', '" +
                        PS_Rab_Succ + "', '" +
                        SHO_Success + "', '" +
                        Spch_Drop + "', '" +
                        PS_Drop + "' )";
                if (k < 500){
                    if (parmBaseRnc == ""){
                        parmBaseRnc = strBaseRnc;
                    }else {
                        parmBaseRnc = parmBaseRnc + "," + strBaseRnc;
                    }
                    if (parmBackUpRnc == ""){
                        parmBackUpRnc = strBackUpRnc;
                    }else {
                        parmBackUpRnc = parmBackUpRnc + "," + strBackUpRnc;
                    }
                }else {
                    parmBaseRnc = parmBaseRnc + "," + strBaseRnc;
                    parmBackUpRnc = parmBackUpRnc + "," + strBackUpRnc;
                    String insert_sql1 = "INSERT INTO base_rnc_3g (status,rnc,CS_Iu_Sig_Suc,PS_Iu_Sig_Suc,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBaseRnc;
                    String insert_sql2 = "INSERT INTO rnc_3g (status,time,rnc,CS_Iu_Sig_Suc,PS_Iu_Sig_Suc,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBackUpRnc;
                    DBUtil dbUtil1 = new DBUtil();
                    dbUtil1.execOther(insert_sql1, new Object[]{});
                    dbUtil1.execOther(insert_sql2, new Object[]{});
                    dbUtil1.closeConnection();
                    k = 0;
                    parmBaseRnc = "";
                    parmBackUpRnc = "";
                }
                k++;
            }
            String insert_sql1 = "INSERT INTO base_rnc_3g (status,rnc,CS_Iu_Sig_Suc,PS_Iu_Sig_Suc,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBaseRnc;
            String insert_sql2 = "INSERT INTO rnc_3g (status,time,rnc,CS_Iu_Sig_Suc,PS_Iu_Sig_Suc,Rrc_Suc,PS_Rrc_Suc_Rate,Spch_Rrc_Suc_Rate,Spch_Rab_Suc,PS_Rab_Succ,SHO_Success,Spch_Drop,PS_Drop) VALUES" + parmBackUpRnc;
            DBUtil dbUtil1 = new DBUtil();
            dbUtil1.execOther(insert_sql1, new Object[]{});
            dbUtil1.execOther(insert_sql2, new Object[]{});
            dbUtil1.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<BaseQuota> getQuota(String quotaName) {
        DBUtil dbUtil = new DBUtil();
        try {

            ResultSet rs= dbUtil.execQuery("SELECT * FROM "+quotaName, new Object[]{});
            List<BaseQuota> baseQuotaList = new ArrayList<BaseQuota>();
            while (rs.next()){
                if (quotaName.equals("quota_threshold_cell_3g")){
                    QuotaThresholdCell3g quotaThresholdCell3g = new QuotaThresholdCell3g();
                    quotaThresholdCell3g.setQuotaName(rs.getString("quota_name"));
                    quotaThresholdCell3g.setQuotaType(rs.getInt("quota_type"));
                    quotaThresholdCell3g.setQuotaUnit(rs.getInt("quota_unit"));
                    quotaThresholdCell3g.setThreshold1(rs.getString("threshold_1"));
                    quotaThresholdCell3g.setThreshold2(rs.getString("threshold_2"));
                    quotaThresholdCell3g.setThreshold3(rs.getString("threshold_3"));
                    baseQuotaList.add(quotaThresholdCell3g);
                }else if (quotaName.equals("quota_threshold_node_3g")){
                    QuotaThresholdNode3g quotaThresholdNode3g = new QuotaThresholdNode3g();
                    quotaThresholdNode3g.setQuotaName(rs.getString("quota_name"));
                    quotaThresholdNode3g.setQuotaType(rs.getInt("quota_type"));
                    quotaThresholdNode3g.setQuotaUnit(rs.getInt("quota_unit"));
                    quotaThresholdNode3g.setThreshold1(rs.getString("threshold_1"));
                    quotaThresholdNode3g.setThreshold2(rs.getString("threshold_2"));
                    quotaThresholdNode3g.setThreshold3(rs.getString("threshold_3"));
                    baseQuotaList.add(quotaThresholdNode3g);
                }else if (quotaName.equals("quota_threshold_rnc_3g")){
                    QuotaThresholdRnc3g quotaThresholdRnc3g = new QuotaThresholdRnc3g();
                    quotaThresholdRnc3g.setQuotaName(rs.getString("quota_name"));
                    quotaThresholdRnc3g.setQuotaType(rs.getInt("quota_type"));
                    quotaThresholdRnc3g.setQuotaUnit(rs.getInt("quota_unit"));
                    quotaThresholdRnc3g.setThreshold1(rs.getString("threshold_1"));
                    quotaThresholdRnc3g.setThreshold2(rs.getString("threshold_2"));
                    quotaThresholdRnc3g.setThreshold3(rs.getString("threshold_3"));
                    baseQuotaList.add(quotaThresholdRnc3g);
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
                                                  float CS_Iu_Sig_Suc,float PS_Iu_Sig_Suc,float Rrc_Suc, float PS_Rrc_Suc_Rate,float Spch_Rrc_Suc_Rate,
                                                  float Spch_Rab_Suc,float PS_Rab_Succ,float SHO_Success, float Spch_Drop,float PS_Drop) {
        List<Integer> statusList = new ArrayList<Integer>();
        for (int m = 0; m < baseQuotaList.size(); m++){
            BaseQuota baseQuota = baseQuotaList.get(m);
            String baseName = baseQuota.getQuotaName();
            if (baseName.equals("CS_Iu_Sig_Suc")){
                statusList.add(compare(baseQuota,CS_Iu_Sig_Suc));
            }else if (baseName.equals("PS_Iu_Sig_Suc")){
                statusList.add(compare(baseQuota,PS_Iu_Sig_Suc));
            }else if (baseName.equals("Rrc_Suc")){
                statusList.add(compare(baseQuota,Rrc_Suc));
            }else if (baseName.equals("PS_Rrc_Suc_Rate")){
                statusList.add(compare(baseQuota,PS_Rrc_Suc_Rate));
            }else if (baseName.equals("Spch_Rrc_Suc_Rate")){
                statusList.add(compare(baseQuota,Spch_Rrc_Suc_Rate));
            }else if (baseName.equals("Spch_Rab_Suc")){
                statusList.add(compare(baseQuota,Spch_Rab_Suc));
            }else if (baseName.equals("PS_Rab_Succ")){
                statusList.add(compare(baseQuota,PS_Rab_Succ));
            }else if (baseName.equals("SHO_Success")){
                statusList.add(compare(baseQuota,SHO_Success));
            }else if (baseName.equals("Spch_Drop")){
                statusList.add(compare(baseQuota,Spch_Drop));
            }else if (baseName.equals("PS_Drop")) {
                statusList.add(compare(baseQuota, PS_Drop));
            }
        }
        Integer status = Collections.max(statusList);
        return status;
    }

    public static String getPath() {
        String path = ThreadUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try{
            path = java.net.URLDecoder.decode(path, "UTF-8");//转换处理中文及空格
        }catch (UnsupportedEncodingException e){
            return null;
        }
        return new File(path).getParent();
    }

    public static void main(final String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("解析开始");
        try {
            //        String path     = getPath();
//        String filename = "ECR89.xml";
//        String filePath = (new StringBuilder(String.valueOf(path))).append("/3g/").append(filename).toString();

//            File xmlFile = new File("D:\\ECR89.xml");// 根据指定的路径创建file对象
             /*----------------------------------*/
            /*解析xm文件应与处理3gcell数据一起操作，采用多线程方式，待所有xml文件处理完毕后，在以单线程方式处理node和rnc数据*/
            XmlUtil.getXmlRootAndCalculateCell3g("D:\\ECR89.xml");
            long cell3gTime = System.currentTimeMillis();
            System.out.println("解析xml并处理3gcell数据完毕，共用"+(cell3gTime-startTime)+"毫秒");
            /*----------------------------------*/
            calculateNode3g();
            long node3gTime = System.currentTimeMillis();
            System.out.println("处理3gnode数据完毕，共用"+(node3gTime-cell3gTime)+"毫秒");
            calculateRnc3g();
            long rnc3gTime = System.currentTimeMillis();
            System.out.println("处理3grnc数据完毕，共用"+(rnc3gTime-node3gTime)+"毫秒");
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
