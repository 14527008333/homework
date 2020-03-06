package com.zhk.zhkopencartstore.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.po.Return;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnMapper {

    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> getReturnList(@Param("customerId") Integer customerId);
}