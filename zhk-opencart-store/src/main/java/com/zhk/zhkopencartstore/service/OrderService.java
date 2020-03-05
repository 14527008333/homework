package com.zhk.zhkopencartstore.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.in.OrdercheckoutInDTO;
import com.zhk.zhkopencartstore.dto.out.OrderShowOutDTO;
import com.zhk.zhkopencartstore.po.Order;

public interface OrderService {
    Integer orderCheckout(OrdercheckoutInDTO ordercheckoutInDTO, Integer customerId);

    Page<Order> getOrderList(Integer pageNum, Integer customerId);

    OrderShowOutDTO OrderShowById(Integer orderId);
}
