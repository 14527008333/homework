package com.zhk.zhkopencart.dao;

import com.zhk.zhkopencart.po.Product;

public interface ProductMapper {
    int insert(Product record);

    int insertSelective(Product record);
}