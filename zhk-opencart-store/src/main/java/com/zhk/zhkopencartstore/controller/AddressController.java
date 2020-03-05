package com.zhk.zhkopencartstore.controller;

import com.zhk.zhkopencartstore.dto.in.AddressUpdateInDTO;
import com.zhk.zhkopencartstore.dto.out.PageOutDTO;
import com.zhk.zhkopencartstore.po.Address;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    @GetMapping("list")
    public PageOutDTO<Address> getAddressList(@RequestParam(required = false ,defaultValue = "1") Integer pageNum){

        return null;
    }

    @GetMapping("addressCreate")
    public Integer addressCreate(@RequestBody Address address,
                                 @RequestAttribute Integer customerId){

        return null;
    }

    @GetMapping("addressUpdate")
    public void addressUpdate(@RequestBody AddressUpdateInDTO addressUpdateInDTO){

    }
}
