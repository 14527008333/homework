package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.OrderHistory;

public interface OrderHistoryMapper {
    int insert(OrderHistory record);

    int insertSelective(OrderHistory record);
}