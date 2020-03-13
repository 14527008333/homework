package com.zhk.zhkopencartstore.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.in.AddressUpdateInDTO;
import com.zhk.zhkopencartstore.dto.out.AddressShowOutDTO;
import com.zhk.zhkopencartstore.po.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddressList(Integer customerId);

    Integer insertSelective(Address address);

    void addressUpdate(AddressUpdateInDTO addressUpdateInDTO);

    AddressShowOutDTO getAddressById(Integer addressId);

    void deleteByPrimaryKey(Integer addressId);
}
