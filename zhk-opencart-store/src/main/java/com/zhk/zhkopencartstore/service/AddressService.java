package com.zhk.zhkopencartstore.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.dto.in.AddressUpdateInDTO;
import com.zhk.zhkopencartstore.dto.out.AddressShowOutDTO;
import com.zhk.zhkopencartstore.po.Address;

public interface AddressService {
    Page<Address> getAddressList(Integer pageNum, Integer customerId);

    Integer insertSelective(Address address);

    void addressUpdate(AddressUpdateInDTO addressUpdateInDTO);

    AddressShowOutDTO getAddressById(Integer addressId);

    void deleteByPrimaryKey(Integer addressId);
}
