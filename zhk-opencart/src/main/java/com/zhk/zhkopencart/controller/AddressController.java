package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.AddressCreateDTO;
import com.zhk.zhkopencart.dto.out.AddressShowDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    @GetMapping("show")
    public AddressShowDTO addressShow(@RequestParam(required = false) Integer addressId){

        return null;
    }

    @GetMapping("create")
    public Integer create(@RequestPart(required = false)AddressCreateDTO addressCreateDTO){

        return null;
    }
}
