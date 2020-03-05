package com.zhk.zhkopencartstore.controller;

import com.zhk.zhkopencartstore.dto.in.CustomerChangerPasswordInDTO;
import com.zhk.zhkopencartstore.dto.in.CustomerRegisterInDTO;
import com.zhk.zhkopencartstore.dto.in.CustomerUpdateInDTO;
import com.zhk.zhkopencartstore.dto.out.CustomerLoginOutDTO;
import com.zhk.zhkopencartstore.dto.out.CustomerShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

    @PostMapping("register")
    public Integer registerCustomer(@RequestBody CustomerRegisterInDTO customerRegisterInDTO){

        return null;
    }

    @GetMapping("login")
    public CustomerLoginOutDTO registerCustomer(@RequestParam String userName, @RequestParam String password){


        return null;
    }
    @GetMapping("getProfile")
    public CustomerShowOutDTO getProfile(@RequestAttribute Integer customerId){

        return null;
    }

    @GetMapping("updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateInDTO customerUpdateInDTO, @RequestAttribute Integer customerId){

    }

    @GetMapping("customerChangePassword")
    public void customerChangePassword(@RequestBody CustomerChangerPasswordInDTO customerChangerPasswordInDTO,
                                       @RequestAttribute Integer customerId){

    }
}
