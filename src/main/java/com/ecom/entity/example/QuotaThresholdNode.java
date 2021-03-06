package com.ecom.entity.example;

import java.io.Serializable;

public class QuotaThresholdNode implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quota_threshold_node.quota_name
     *
     * @mbggenerated
     */
    private String quotaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quota_threshold_node.quota_type
     *
     * @mbggenerated
     */
    private Integer quotaType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quota_threshold_node.threshold_1
     *
     * @mbggenerated
     */
    private String threshold1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quota_threshold_node.threshold_2
     *
     * @mbggenerated
     */
    private String threshold2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quota_threshold_node.threshold_3
     *
     * @mbggenerated
     */
    private String threshold3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column quota_threshold_node.quota_unit
     *
     * @mbggenerated
     */
    private Integer quotaUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table quota_threshold_node
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quota_threshold_node.quota_name
     *
     * @return the value of quota_threshold_node.quota_name
     *
     * @mbggenerated
     */
    public String getQuotaName() {
        return quotaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quota_threshold_node.quota_name
     *
     * @param quotaName the value for quota_threshold_node.quota_name
     *
     * @mbggenerated
     */
    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName == null ? null : quotaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quota_threshold_node.quota_type
     *
     * @return the value of quota_threshold_node.quota_type
     *
     * @mbggenerated
     */
    public Integer getQuotaType() {
        return quotaType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quota_threshold_node.quota_type
     *
     * @param quotaType the value for quota_threshold_node.quota_type
     *
     * @mbggenerated
     */
    public void setQuotaType(Integer quotaType) {
        this.quotaType = quotaType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quota_threshold_node.threshold_1
     *
     * @return the value of quota_threshold_node.threshold_1
     *
     * @mbggenerated
     */
    public String getThreshold1() {
        return threshold1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quota_threshold_node.threshold_1
     *
     * @param threshold1 the value for quota_threshold_node.threshold_1
     *
     * @mbggenerated
     */
    public void setThreshold1(String threshold1) {
        this.threshold1 = threshold1 == null ? null : threshold1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quota_threshold_node.threshold_2
     *
     * @return the value of quota_threshold_node.threshold_2
     *
     * @mbggenerated
     */
    public String getThreshold2() {
        return threshold2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quota_threshold_node.threshold_2
     *
     * @param threshold2 the value for quota_threshold_node.threshold_2
     *
     * @mbggenerated
     */
    public void setThreshold2(String threshold2) {
        this.threshold2 = threshold2 == null ? null : threshold2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quota_threshold_node.threshold_3
     *
     * @return the value of quota_threshold_node.threshold_3
     *
     * @mbggenerated
     */
    public String getThreshold3() {
        return threshold3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quota_threshold_node.threshold_3
     *
     * @param threshold3 the value for quota_threshold_node.threshold_3
     *
     * @mbggenerated
     */
    public void setThreshold3(String threshold3) {
        this.threshold3 = threshold3 == null ? null : threshold3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column quota_threshold_node.quota_unit
     *
     * @return the value of quota_threshold_node.quota_unit
     *
     * @mbggenerated
     */
    public Integer getQuotaUnit() {
        return quotaUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column quota_threshold_node.quota_unit
     *
     * @param quotaUnit the value for quota_threshold_node.quota_unit
     *
     * @mbggenerated
     */
    public void setQuotaUnit(Integer quotaUnit) {
        this.quotaUnit = quotaUnit;
    }
}