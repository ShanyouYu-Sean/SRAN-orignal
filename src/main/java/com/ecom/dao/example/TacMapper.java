package com.ecom.dao.example;

import com.ecom.entity.example.Tac;

public interface TacMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tac
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tac
     *
     * @mbggenerated
     */
    int insert(Tac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tac
     *
     * @mbggenerated
     */
    int insertSelective(Tac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tac
     *
     * @mbggenerated
     */
    Tac selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tac
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Tac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tac
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Tac record);
}