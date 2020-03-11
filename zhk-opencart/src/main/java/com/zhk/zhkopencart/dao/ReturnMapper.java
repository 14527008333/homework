package com.zhk.zhkopencart.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.po.Return;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReturnMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> getReturnList(@Param("returnId")Integer returnId, @Param("orderId")Integer orderId,
                               @Param("customerName")String customerName, @Param("productName")String productName,
                               @Param("status")Integer status, @Param("createTime")Date createTime, @Param("updateTime")Date updateTime);
}