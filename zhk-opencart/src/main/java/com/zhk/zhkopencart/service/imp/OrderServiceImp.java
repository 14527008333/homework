package com.zhk.zhkopencart.service.imp;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencart.dao.OrderDetailMapper;
import com.zhk.zhkopencart.dao.OrderMapper;
import com.zhk.zhkopencart.dto.out.OrderListDTO;
import com.zhk.zhkopencart.dto.out.OrderShowDTO;
import com.zhk.zhkopencart.po.Order;
import com.zhk.zhkopencart.po.OrderDetail;
import com.zhk.zhkopencart.service.OrderService;
import com.zhk.zhkopencart.vo.OrderProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Page<OrderListDTO> getOrderList(String customerName, Integer status,
                                           Double totalPrice, Long createTime,
                                           Long updateTime, Integer pageNum) {

        PageHelper.startPage(pageNum,3);
        Page<OrderListDTO> orderListDTOS= orderMapper.getOrderList(customerName,status,totalPrice,createTime==null?null:new Date(createTime),updateTime==null?null:new Date(updateTime));

        return orderListDTOS;
    }

    @Override
    public OrderShowDTO getOrderById(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey((long) orderId);

        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey((long) orderId);

        OrderShowDTO orderShowDTO = new OrderShowDTO();
        orderShowDTO.setOrderId(order.getOrderId());
        orderShowDTO.setStatus(order.getStatus());
        orderShowDTO.setTotalPrice(order.getTotalPrice());
        orderShowDTO.setRewordPoints(order.getRewordPoints());
        orderShowDTO.setCreateTimestamp(order.getCreateTime().getTime());
        orderShowDTO.setUpdateTimestamp(order.getUpdateTime().getTime());
        orderShowDTO.setShipMethod(orderDetail.getShipMethod());
        orderShowDTO.setShipAddress(orderDetail.getShipAddress());
        orderShowDTO.setShipPrice(orderDetail.getShipPrice());
        orderShowDTO.setPayMethod(orderDetail.getPayMethod());
        orderShowDTO.setInvoicePrice(orderDetail.getInvoicePrice());
        orderShowDTO.setInvoiceAddress(orderDetail.getInvoiceAddress());
        orderShowDTO.setComment(orderDetail.getComment());
        String orderProducts = orderDetail.getOrderProducts();
        List<OrderProductVO> orderProductVOS = JSON.parseArray(orderProducts, OrderProductVO.class);
        orderShowDTO.setOrderProducts(orderProductVOS);

        return orderShowDTO;
    }
}
