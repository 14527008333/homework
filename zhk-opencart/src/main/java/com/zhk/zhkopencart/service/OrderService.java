package com.zhk.zhkopencart.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.out.OrderListDTO;

public interface OrderService {
    Page<OrderListDTO> getOrderList(String customerName, Integer status,
                                    Double totalPrice, Long createTime,
                                    Long updateTime, Integer pageNum);
}
