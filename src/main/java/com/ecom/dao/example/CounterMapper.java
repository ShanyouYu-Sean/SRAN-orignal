package com.ecom.dao.example;

import com.ecom.entity.example.Counter;

public interface CounterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int insert(Counter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int insertSelective(Counter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    Counter selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Counter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table counter
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Counter record);
}