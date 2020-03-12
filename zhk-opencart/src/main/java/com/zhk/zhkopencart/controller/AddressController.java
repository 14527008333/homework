package com.zhk.zhkopencart.controller;

import com.zhk.zhkopencart.dto.in.AddressCreateDTO;
import com.zhk.zhkopencart.dto.out.AddressShowDTO;
import com.zhk.zhkopencart.po.Address;
import com.zhk.zhkopencart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("getAddressListByCustomerId")
    public List<Address> getAddressListByCustomerId(@RequestParam(required = false)Integer customerId){

        List<Address> addressList= addressService.getAddressListByCustomerId(customerId);

        return addressList;
    }

    @GetMapping("show")
    public AddressShowDTO addressShow(@RequestParam(required = false) Integer addressId){
        AddressShowDTO addressShowDTO= addressService.getAddressById(addressId);
        return addressShowDTO;
    }


}
