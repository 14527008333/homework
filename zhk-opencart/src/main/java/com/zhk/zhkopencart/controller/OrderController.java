package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.out.InvoiceShowDTO;
import com.zhk.zhkopencart.dto.out.OrderListDTO;
import com.zhk.zhkopencart.dto.out.OrderShowDTO;
import com.zhk.zhkopencart.dto.out.ShipShow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @GetMapping("list")
    public OrderListDTO getList(@RequestParam(required = false) String customerName,
                                @RequestParam(required = false) Integer status,
                                @RequestParam(required = false)Double totalPrice,
                                @RequestParam(required = false)Long createTime,
                                @RequestParam(required = false)Long updateTime,
                                @RequestParam(required = false,defaultValue = "1")Integer pageNum){
        return null;
    }

    @GetMapping("show")
    public OrderShowDTO show(@RequestParam(required = false) Integer orderId){
        return null;
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
