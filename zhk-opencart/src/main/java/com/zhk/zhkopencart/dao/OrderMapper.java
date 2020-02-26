package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.Order;

public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);
}