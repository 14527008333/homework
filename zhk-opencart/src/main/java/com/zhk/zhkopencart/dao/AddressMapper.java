package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.Address;

public interface AddressMapper {
    int insert(Address record);

    int insertSelective(Address record);
}