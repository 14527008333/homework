package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.po.OrderHistory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orderHistory")
public class OrderHistoryController {

    @PostMapping("create")
    public Integer create(@RequestPart(required = false) OrderHistory orderHistory){
        return null;
    }
}
