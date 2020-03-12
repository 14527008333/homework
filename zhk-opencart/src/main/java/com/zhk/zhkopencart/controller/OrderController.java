package com.zhk.zhkopencart.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.out.*;
import com.zhk.zhkopencart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public PageDTO<OrderListDTO> getOrderList(@RequestParam(required = false) String customerName,
                           @RequestParam(required = false) Integer status,
                           @RequestParam(required = false)Double totalPrice,
                           @RequestParam(required = false)Long createTime,
                           @RequestParam(required = false)Long updateTime,
                           @RequestParam(required = false,defaultValue = "1")Integer pageNum){

       Page<OrderListDTO> orderListDTOS= orderService.getOrderList(customerName,status,totalPrice,createTime,updateTime,pageNum);

        PageDTO<OrderListDTO> orderListDTOPageDTO = new PageDTO<>();
        orderListDTOPageDTO.setTotal(orderListDTOS.getTotal());
        orderListDTOPageDTO.setPageSize(orderListDTOS.getPageSize());
        orderListDTOPageDTO.setPageNum(orderListDTOS.getPageNum());
        orderListDTOPageDTO.setList(orderListDTOS);

        return orderListDTOPageDTO;
    }

    @GetMapping("show")
    public OrderShowDTO show(@RequestParam Integer orderId){
        OrderShowDTO orderShowDTO= orderService.getOrderById(orderId);
        return orderShowDTO;
    }

    @GetMapping("invoiceList")
    public InvoiceShowDTO invoiceList(@RequestParam(required = false)Integer orderId){
        return null;
    }

    @GetMapping("shipShow")
    public ShipShow shipShow(@RequestParam(required = false)Integer orderId){
        return null;
    }
}
