package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.ReturnCreateDTO;
import com.zhk.zhkopencart.dto.out.PageDTO;
import com.zhk.zhkopencart.dto.out.ReturnListDTO;
import com.zhk.zhkopencart.dto.out.ReturnShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("return")
public class ReturnController {

    @GetMapping("list")
    public PageDTO<ReturnListDTO> getList(@RequestParam(required = false) Integer returnId,
                                          @RequestParam(required = false) Integer orderId,
                                          @RequestParam(required = false) String customerName,
                                          @RequestParam(required = false) String productName,
                                          @RequestParam(required = false) Integer status,
                                          @RequestParam(required = false)Long createTime,
                                          @RequestParam(required = false)Long updateTime,
                                          @RequestParam(required = false,defaultValue = "1")Integer pageNum){


        return null;
    }

    @GetMapping("show")
    public ReturnShowDTO show(@RequestParam Integer returnId){

        return null;
    }

    @PostMapping("create")
    public Integer create(@RequestPart(required = false) ReturnCreateDTO returnCreateDTO){

        return null;
    }

    @PostMapping("update")
    public void update(@RequestPart(required = false) ReturnCreateDTO returnCreateDTO){

    }
}
