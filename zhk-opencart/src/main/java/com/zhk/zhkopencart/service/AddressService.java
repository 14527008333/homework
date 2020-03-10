package com.zhk.zhkopencart.service;

import com.zhk.zhkopencart.dto.out.AddressShowDTO;
import com.zhk.zhkopencart.po.Address;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AddressService {
    AddressShowDTO getAddressById(Integer addressId);

    List<Address> getAddressListByCustomerId(Integer customerId);
}
