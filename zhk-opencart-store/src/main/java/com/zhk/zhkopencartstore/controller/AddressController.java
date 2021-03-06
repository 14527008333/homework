package com.zhk.zhkopencartstore.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.in.AddressUpdateInDTO;
import com.zhk.zhkopencartstore.dto.out.AddressLsitOutDTO;
import com.zhk.zhkopencartstore.dto.out.AddressShowOutDTO;
import com.zhk.zhkopencartstore.dto.out.PageOutDTO;
import com.zhk.zhkopencartstore.po.Address;
import com.zhk.zhkopencartstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("address")
@CrossOrigin
public class AddressController {


    @Autowired
    private AddressService addressService;

    @GetMapping("list")
    public List<AddressLsitOutDTO> getAddressList(@RequestAttribute Integer customerId){
        List<Address> addresses= addressService.getAddressList(customerId);
        List<AddressLsitOutDTO> addressLsitOutDTOS=addresses.stream().map(address -> {
            AddressLsitOutDTO addressLsitOutDTO = new AddressLsitOutDTO();

            addressLsitOutDTO.setAddressId(address.getAddressId());
            addressLsitOutDTO.setReceiverName(address.getReceiverName());
            addressLsitOutDTO.setReceiverMobile(address.getReceiverMobile());
            addressLsitOutDTO.setContent(address.getContent());
            addressLsitOutDTO.setTag(address.getTag());

            return addressLsitOutDTO;
        }).collect(Collectors.toList());


        return addressLsitOutDTOS;
    }

    @PostMapping("addressCreate")
    public Integer addressCreate(@RequestBody Address address,
                                 @RequestAttribute Integer customerId){
        address.setCustomerId(customerId);
       Integer addressId= addressService.insertSelective(address);
        return addressId;
    }

    @PostMapping("addressUpdate")
    public void addressUpdate(@RequestBody AddressUpdateInDTO addressUpdateInDTO){
        addressService.addressUpdate(addressUpdateInDTO);
    }

    @GetMapping("show")
    public AddressShowOutDTO addressShow(@RequestParam Integer addressId){
        AddressShowOutDTO addressShowOutDTO= addressService.getAddressById(addressId);
        return addressShowOutDTO;
    }

    @PostMapping("delete")
    public void addressDeleteById(@RequestParam Integer addressId){
        addressService.deleteByPrimaryKey(addressId);
    }

}
