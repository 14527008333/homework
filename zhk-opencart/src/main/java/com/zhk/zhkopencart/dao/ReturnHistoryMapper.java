package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.ReturnHistory;

public interface ReturnHistoryMapper {
    int insert(ReturnHistory record);

    int insertSelective(ReturnHistory record);
}