package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.Return;

public interface ReturnMapper {
    int insert(Return record);

    int insertSelective(Return record);
}