package com.ecom.dao.example;

import com.ecom.entity.example.Role3g;
import com.ecom.entity.example.Role3gWithBLOBs;

public interface Role3gMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    int insert(Role3gWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    int insertSelective(Role3gWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    Role3gWithBLOBs selectByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Role3gWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Role3gWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_3g
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Role3g record);
}