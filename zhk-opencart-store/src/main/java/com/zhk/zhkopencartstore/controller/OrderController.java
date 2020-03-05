package com.zhk.zhkopencartstore.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.in.OrdercheckoutInDTO;
import com.zhk.zhkopencartstore.dto.out.OrderListOutDTO;
import com.zhk.zhkopencartstore.dto.out.OrderShowOutDTO;
import com.zhk.zhkopencartstore.dto.out.PageOutDTO;
import com.zhk.zhkopencartstore.po.Order;
import com.zhk.zhkopencartstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("checkout")
    @Transactional
    public Integer orderCheckout(@RequestBody OrdercheckoutInDTO ordercheckoutInDTO,
                                 @RequestAttribute Integer customerId){
       Integer orderId= orderService.orderCheckout(ordercheckoutInDTO,customerId);
        return null;
    }

    @GetMapping("list")
    public PageOutDTO<OrderListOutDTO> getOrderList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestAttribute Integer customerId){
        Page<Order> orders= orderService.getOrderList(pageNum,customerId);
       List<OrderListOutDTO> orderListOutDTOS= orders.stream().map(order -> {
            OrderListOutDTO orderListOutDTO = new OrderListOutDTO();

            orderListOutDTO.setOrderId(order.getOrderId());
            orderListOutDTO.setRewordPoints(order.getRewordPoints());
            orderListOutDTO.setTotalPrice(order.getTotalPrice());
            orderListOutDTO.setStatus(order.getStatus());
            orderListOutDTO.setCreateTime(order.getCreateTime());

            return orderListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<OrderListOutDTO> orderListOutDTOPageOutDTO = new PageOutDTO<OrderListOutDTO>();
        orderListOutDTOPageOutDTO.setTotal(orders.getTotal());
        orderListOutDTOPageOutDTO.setPageSize(orders.getPageSize());
        orderListOutDTOPageOutDTO.setPageNum(orders.getPageNum());
        orderListOutDTOPageOutDTO.setList(orderListOutDTOS);
        return orderListOutDTOPageOutDTO;
    }

    @PostMapping("show")
    public OrderShowOutDTO OrderShowById(@RequestParam Integer orderId){

        OrderShowOutDTO orderShowOutDTO= orderService.OrderShowById(orderId);

        return null;
    }

}
