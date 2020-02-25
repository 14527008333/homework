package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.CustomerCreateDTO;
import com.zhk.zhkopencart.dto.in.CustomerUpdateDTO;
import com.zhk.zhkopencart.dto.out.CustomerListDTO;
import com.zhk.zhkopencart.dto.out.CustomerShowDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @GetMapping("list")
    public CustomerListDTO getList(@RequestParam(required = false) String userName,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false)Integer status,
                                   @RequestParam(required = false)Long createTime,
                                   @RequestParam(required = false,defaultValue = "1")Integer pageNum){
        return null;
    }

    @GetMapping("show")
    public CustomerShowDTO show(@RequestParam(required = false) Integer customerId){

        return null;
    }

    @PostMapping("create")
    public Integer create(@RequestPart(required = false) CustomerCreateDTO customerCreateDTO){

        return null;
    }
    @PostMapping("update")
    public void update(@RequestPart(required = false) CustomerUpdateDTO customerUpdateDTO){

    }




}
