package com.zhk.zhkopencart.service.imp;

import com.zhk.zhkopencart.dao.AddressMapper;
import com.zhk.zhkopencart.dto.out.AddressShowDTO;
import com.zhk.zhkopencart.po.Address;
import com.zhk.zhkopencart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public AddressShowDTO getAddressById(Integer addressId) {

        Address address = addressMapper.selectByPrimaryKey(addressId);

        AddressShowDTO addressShowDTO = new AddressShowDTO();
        addressShowDTO.setAddressId(address.getAddressId());
        addressShowDTO.setReceiverName(address.getReceiverName());
        addressShowDTO.setReceiverMobile(address.getReceiverMobile());
        addressShowDTO.setContent(address.getContent());
        addressShowDTO.setTag(address.getTag());
        return addressShowDTO;
    }

    @Override
    public List<Address> getAddressListByCustomerId(Integer customerId) {

       List<Address> addressList= addressMapper.getAddressListByCustomerId(customerId);

        return null;
    }
}
