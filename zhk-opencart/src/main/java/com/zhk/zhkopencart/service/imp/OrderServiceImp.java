package com.zhk.zhkopencart.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencart.dao.OrderMapper;
import com.zhk.zhkopencart.dto.out.OrderListDTO;
import com.zhk.zhkopencart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Page<OrderListDTO> getOrderList(String customerName, Integer status,
                                           Double totalPrice, Long createTime,
                                           Long updateTime, Integer pageNum) {

        PageHelper.startPage(pageNum,3);
        Page<OrderListDTO> orderListDTOS= orderMapper.getOrderList(customerName,status,totalPrice,createTime,updateTime);

        return orderListDTOS;
    }
}
