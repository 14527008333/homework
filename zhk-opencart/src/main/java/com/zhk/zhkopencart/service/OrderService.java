package com.zhk.zhkopencart.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.out.OrderListDTO;
import com.zhk.zhkopencart.dto.out.OrderShowDTO;

public interface OrderService {
    Page<OrderListDTO> getOrderList(String customerName, Integer status,
                                    Double totalPrice, Long createTime,
                                    Long updateTime, Integer pageNum);

    OrderShowDTO getOrderById(Integer orderId);
}
