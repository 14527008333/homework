package com.zhk.zhkopencartstore.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencartstore.po.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    Page<Address> getAddressList(@Param("customerId") Integer customerId);
}