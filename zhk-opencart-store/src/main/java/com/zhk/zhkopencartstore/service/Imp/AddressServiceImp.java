package com.zhk.zhkopencartstore.service.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencartstore.dao.AddressMapper;
import com.zhk.zhkopencartstore.dto.in.AddressUpdateInDTO;
import com.zhk.zhkopencartstore.po.Address;
import com.zhk.zhkopencartstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Page<Address> getAddressList(Integer pageNum, Integer customerId) {
        PageHelper.startPage(pageNum,3);
        Page<Address> addresses= addressMapper.getAddressList(customerId);
        return addresses;
    }

    @Override
    public Integer insertSelective(Address address) {
        addressMapper.insertSelective(address);
        return address.getAddressId();
    }

    @Override
    public void addressUpdate(AddressUpdateInDTO addressUpdateInDTO) {
        Address address = new Address();
        address.setAddressId(addressUpdateInDTO.getAddressId());
        address.setReceiverName(addressUpdateInDTO.getReceiverName());
        address.setReceiverMobile(addressUpdateInDTO.getReceiverMobile());
        address.setContent(addressUpdateInDTO.getContent());
        address.setTag(addressUpdateInDTO.getTag());

        addressMapper.updateByPrimaryKeySelective(address);
    }
}
