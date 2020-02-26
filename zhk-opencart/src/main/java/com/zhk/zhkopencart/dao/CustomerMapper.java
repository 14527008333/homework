package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.Customer;

public interface CustomerMapper {
    int insert(Customer record);

    int insertSelective(Customer record);
}