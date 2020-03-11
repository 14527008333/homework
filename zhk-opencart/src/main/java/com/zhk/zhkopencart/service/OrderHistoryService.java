package com.zhk.zhkopencart.service;

import com.zhk.zhkopencart.po.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    Integer createOrderHistory(OrderHistory orderHistory);

    List<OrderHistory> getOrderHistoryListByOrderId(Integer orderId);
}
