package com.zhk.zhkopencart.controller;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.in.CustomerCreateDTO;
import com.zhk.zhkopencart.dto.in.CustomerUpdateDTO;
import com.zhk.zhkopencart.dto.in.CustomerUpdateStatusInDTO;
import com.zhk.zhkopencart.dto.out.AddressShowDTO;
import com.zhk.zhkopencart.dto.out.CustomerListDTO;
import com.zhk.zhkopencart.dto.out.CustomerShowDTO;
import com.zhk.zhkopencart.dto.out.PageDTO;
import com.zhk.zhkopencart.po.Customer;
import com.zhk.zhkopencart.service.AddressService;
import com.zhk.zhkopencart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @GetMapping("list")
    public PageDTO<CustomerListDTO> getCustomerList(@RequestParam(required = false) String userName,
                                                    @RequestParam(required = false) String email,
                                                    @RequestParam(required = false)Integer status,
                                                    @RequestParam(required = false)Long createTime,
                                                    @RequestParam(required = false,defaultValue = "1")Integer pageNum){
       Page<Customer> customerPage= customerService.getCustomerList(userName,email,status,createTime,pageNum);

       List<CustomerListDTO> customerListDTOS= customerPage.stream().map(customer->{
           CustomerListDTO customerListDTO = new CustomerListDTO();

           customerListDTO.setCustomerId(customer.getCustomerId());
           customerListDTO.setUserName(customer.getUsername());
           customerListDTO.setRealName(customer.getRealName());
           customerListDTO.setEmail(customer.getEmail());
           customerListDTO.setStatus(customer.getStatus().intValue());
           customerListDTO.setCreateTime(customer.getCreateTime().getTime());
           return customerListDTO;
       }).collect(Collectors.toList());

        PageDTO<CustomerListDTO> customerListDTOPageDTO = new PageDTO<>();
        customerListDTOPageDTO.setTotal(customerPage.getTotal());
        customerListDTOPageDTO.setPageNum(customerPage.getPageNum());
        customerListDTOPageDTO.setPageSize(customerPage.getPageSize());
        customerListDTOPageDTO.setList(customerListDTOS);
        return customerListDTOPageDTO;
    }

    @GetMapping("show")
    public CustomerShowDTO show(@RequestParam(required = false) Integer customerId){
        Customer customer= customerService.getCustomerById(customerId);
        CustomerShowDTO customerShowDTO = new CustomerShowDTO();
        customerShowDTO.setCustomerId(customer.getCustomerId());
        customerShowDTO.setUserName(customer.getUsername());
        customerShowDTO.setRealName(customer.getRealName());
        customerShowDTO.setEmail(customer.getEmail());
        customerShowDTO.setMobil(customer.getMobile());
        customerShowDTO.setAvatarUrl(customer.getAvatarUrl());
        customerShowDTO.setStatus(customer.getStatus().intValue());
        customerShowDTO.setNewsSubscribed(customer.getNewsSubscribed().hashCode());
        customerShowDTO.setRewordPoints(customer.getRewordPoints());
        customerShowDTO.setCreateTime(customer.getCreateTime().getTime());
        customerShowDTO.setDefaultAddressId(customer.getDefaultAddressId());

        //根据addressId获取到默认地址的详细信息
        AddressShowDTO addressById = addressService.getAddressById(customer.getDefaultAddressId());

        customerShowDTO.setDefaultAddress(addressById.getContent());
        return customerShowDTO;
    }

    @GetMapping("updateStatus")
    public void updateStatus(@RequestBody CustomerUpdateStatusInDTO customerUpdateStatusInDTO){
        customerService.CustomerUpdateStatusInDTO(customerUpdateStatusInDTO);

    }





}
