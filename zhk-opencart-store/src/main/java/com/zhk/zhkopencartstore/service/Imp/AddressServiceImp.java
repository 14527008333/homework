package com.zhk.zhkopencartstore.service.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhk.zhkopencartstore.dao.AddressMapper;
import com.zhk.zhkopencartstore.dto.in.AddressUpdateInDTO;
import com.zhk.zhkopencartstore.dto.out.AddressShowOutDTO;
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

    @Override
    public AddressShowOutDTO getAddressById(Integer addressId) {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        AddressShowOutDTO addressShowOutDTO = new AddressShowOutDTO();
        addressShowOutDTO.setAddressId(address.getAddressId());
        addressShowOutDTO.setReceiverName(address.getReceiverName());
        addressShowOutDTO.setReceiverMobile(address.getReceiverMobile());
        addressShowOutDTO.setContent(address.getContent());
        addressShowOutDTO.setTag(address.getTag());
        return addressShowOutDTO;
    }

    @Override
    public void deleteByPrimaryKey(Integer addressId) {
        addressMapper.deleteByPrimaryKey(addressId);
    }
}
