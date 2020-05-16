package com.jm.oj.jmoj.dao;

import com.jm.oj.jmoj.dataobject.TeacherDO;
import com.jm.oj.jmoj.dataobject.TeacherDOKey;

public interface TeacherDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Sun May 17 00:26:09 AEST 2020
     */
    int deleteByPrimaryKey(TeacherDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Sun May 17 00:26:09 AEST 2020
     */
    int insert(TeacherDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Sun May 17 00:26:09 AEST 2020
     */
    int insertSelective(TeacherDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Sun May 17 00:26:09 AEST 2020
     */
    TeacherDO selectByPrimaryKey(TeacherDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Sun May 17 00:26:09 AEST 2020
     */
    int updateByPrimaryKeySelective(TeacherDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Sun May 17 00:26:09 AEST 2020
     */
    int updateByPrimaryKey(TeacherDO record);
}