package com.zhk.zhkopencartstore.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.po.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Page<Order> getOrderList(Integer customerId);
}