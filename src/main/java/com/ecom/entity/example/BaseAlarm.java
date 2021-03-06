package com.ecom.entity.example;

import java.io.Serializable;

public class BaseAlarm implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_alarm.node_name
     *
     * @mbggenerated
     */
    private String nodeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_alarm.mo
     *
     * @mbggenerated
     */
    private String mo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_alarm.alarm_time
     *
     * @mbggenerated
     */
    private String alarmTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_alarm.alarm_name
     *
     * @mbggenerated
     */
    private String alarmName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_alarm.alarm_type
     *
     * @mbggenerated
     */
    private String alarmType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table base_alarm
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_alarm.node_name
     *
     * @return the value of base_alarm.node_name
     *
     * @mbggenerated
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_alarm.node_name
     *
     * @param nodeName the value for base_alarm.node_name
     *
     * @mbggenerated
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_alarm.mo
     *
     * @return the value of base_alarm.mo
     *
     * @mbggenerated
     */
    public String getMo() {
        return mo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_alarm.mo
     *
     * @param mo the value for base_alarm.mo
     *
     * @mbggenerated
     */
    public void setMo(String mo) {
        this.mo = mo == null ? null : mo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_alarm.alarm_time
     *
     * @return the value of base_alarm.alarm_time
     *
     * @mbggenerated
     */
    public String getAlarmTime() {
        return alarmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_alarm.alarm_time
     *
     * @param alarmTime the value for base_alarm.alarm_time
     *
     * @mbggenerated
     */
    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime == null ? null : alarmTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_alarm.alarm_name
     *
     * @return the value of base_alarm.alarm_name
     *
     * @mbggenerated
     */
    public String getAlarmName() {
        return alarmName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_alarm.alarm_name
     *
     * @param alarmName the value for base_alarm.alarm_name
     *
     * @mbggenerated
     */
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_alarm.alarm_type
     *
     * @return the value of base_alarm.alarm_type
     *
     * @mbggenerated
     */
    public String getAlarmType() {
        return alarmType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_alarm.alarm_type
     *
     * @param alarmType the value for base_alarm.alarm_type
     *
     * @mbggenerated
     */
    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }
}