package com.ecom.entity.example;

import java.io.Serializable;

public class DicAlarmName implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_alarm_name.alarm_name
     *
     * @mbggenerated
     */
    private String alarmName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_alarm_name.alarm_name_dis
     *
     * @mbggenerated
     */
    private String alarmNameDis;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dic_alarm_name
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_alarm_name.alarm_name
     *
     * @return the value of dic_alarm_name.alarm_name
     *
     * @mbggenerated
     */
    public String getAlarmName() {
        return alarmName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_alarm_name.alarm_name
     *
     * @param alarmName the value for dic_alarm_name.alarm_name
     *
     * @mbggenerated
     */
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_alarm_name.alarm_name_dis
     *
     * @return the value of dic_alarm_name.alarm_name_dis
     *
     * @mbggenerated
     */
    public String getAlarmNameDis() {
        return alarmNameDis;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_alarm_name.alarm_name_dis
     *
     * @param alarmNameDis the value for dic_alarm_name.alarm_name_dis
     *
     * @mbggenerated
     */
    public void setAlarmNameDis(String alarmNameDis) {
        this.alarmNameDis = alarmNameDis == null ? null : alarmNameDis.trim();
    }
}