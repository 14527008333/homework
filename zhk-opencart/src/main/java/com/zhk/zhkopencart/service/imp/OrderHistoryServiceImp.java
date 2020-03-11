package com.zhk.zhkopencart.service.imp;

import com.zhk.zhkopencart.dao.OrderHistoryMapper;
import com.zhk.zhkopencart.po.OrderHistory;
import com.zhk.zhkopencart.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImp implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Override
    public Integer createOrderHistory(OrderHistory orderHistory) {

        orderHistoryMapper.insertSelective(orderHistory);
        return orderHistory.getOrderHistoryId().intValue();
    }

    @Override
    public List<OrderHistory> getOrderHistoryListByOrderId(Integer orderId) {
        List<OrderHistory> orderHistories= orderHistoryMapper.getOrderHistoryListByOrderId(orderId);
        return orderHistories;
    }
}
