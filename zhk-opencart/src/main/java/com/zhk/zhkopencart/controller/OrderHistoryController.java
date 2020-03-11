package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.out.OrderHistoryCreateOutDTO;
import com.zhk.zhkopencart.dto.out.OrderHistoryListOutDTO;
import com.zhk.zhkopencart.po.OrderHistory;
import com.zhk.zhkopencart.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orderHistory")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("list")
    public List<OrderHistoryListOutDTO> getOrderHistoryListByOrderId (@RequestParam Integer orderId){

        List<OrderHistory> orderHistories= orderHistoryService.getOrderHistoryListByOrderId(orderId);
        List<OrderHistoryListOutDTO> orderHistoryListOutDTOS = orderHistories.stream().map(orderHistory -> {
            OrderHistoryListOutDTO orderHistoryListOutDTO = new OrderHistoryListOutDTO();

            orderHistoryListOutDTO.setOrderHistoryId(orderHistory.getOrderId());
            orderHistoryListOutDTO.setOrderStatus(orderHistory.getOrderStatus());
            orderHistoryListOutDTO.setTime(orderHistory.getTime().getTime());
            orderHistoryListOutDTO.setComment(orderHistory.getComment());
            orderHistoryListOutDTO.setCustomerNotified(orderHistory.getCustomerNotified());

            return orderHistoryListOutDTO;
        }).collect(Collectors.toList());

        return orderHistoryListOutDTOS;
    }

    @PostMapping("create")
    public Integer create(@RequestBody(required = false) OrderHistoryCreateOutDTO orderHistoryCreateOutDTO){
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(orderHistoryCreateOutDTO.getOrderId());
        orderHistory.setOrderStatus(orderHistoryCreateOutDTO.getOrderStatus());
        orderHistory.setTime(new Date());
        orderHistory.setComment(orderHistoryCreateOutDTO.getComment());
        orderHistory.setCustomerNotified(orderHistoryCreateOutDTO.getCustomerNotified());
        Integer orderHistoryId= orderHistoryService.createOrderHistory(orderHistory);
        return orderHistoryId;
    }
}
