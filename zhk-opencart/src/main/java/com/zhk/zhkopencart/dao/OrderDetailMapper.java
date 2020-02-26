package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.OrderDetail;

public interface OrderDetailMapper {
    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);
}