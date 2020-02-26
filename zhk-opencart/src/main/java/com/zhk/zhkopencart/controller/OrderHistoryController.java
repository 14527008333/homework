package com.zhk.zhkopencart.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orderHistory")
public class OrderHistoryController {

    @PostMapping("create")
    public Integer create(@RequestPart(required = false)OrderHistory orderHistory){
        return null;
    }
}
